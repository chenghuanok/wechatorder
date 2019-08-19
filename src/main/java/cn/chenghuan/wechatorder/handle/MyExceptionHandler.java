package cn.chenghuan.wechatorder.handle;

import cn.chenghuan.wechatorder.exception.EmptyValueException;
import cn.chenghuan.wechatorder.utils.ResultVOUtil;
import cn.chenghuan.wechatorder.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenghuan
 * @Description 全局异常处理
 * @Date 2019/8/16 22:19
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVO handleException(Exception e){
        log.info(e.getMessage(),e);
        if(e instanceof EmptyValueException){
            final EmptyValueException emptyValueException = (EmptyValueException) e;
            return ResultVOUtil.fail(emptyValueException.getCode(),emptyValueException.getMessage());
        }
        return ResultVOUtil.fail(1,e.getMessage());
    }
}
