package org.pettyfox.examples.service.account.infrastructure.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import org.pettyfox.base.comm.log.ApiLog;
import org.pettyfox.base.comm.log.ApiLogType;
import org.pettyfox.examples.service.account.doamin.account.biz.SystemLogBiz;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


@Aspect
@Slf4j
@Component
public class ApiLogAspect {
    /**
     * The Session.
     */
    @Resource
    HttpSession session;
    @Resource
    private SystemLogBiz systemLogBiz;
    @Pointcut("@annotation(org.pettyfox.base.comm.log.ApiLog)")
    public void point(){

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ApiLog annotation = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(ApiLog.class);
        String logText =buildLogText(joinPoint,annotation);
        JSONObject data = new JSONObject();
        if(annotation.optionType() == ApiLogType.OptionType.DELETE_BATCH){
            if(ArrayUtils.isNotEmpty(joinPoint.getArgs()) && joinPoint.getArgs()[0] instanceof List){
                List<Long> ids = (List<Long>) joinPoint.getArgs()[0];
               logText += Strings.join(ids,',');
            }else{
                log.warn("api log code format error!{}",annotation.log());
            }
        }else if(annotation.optionType() == ApiLogType.OptionType.SAVE){
            Object entityId = getEntityId(joinPoint,annotation);
            if(null == entityId){
                logText += String.format(",新增:%s",getEntityKey(joinPoint,annotation));
                data.put("insert",JSONObject.toJSONString(joinPoint.getArgs()[0]));
            }else{
                logText += String.format(",修改:%s(%s)",getEntityKey(joinPoint,annotation),entityId);
                data.put("update",JSONObject.toJSONString(joinPoint.getArgs()[0]));
            }
        }
        ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String uri = request.getRequest().getRequestURI();
        String ip = request.getRequest().getHeader("X-Real-IP");
        if(null == ip){
            ip = request.getRequest().getRemoteHost();
        }

        Object result = joinPoint.proceed(joinPoint.getArgs());

        if(ApiLogType.Type.ACCOUNT_OPTION_LOG.equals(annotation.type())){
            systemLogBiz.saveOptionLog(1,"admin",ip,uri,logText,data.toJSONString(),annotation.optionType());
        }else if(ApiLogType.Type.SYSTEM_LOG.equals(annotation.type())){
            systemLogBiz.saveSystemLog(1,"admin",ip,uri,logText);
        }
        return result;

    }
    private Object getEntityId(ProceedingJoinPoint joinPoint,ApiLog annotation) {
        try {
            Method method = joinPoint.getArgs()[0].getClass().getMethod(getMethodName("id"));
            return method.invoke(joinPoint.getArgs()[0]);
        }catch (NoSuchMethodException e) {
            log.error("aop log error",e);
        } catch (InvocationTargetException e) {
            log.error("aop log error",e);
        } catch (IllegalAccessException e) {
            log.error("aop log error",e);
        }

        return null;

    }
    private Object getEntityKey(ProceedingJoinPoint joinPoint,ApiLog annotation){
        try {
            Method method = joinPoint.getArgs()[0].getClass().getMethod(getMethodName(annotation.key()));
            return method.invoke(joinPoint.getArgs()[0]);
        }  catch (IllegalAccessException e) {
//            log.error("aop log error",e);
        } catch (NoSuchMethodException e) {
//            log.error("aop log error",e);
        } catch (InvocationTargetException e) {
//            log.error("aop log error",e);
        }
        return null;
    }
    private String getMethodName(String field){
        return "get"+field.substring(0,1).toUpperCase()+field.substring(1);
    }
    private String buildLogText(ProceedingJoinPoint joinPoint,ApiLog annotation){
        String logText = annotation.log();
        for(int i = 0;i<10;i++){
            String key = "${"+i+"}";
            if(logText.contains(key)){
                if(joinPoint.getArgs() != null && joinPoint.getArgs().length>i && joinPoint.getArgs()[i] !=null){
                    logText = logText.replace(key,joinPoint.getArgs()[i].toString());
                }else{
                    logText = logText.replace(key,"全部");
                }
            }
        }

        return logText;
    }


}
