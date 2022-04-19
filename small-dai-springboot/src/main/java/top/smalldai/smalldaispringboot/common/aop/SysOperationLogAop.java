package top.smalldai.smalldaispringboot.common.aop;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.smalldai.smalldaispringboot.common.annotation.SysOperationLog;
import top.smalldai.smalldaispringboot.entity.system.SysLog;
import top.smalldai.smalldaispringboot.entity.system.SysUser;
import top.smalldai.smalldaispringboot.service.system.SysLogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/4/6 2:28 上午
 */
@Slf4j
@Aspect
@Component
public class SysOperationLogAop {

    @Resource
    private SysLogService sysLogService;


    @Pointcut(value = "execution(* top.smalldai.smalldaispringboot.controller.system..*.*(..))")
    public void pointCut(){
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 方法名
        String methodName = method.getName();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求路径
        String url = request.getRequestURI();
        // 获取客户端请求IP
        String ipAddress = request.getRemoteAddr();
        // 获取当前的用户ID
        // 判断用户是否登陆
        Subject subject = SecurityUtils.getSubject();
        Long userId = 0L;
        String userName = "";
        if(subject.isAuthenticated()){
            // 获取用户ID
            SysUser principal = (SysUser) subject.getPrincipal();
            userId = principal.getUId();
            userName = principal.getName();
        }
        boolean annotationPresent = method.isAnnotationPresent(SysOperationLog.class);
        // 如果注解不存在，就执行代码逻辑
        if(!annotationPresent){
            return joinPoint.proceed();
        }
        // 存在就拿到注解的参数
        SysOperationLog sysOperationLog = method.getAnnotation(SysOperationLog.class);
        String logValue = sysOperationLog.value();

        // 拼接日志参数
        StringBuffer logParam = new StringBuffer();
        logParam.append("用户ID:").append(userId);
        if (!StringUtils.isEmpty(userName)) {
            logParam.append(",用户名:").append(userName);
        }
        logParam.append(",请求路径:").append(url)
                .append(",行为:").append(logValue).append(";");

        SysLog sysLog = new SysLog().setUId(userId).setAction(logParam.toString())
                .setIp(ipAddress).setDbSource("small-dai-springboot");
        boolean save = sysLogService.save(sysLog);
        if(!save){
            log.error("日志记录失败");
            throw new RuntimeException("日志记录失败");
        }
        return joinPoint.proceed();
    }
}
