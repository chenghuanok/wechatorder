package cn.chenghuan.wechatorder.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 程欢
 * @Description 商品实体
 * @Date 2019/6/2 19:39
 */
@Entity
@Table(name = "product_info")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class ProductInfo implements Serializable {

    /**
     * 主键GID
     */
    @Id
    @Column(name = "gid",nullable = false,length = 36)
    private String gid;

    /**
     * 商品名称
     */
    @Column(name = "product_name",nullable = false,length = 64)
    private String productName;

    /**
     * 商品价格
     */
    @Column(name = "product_price",nullable = false)
    private BigDecimal productPrice;

    /**
     * 库存
     */
    @Column(name = "product_stock",nullable = false)
    private Integer productStock;

    /**
     * 商品描述
     */
    @Column(name = "product_description",length = 64)
    private String productDescription;

    /**
     * 商品图标
     */
    @Column(name = "product_icon",length = 512)
    private String productIcon;

    /**
     * 商品状态
     */
    @Column(name = "product_status")
    private Integer productStatus;

    /**
     * 产品类目
     */
    @Column(name = "category_type",nullable = false)
    private Integer categoryType;

    /**
     *创建时间
     */
    @Column(name = "create_time",nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time",nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updateTime;

}
