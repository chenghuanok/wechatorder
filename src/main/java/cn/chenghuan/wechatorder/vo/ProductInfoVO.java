package cn.chenghuan.wechatorder.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.metadata.ValidateUnwrappedValue;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 程欢
 * @Description 产品信息VO
 * @Date 2019/6/11 21:22
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ProductInfoVO implements Serializable {

    /**
     * 商品GID
     */
    @JsonProperty(value="id")
    private String gid;

    /**
     * 商品名称
     */
    @JsonProperty(value = "name")
    private String productName;

    /**
     * 商品价格
     */
    @JsonProperty(value = "price")
    private BigDecimal productPrice;

    /**
     * 商品描述
     */
    @JsonProperty(value = "description")
    private String productDescription;

    /**
     * 商品图标
     */
    @JsonProperty(value = "icon")
    private String  productIcon;
}


