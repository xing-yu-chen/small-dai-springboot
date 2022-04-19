package top.smalldai.smalldaispringboot.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.smalldai.smalldaispringboot.common.constant.ResultConstant;
import top.smalldai.smalldaispringboot.common.result.ResultVO;

/**
 * @Author: xing-yu-chen
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
     * @Author: xingyuchen
     * @Discription: 处理Assert的异常 400
     * @Date: 2022/3/25 1:40 下午
    */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultVO handler(IllegalArgumentException e){
        log.error(ILLEGAL_ARGUMENT_EXCEPTION_MSG, e.getMessage());
        return ResultVO.failByBadRequest();
    }

    /**
     * @Author: xingyuchen
     * @Discription: 运行时异常 400
     * @Date: 2022/3/25 1:44 下午
    */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResultVO handler(RuntimeException e){
        log.error(RUNTIME_EXCEPTION_MSG, e.getMessage());
        return ResultVO.failByBadRequest();
    }

    /**
     * @Author: xingyuchen
     * @Discription: 请求的资源不可用
     * @Date: 2022/3/25 1:48 下午
    */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResultVO handler(NoHandlerFoundException e){
        log.error(NO_HANDLER_FOUND_EXCEPTION_MSG, e.getMessage());
        return ResultVO.failByNotFound(e.getRequestURL());
    }

    /**
     * @Author: xingyuchen
     * @Discription: 请求的方法不合法
     * @Date: 2022/3/25 1:59 下午
    */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResultVO handler(HttpRequestMethodNotSupportedException e){
        log.error(HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, e.getMessage());
        return ResultVO.failByMethodNotAllowed(e.getMethod());
    }

    /**
     * @Author: xingyuchen
     * @Discription: 服务器内部错误
     * @Date: 2022/3/25 2:16 下午
    */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResultVO handler(Exception e){
        log.error(INTERNAL_SERVER_EXCEPTION, e.getMessage());
        return ResultVO.failByInternalServer(e.getMessage());
    }

    /**
     * @Author: xingyuchen
     * @Discription: 空指针异常
     * @Date: 2022/3/25 2:22 下午
    */
    @ExceptionHandler(value = NullPointerException.class)
    public ResultVO handler(NullPointerException e){
        log.error(NULL_POINT_EXCEPTION, e.getMessage());
        return ResultVO.fail(ResultConstant.INTERNAL_SERVER_CODE, ResultConstant.INTERNAL_SERVER_MSG, "空指针异常");
    }

    /**
     * @Author: xingyuchen
     * @Discription: 授权异常处理
     * @Date: 2022/4/5 11:32 下午
    */
    @ExceptionHandler(UnauthorizedException.class)
    public ResultVO handleShiroException(UnauthorizedException ex) {
        log.error("授权异常:\n{}", ex.getMessage());
        return ResultVO.fail(300, "没有权限", ex.getMessage());
    }
}
