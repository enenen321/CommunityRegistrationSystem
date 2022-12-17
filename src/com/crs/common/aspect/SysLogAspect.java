package com.crs.common.aspect;

import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.crs.common.annotation.SystemLog;
import com.crs.entity.SysLog;
import com.crs.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志切面类
 * @author LZ
 * @date 2022/12/2 15:02
 */
@Aspect
@Component
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class SysLogAspect {
    private Logger logger=  LoggerFactory.getLogger(SysLogAspect.class);

    private ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<>();

    private static final String REQUEST_PARAMS = "REQUEST_PARAMS";

    private static final String START_TIME = "START_TIME";

    private static final Integer ABNORMAL = 1;

    private static final Integer NORMAL = 0;

    private final SysLogService sysLogService;

    /**
     * 该方式仅* com.front.controller..*.*(..))目录下的方法会生效
     * 其他目录下不生效
     */
    @Pointcut("execution(* com.crs.controller..*.*(..))")
    private void executionLog(){

    }

    /**
     * 该方式所有使用了@SysLog注解的方法都会生效
     */
    @Pointcut("@annotation(com.crs.common.annotation.SystemLog)")
    private void annotationLog(){

    }

    /**
     * 定义前置通知
     * @param joinPoint 连接点
     * @param sysLog 注解类
     */
    @Before(value = "annotationLog() && @annotation(sysLog)")
    private void doBefore(JoinPoint joinPoint, SystemLog sysLog){
        Long startTime = System.currentTimeMillis();
        Map<String,Object> map = new HashMap<>(3);
        //记录调用开始时间
        map.put(START_TIME,startTime);
        StringBuilder requestStr = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0){
            for (Object arg:args){
                requestStr.append(arg.toString());
            }
        }
        map.put(REQUEST_PARAMS,requestStr.toString());
        threadLocal.set(map);
        logger.info("interface:{}：rgs:{}",sysLog.message(),map.get(REQUEST_PARAMS));
    }

    /**
     * 抛出异常信息
     * @param sysLog 注解类
     * @param throwable 异常
     */
    @AfterThrowing(value = "annotationLog() && @annotation(sysLog)",throwing = "throwable")
    private void doAfterReturning(SystemLog sysLog, Throwable throwable){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //访问ip
        String remoteAddr = request.getRemoteAddr();
        //写入数据库
        insert(sysLog.message(), "",request.getRequestURL().toString(),remoteAddr,ABNORMAL,null,throwable.toString());
        threadLocal.remove();
        logger.error("interface:{},error==>{}",sysLog.message(),throwable);
    }

    /**
     * 写入数据库
     * @param message 接口message
     * @param result 返回结果
     * @param status 状态 是否异常
     * @param url 请求url
     * @param ip 访问ip
     * @param exception 异常信息
     */
    private void insert(String message,String result,String url,String ip,Integer status,Long time,String exception){
        SysLog sysLogEntity =new SysLog();
        sysLogEntity.setMessage(message);
        sysLogEntity.setStatus(status);
        sysLogEntity.setResult(result);
        sysLogEntity.setTime(time);
        sysLogEntity.setUrl(URLUtil.getPath(url));
        sysLogEntity.setIp(ip);
        sysLogEntity.setException(exception);
        sysLogService.save(sysLogEntity);
    }

    /**
     * 完成调用
     * @param sysLog 注解类
     * @param res 接口返回内容
     */
    @AfterReturning(value ="annotationLog() && @annotation(sysLog)",returning = "res")
    private void doAfterReturning(SystemLog sysLog, Object res){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //访问ip
        String remoteAddr = request.getRemoteAddr();
        Map<String,Object> threadInfo = threadLocal.get();
        Long start = (Long) threadInfo.get(START_TIME);
        //接口调用时长
        Long time =System.currentTimeMillis() - start;
        //写入数据库
        insert(sysLog.message(),JSONUtil.toJsonStr(res),request.getRequestURL().toString(),remoteAddr,NORMAL,time,null);
        threadLocal.remove();
        logger.info("interface:{}调用完成,result:{},time:{}ms", "《"+sysLog.message()+"》",res,time);
    }
}
