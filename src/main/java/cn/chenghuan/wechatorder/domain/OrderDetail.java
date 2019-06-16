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
 * @Description 订单详情
 * @Date 2019/6/16 21:32
 */
@Entity
@Table(name="order_detail")
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class OrderDetail implements Serializable {
    /**
     * 订单GID
     */
    @Id
    @Column(name = "gid",nullable = false,length = 36)
    private String gid;

    /**
     * 订单ID
     */
    @Column(name = "order_id",nullable = false,length = 36)
    private String orderId;

    /**
     * 商品ID
     */
    @Column(name = "product_id",nullable = false,length = 36)
    private String productId;

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
     * 商品数量
     */
    @Column(name = "product_quantity",nullable = false)
    private Integer productQuantity;

    /**
     * 商品图标地址
     */
    @Column(name = "product_icon",nullable = false)
    private String productIcon;

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
