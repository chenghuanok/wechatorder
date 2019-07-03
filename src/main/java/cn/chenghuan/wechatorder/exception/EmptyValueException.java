package cn.chenghuan.wechatorder.exception;

import cn.chenghuan.wechatorder.enums.ExceptionEnum;

/**
 * @author 程欢
 * @Description TODO
 * @Date 2019/7/3 8:51
 */
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
}
