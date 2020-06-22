package org.pettyfox.framework.restweb.config;

import com.eface.base.comm.exception.DirtyException;
import com.eface.base.comm.exception.RestCommException;
import com.eface.comm.base.web.rest.RestObjectResponse;
import com.eface.comm.base.web.rest.RestObjectResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 异常处理
 * @author eface
 */
@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

    /**
     * 全局异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public RestObjectResponse errorHandler(Exception ex) {
        log.error("server not catch exception",ex);
        return RestObjectResponse.err(RestObjectResponseCode.API_ERROR_UN_CATCH,0,"服务端未处理异常");
    }
    @ResponseBody
    @ExceptionHandler(value = DirtyException.class)
    public RestObjectResponse dirtyHandler(DirtyException ex) {
        log.debug("server dirtyHandler exception:{}",ex.getMessage());
        return RestObjectResponse.err(RestObjectResponseCode.API_ERROR_BCODE,ex.getBcode(),ex.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(value = RestCommException.class)
    public RestObjectResponse restCommException(RestCommException ex) {
        log.debug("server restCommException exception:{}",ex.getMessage());

        return RestObjectResponse.err(ex.getCode(),ex.getMessage());
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常，详情继续往下看代码
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public RestObjectResponse bindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return RestObjectResponse.err(RestObjectResponseCode.API_ERROR_PARAM,0,message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public RestObjectResponse constraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return RestObjectResponse.err(RestObjectResponseCode.API_ERROR_PARAM,0,message);
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestObjectResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return RestObjectResponse.err(RestObjectResponseCode.API_ERROR_PARAM,0,message);
    }


}