package ${springPackageName}.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(description = "消息返回信息VO")
public class ResponseVO implements Serializable {

    @ApiModelProperty(value = "状态码", name = "code")
    private Integer code;
    @ApiModelProperty(value = "提示信息", name = "msg")
    private String msg;
    @ApiModelProperty(value = "返回值内容", name = "data")
    private Object data;

    /**
    * @Discription: 返回成功信息的底层封装
    * @param code 状态码
    * @param msg 提示信息
    * @param data 返回值内容
    * @Date: 2022/3/25 10:49 上午
    */
    public static ResponseVO success(Integer code, String msg, Object data){
        return new ResponseVO().setCode(code).setMsg(msg).setData(data);
    }

    /**
    * @Discription: 请求成功的封装
    * @param msg 提示信息
    * @param data 返回值内容
    * @Date: 2022/3/25 10:50 上午
    */
    public static ResponseVO success(String msg, Object data){
        return success(200, msg, data);
    }

    /**
    * @Discription: 返回失败信息的封装
    * @param code 状态码
    * @param msg 提示信息
    * @param data 返回值内容
    * @Date: 2022/3/25 10:51 上午
    */
    public static ResponseVO fail(Integer code, String msg, Object data){
        return new ResponseVO().setCode(code).setMsg(msg).setData(data);
    }

    /**
    * @Discription: 返回的数据为空的失败信息
    * @Date: 2022/3/25 12:16 下午
    */
    public static ResponseVO failByNull(Object data){
        return fail(201, "数据为空", data);
    }

    /**
    * @Discription: 请求参数不合法时触发
    * @Date: 2022/3/25 12:17 下午
    */
    public static ResponseVO failByBadRequest(){
        return fail(400, "请求参数不合法", "Bad Request");
    }

    /**
    * @Discription: 资源未找到时触发
    * @param data 返回值内容
    * @Date: 2022/3/25 12:20 下午
    */
    public static ResponseVO failByNotFound(Object data){
        return fail(404, "资源未找到", data);
    }

    /**
    * @Discription: 服务器内部错误
    * @param data 返回值内容
    * @Date: 2022/3/25 1:54 下午
    */
    public static ResponseVO failByInternalServer(Object data){
        return fail(500, "服务器内部错误", data);
    }

    /**
    * @Discription: 不合法的请求方法
    * @param data
    * @Date: 2022/3/25 1:56 下午
    */
    public static ResponseVO failByMethodNotAllowed(Object data){
        return fail(405, "不合法的请求方法", data);
    }
}
