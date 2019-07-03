package cn.chenghuan.wechatorder.exception;

import cn.chenghuan.wechatorder.enums.ExceptionEnum;

/**
 * @author 程欢
 * @Description 数量异常
 * @Date 2019/7/3 9:57
 */
public class AmountException extends RuntimeException {

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 构造方法
     * @param exceptionEnum
     */
    public AmountException(ExceptionEnum exceptionEnum, String exceptionDetail) {
        super(exceptionDetail+exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }
}
