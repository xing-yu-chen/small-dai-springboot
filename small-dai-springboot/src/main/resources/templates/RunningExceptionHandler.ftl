package ${springPackageName}.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.apache.shiro.authz.UnauthorizedException;
import ${springPackageName}.common.response.ResponseVO;

/**
* @Project: small-dai-springboot
* @Description: 处理运行时异常抛出异常信息
* @Data:Created in 2022/3/25 1:37 下午
*/
@Slf4j
@RestControllerAdvice
public class RunningExceptionHandler {

    private static final String ILLEGAL_ARGUMENT_EXCEPTION_MSG = "Assert异常:\n{}";
    private static final String RUNTIME_EXCEPTION_MSG = "运行时异常:\n{}";
    private static final String NO_HANDLER_FOUND_EXCEPTION_MSG = "请求的资源不可用:\n{}";
    private static final String HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION = "请求的方法不合法:\n{}";
    private static final String INTERNAL_SERVER_EXCEPTION = "服务器内部错误:\n{}";

    private static final String NULL_POINT_EXCEPTION = "空指针异常\n{}";

    /**
    * @Discription: 处理Assert的异常 400
    * @Date: 2022/3/25 1:40 下午
    */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseVO handler(IllegalArgumentException e){
        log.error(ILLEGAL_ARGUMENT_EXCEPTION_MSG, e.getMessage());
        return ResponseVO.failByBadRequest();
    }

    /**
    * @Discription: 运行时异常 400
    * @Date: 2022/3/25 1:44 下午
    */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseVO handler(RuntimeException e){
        log.error(RUNTIME_EXCEPTION_MSG, e.getMessage());
        return ResponseVO.failByBadRequest();
        }

    /**
    * @Author: xingyuchen
    * @Discription: 请求的资源不可用
    * @Date: 2022/3/25 1:48 下午
    */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseVO handler(NoHandlerFoundException e){
        log.error(NO_HANDLER_FOUND_EXCEPTION_MSG, e.getMessage());
        return ResponseVO.failByNotFound(e.getRequestURL());
    }

    /**
    * @Discription: 请求的方法不合法
    * @Date: 2022/3/25 1:59 下午
    */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseVO handler(HttpRequestMethodNotSupportedException e){
        log.error(HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, e.getMessage());
        return ResponseVO.failByMethodNotAllowed(e.getMethod());
    }

    /**
    * @Discription: 服务器内部错误
    * @Date: 2022/3/25 2:16 下午
    */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseVO handler(Exception e){
        log.error(INTERNAL_SERVER_EXCEPTION, e.getMessage());
        return ResponseVO.failByInternalServer(e.getMessage());
    }

    /**
    * @Discription: 空指针异常
    * @Date: 2022/3/25 2:22 下午
    */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseVO handler(NullPointerException e){
        log.error(NULL_POINT_EXCEPTION, e.getMessage());
        return ResponseVO.fail(500, "空指针异常", "NullPointerException");
    }

    /**
    * @Discription: 授权异常处理
    * @Date: 2022/4/5 11:32 下午
    */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseVO handleShiroException(UnauthorizedException ex) {
        return ResponseVO.fail(300, "没有权限", ex.getMessage());
    }
}