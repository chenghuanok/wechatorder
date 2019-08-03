package cn.chenghuan.wechatorder.enums;

import lombok.Getter;

/**
 * @author 程欢
 * @Description 订单状态枚举
 * @Date 2019/6/16 21:19
 */
@Getter
public enum OrderStatusEnum implements CodeEnum<Integer> {

    /**
     * 新下单
     */
    NEW(0,"新下单"),

    /**
     * 完成
     */
    FINISH(1,"完成"),

    /**
     * 取消
     */
    CANCEL(2,"取消");

    /**
     * 枚举编号
     */
    private Integer code;
    /**
     * 枚举信息
     */
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
