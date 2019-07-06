package cn.chenghuan.wechatorder.exception;

import cn.chenghuan.wechatorder.enums.ExceptionEnum;

/**
 * @author 程欢
 * @Description 订单异常
 * @Date 2019/7/6 11:11
 */
public class OrderException extends RuntimeException{
    /**
     * 异常码
     */
    private Integer code;

    /**
     * 构造方法
     * @param exceptionEnum
     */
    public OrderException(ExceptionEnum exceptionEnum, String exceptionDetail) {
        super(exceptionDetail+exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }
}
