package ${springPackageName}.common.aop;

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
import ${springPackageName}.common.annotation.OperationLog;
import ${springPackageName}.entity.Log;
import ${springPackageName}.entity.User;
import ${springPackageName}.service.LogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class OperationLogAop {

    @Resource
    private LogService logService;

    @Pointcut(value = "execution(* ${springPackageName}.controller..*.*(..))")
    public void pointCut() {
        log.info("pointCut");
    }

    @Around(value = "pointCut()")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
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
        Long uId = null;
        String userName = null;
        if(subject.isAuthenticated()){
            // 获取用户ID
            User principal = (User) subject.getPrincipal();
            uId = principal.getUId();
            userName = principal.getUName();
        }

        boolean annotationPresent = method.isAnnotationPresent(OperationLog.class);
        // 如果注解不存在，就执行代码逻辑
        if(!annotationPresent){
            return proceedingJoinPoint.proceed();
        }
        // 存在就拿到注解的参数
        OperationLog sysOperationLog = method.getAnnotation(OperationLog.class);
        String logValue = sysOperationLog.value();

        // 拼接日志参数
        StringBuffer logParam = new StringBuffer();
        logParam.append("用户ID:").append(uId);
        if (!StringUtils.isEmpty(userName)) {
            logParam.append(",用户名:").append(userName);
        }
        logParam.append(",请求路径:").append(url)
                .append(",").append(methodName)
                .append("行为:").append(logValue)
                .append(";");

        Log log = new Log().setLContent(logParam.toString()).setLIp(request.getRemoteAddr()).setLUserId(uId);
        boolean save = logService.save(log);
        if(!save){
            log.setLContent("保存日志失败");
            throw new RuntimeException("日志记录失败");
        }
        return proceedingJoinPoint.proceed();
    }
}
