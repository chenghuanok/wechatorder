package cn.chenghuan.wechatorder.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

/**
 * @author 程欢
 * @Description 商品VO
 * @Date 2019/6/11 20:25
 */
@Getter
@Setter
@ToString
public class ProductVO implements Serializable {

    /**
     * 商品类目名称
     */
    @JsonProperty(value="name")
    private String categoryName;

    /**
     * 商品类目
     */
    @JsonProperty(value = "type")
    private Integer categoryType;

    /**
     * 商品详情
     */
    @JsonProperty(value = "foods")
    private List<ProductInfoVO> productInfoVOList;
}
