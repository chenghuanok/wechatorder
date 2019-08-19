package cn.chenghuan.wechatorder.exception;

import cn.chenghuan.wechatorder.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @author 程欢
 * @Description 空值异常
 * @Date 2019/7/3 8:51
 */
@Getter
public class EmptyValueException extends RuntimeException {

    /**
     * 异常码
     */
    private Integer code;


    /**
     * 构造方法
     * @param exceptionEnum
     */
    public EmptyValueException(ExceptionEnum exceptionEnum,String exceptionDetail) {
        super(exceptionDetail+exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

    /**
     * 构造方法
     * @param code
     * @param message
     */
    public EmptyValueException(Integer code,String message){
         super(message);
         this.code = code;
    }
}
