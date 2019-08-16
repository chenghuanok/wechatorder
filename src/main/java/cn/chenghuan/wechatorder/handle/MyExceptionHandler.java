package cn.chenghuan.wechatorder.handle;

import cn.chenghuan.wechatorder.exception.EmptyValueException;
import cn.chenghuan.wechatorder.utils.ResultVOUtil;
import cn.chenghuan.wechatorder.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenghuan
 * @Description 全局异常处理
 * @Date 2019/8/16 22:19
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(EmptyValueException.class)
    public ResultVO handleException(Exception e){
        return ResultVOUtil.fail(123);
    }
}
