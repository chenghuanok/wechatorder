package cn.chenghuan.wechatorder.enums;

import lombok.Getter;

/**
 * @author 程欢
 * @Description 商品状态枚举
 * @Date 2019/6/10 14:28
 */
@Getter
public enum ProductInfoStatusEnum {
    /**
     * 正常
     */
    UP(0,"正常"),

    DOWN(1,"下架");

    /**
     * 枚举编号
     */
    private Integer code;
    /**
     * 枚举信息
     */
    private String message;

    ProductInfoStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
