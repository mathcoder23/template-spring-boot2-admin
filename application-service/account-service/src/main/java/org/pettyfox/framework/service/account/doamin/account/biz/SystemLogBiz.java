package org.pettyfox.framework.service.account.doamin.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.joda.time.DateTime;
import org.pettyfox.base.comm.log.ApiLogType;
import org.pettyfox.base.web.dao.BaseService;
import org.pettyfox.framework.service.account.doamin.account.po.SystemLog;
import org.pettyfox.framework.service.account.doamin.account.repository.SystemLogMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Petty Fox
 */
@Service
public class SystemLogBiz extends BaseService<SystemLogMapper, SystemLog> {
    private static final String LOCALHOST = "127.0.0.1";
    private static final String LOCALHOST_GET = "0:0:0:0:0:0:0:1";

    public PageInfo<SystemLog> listPage(Integer pageNo,
                                        Integer pageSize,
                                        String startDate,
                                        String endDate,
                                        Integer type,
                                        String ip,
                                        String api,
                                        String log) {

        QueryWrapper<SystemLog> queryWrapper = new QueryWrapper<>();
        if (null != startDate && null != endDate) {
            queryWrapper.between("datetime", startDate, endDate);
        }
        if (StringUtils.isNotBlank(ip)) {
            queryWrapper.like("ip", ip);
        }
        if (StringUtils.isNotBlank(log)) {
            queryWrapper.like("log", log);
        }
        if (StringUtils.isNotBlank(api)) {
            queryWrapper.like("api", api);
        }
        if (null != type) {
            queryWrapper.eq("type", type);
        }

        queryWrapper.orderByDesc("id");
        PageHelper.startPage(pageNo, pageSize);
        List<SystemLog> list = list(queryWrapper);
        return new PageInfo<>(list, pageSize);
    }

    @Async
    public void saveSystemLog(String ip, String api, String log) {
        save(SystemLog.builder()
                .accountName("系统")
                .ip(ip)
                .api(api)
                .log(log)
                .datetime(new Date())
                .type(ApiLogType.Type.SYSTEM_LOG)
                .build());
    }

    @Async
    public void saveSystemLog(Integer accountId, String accountName, String ip, String api, String log) {
        ip = formatLocalhostIp(ip);
        save(SystemLog.builder()
                .accountName(accountName)
                .ip(ip)
                .accountId(accountId)
                .api(api)
                .log(log)
                .datetime(new Date())
                .type(ApiLogType.Type.SYSTEM_LOG)
                .build());
    }

    @Async
    public void saveOptionLog(Integer accountId, String accountName, String ip, String api, String log, String data, ApiLogType.OptionType optionType) {
        ip = formatLocalhostIp(ip);
        save(SystemLog.builder()
                .accountId(accountId)
                .accountName(accountName)
                .ip(ip)
                .api(api)
                .log(log)
                .data(data)
                .optionType(optionType)
                .datetime(new Date())
                .type(ApiLogType.Type.ACCOUNT_OPTION_LOG)
                .build());
    }

    public void clearExpireLogRecord(Integer expireDay) {
        QueryWrapper<SystemLog> queryWrapper = new QueryWrapper<>();
        Date expireDate = new DateTime().minusDays(expireDay).toDate();
        queryWrapper.le("datetime", expireDate);
        remove(queryWrapper);
    }

    private String formatLocalhostIp(String ip) {
        if (LOCALHOST_GET.equals(ip)) {
            return LOCALHOST;
        }
        return ip;
    }
}
