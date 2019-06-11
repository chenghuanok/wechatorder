package cn.chenghuan.wechatorder.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 程欢
 * @Description 返回结果VO
 * @Date 2019/6/11 20:09
 */
@Getter
@Setter
@ToString
public class ResultVO<T> implements Serializable {
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回具体信息
     */
    private T data;
}
