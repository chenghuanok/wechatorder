package cn.chenghuan.wechatorder.enums;

import lombok.Getter;

/**
 * @author 程欢
 * @Description 支付状态枚举
 * @Date 2019/6/16 21:19
 */
@Getter
public enum PayStatusEnum {

    /**
     * 未支付
     */
    UNFINISH(0,"未支付"),

    /**
     * 已支付
     */
    FINISHED(1,"已支付");

    /**
     * 枚举编号
     */
    private Integer code;

    /**
     * 枚举信息
     */
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
