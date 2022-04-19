package top.smalldai.smalldaispringboot.common.aop;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.smalldai.smalldaispringboot.common.annotation.GenOperationLog;
import top.smalldai.smalldaispringboot.entity.generation.GenLog;
import top.smalldai.smalldaispringboot.entity.system.SysUser;
import top.smalldai.smalldaispringboot.service.generation.GenLogService;
import top.smalldai.smalldaispringboot.service.system.SysUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description:
 * @Data:Created in 2022/4/4 10:26 下午
 */
@Aspect
@Component
@Slf4j
public class GenOperationLogAop {

    @Resource
    private GenLogService genLogService;

    @Pointcut(value = "execution(* top.smalldai.smalldaispringboot.controller.generation.*.*(..))")
    public void pointCut(){
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        GenLog genLog = new GenLog();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取方法名
        Method method = signature.getMethod();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求的url
        String url = request.getRequestURI();
        // 获取请求的方法名
        String methodName = method.getName();
        // 获取请求的参数
        Object[] args = joinPoint.getArgs();
        List<Object> arg = Arrays.asList(args);

        // 获取请求的ip地址
        String ip = request.getRemoteAddr();
        genLog.setIp(ip);
        log.info("请求的url:{},请求的方法名:{},请求的参数:{},请求的ip地址:{}", url, methodName, arg, ip);

        // 获取GenOperationLog注解
        boolean annotationPresent = method.isAnnotationPresent(GenOperationLog.class);
        if(!annotationPresent){
            return joinPoint.proceed();
        }
        GenOperationLog genOperationLog = method.getAnnotation(GenOperationLog.class);

        Object proceed = joinPoint.proceed();

        // 行为
        String action = genOperationLog.value();
        genLog.setAction(action);
        // 是否有sql
        boolean type = genOperationLog.type();
        if (!type) {
            genLog.setDbSource(null).setSqlContent(null);
        } else {
            genLog.setDbSource(request.getSession().getAttribute("databaseName").toString()).setSqlContent(request.getSession().getAttribute("sql").toString());
        }

        // 获取登陆的用户Id
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipal() != null){
            SysUser principal = (SysUser) subject.getPrincipal();
            Long uId = principal.getUId();
            genLog.setUId(ObjectUtil.isNull(uId) ? 0L : uId);
        }else {
            genLog.setUId(0L);
        }

        // 保存日志信息
        boolean save = genLogService.save(genLog);
        if (!save) {
            throw new RuntimeException("保存日志信息失败");
        }
        return proceed;
    }
}
