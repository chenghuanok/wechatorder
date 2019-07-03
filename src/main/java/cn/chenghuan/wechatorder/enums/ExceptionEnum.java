package cn.chenghuan.wechatorder.enums;

import lombok.Getter;

/**
 * @author 程欢
 * @Description 异常枚举
 * @Date 2019/7/3 8:36
 */
@Getter
public enum ExceptionEnum {

    /**
     * 值不存在
     */
    EMPTY_VALUE(0,"为空"),

    AMOUNT_NO_ENOUGH(1,"数量不足"),
    ;


    /**
     * 枚举编号
     */
    private Integer code;

    /**
     * 枚举信息
     */
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
