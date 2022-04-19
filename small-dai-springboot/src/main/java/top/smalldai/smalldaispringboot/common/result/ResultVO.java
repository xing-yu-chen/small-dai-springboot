package top.smalldai.smalldaispringboot.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import top.smalldai.smalldaispringboot.common.constant.ResultConstant;

import java.io.Serializable;

/**
 * @Author: xing-yu-chen
 * @Project: small-dai-springboot
 * @Description: 消息返回信息VO
 * @Data:Created in 2022/3/25 10:23 上午
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "消息返回信息VO")
public class ResultVO implements Serializable {

    @ApiModelProperty(value = "状态码", name = "code")
    private Integer code;
    @ApiModelProperty(value = "提示信息", name = "msg")
    private String msg;
    @ApiModelProperty(value = "返回值内容", name = "data")
    private Object data;

    /**
     * @Author: xingyuchen
     * @Discription: 返回成功信息的底层封装
     * @param code 状态码
     * @param msg 提示信息
     * @param data 返回值内容
     * @Date: 2022/3/25 10:49 上午
    */
    public static ResultVO success(Integer code, String msg, Object data){
        return new ResultVO().setCode(code).setMsg(msg).setData(data);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 请求成功的封装
     * @param msg 提示信息
     * @param data 返回值内容
     * @Date: 2022/3/25 10:50 上午
    */
    public static ResultVO success(String msg, Object data){
        return success(ResultConstant.SUCCESS_CODE, msg, data);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 返回失败信息的封装
     * @param code 状态码
     * @param msg 提示信息
     * @param data 返回值内容
     * @Date: 2022/3/25 10:51 上午
    */
    public static ResultVO fail(Integer code, String msg, Object data){
        return new ResultVO().setCode(code).setMsg(msg).setData(data);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 返回的数据为空的失败信息
     * @Date: 2022/3/25 12:16 下午
    */
    public static ResultVO failByNull(Object data){
        return new ResultVO().setCode(ResultConstant.CREATED_CODE).setMsg(ResultConstant.CREATED_MSG).setData(data);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 请求参数不合法时触发
     * @Date: 2022/3/25 12:17 下午
    */
    public static ResultVO failByBadRequest(){
        return new ResultVO().setCode(ResultConstant.BAD_REQUEST_CODE).setMsg(ResultConstant.BAD_REQUEST_MSG).setData("Bad Request!");
    }

    /**
     * @Author: xingyuchen
     * @Discription: 资源未找到时触发
     * @param data 返回值内容
     * @Date: 2022/3/25 12:20 下午
    */
    public static ResultVO failByNotFound(Object data){
        return new ResultVO().setCode(ResultConstant.NOT_FOUND_CODE).setMsg(ResultConstant.NOT_FOUND_MSG).setData(data);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 服务器内部错误
     * @param data 返回值内容
     * @Date: 2022/3/25 1:54 下午
    */
    public static ResultVO failByInternalServer(Object data){
        return new ResultVO().setCode(ResultConstant.INTERNAL_SERVER_CODE).setMsg(ResultConstant.INTERNAL_SERVER_MSG).setData(data);
    }

    /**
     * @Author: xingyuchen
     * @Discription: 不合法的请求方法
     * @param data
     * @Date: 2022/3/25 1:56 下午
    */
    public static ResultVO failByMethodNotAllowed(Object data){
        return new ResultVO().setCode(ResultConstant.METHOD_NOT_ALLOWED_CODE).setMsg(ResultConstant.METHOD_FOUND_ALLOWED_MSG).setData(data);
    }
}
