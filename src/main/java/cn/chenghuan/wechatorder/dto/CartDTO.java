package cn.chenghuan.wechatorder.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

/**
 * @author 程欢
 * @Description 购物车DTO
 * @Date 2019/7/1 22:39
 */
@Getter
@Setter
@ToString
public class CartDTO implements Serializable {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
