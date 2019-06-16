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
 * @Description 订单
 * @Date 2019/6/16 21:05
 */
@Entity
@Table(name="order_master")
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class OrderMaster implements Serializable {
    /**
     * 订单GID
     */
    @Id
    @Column(name = "gid",nullable = false,length = 36)
    private String gid;

    /**
     * 买家名字
     */
    @Column(name = "buyer_name",nullable = false,length = 32)
    private String buyerName;

    /**
     * 买家电话
     */
    @Column(name = "buyer_phone",nullable = false,length = 32)
    private String buyerPhone;

    /**
     * 买家地址
     */
    @Column(name = "buyer_address",nullable = false,length = 128)
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    @Column(name = "buyer_openid",nullable = false,length = 64)
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    @Column(name = "order_amount",nullable = false)
    private BigDecimal orderAmount;

    /**
     * 订单状态
     */
    @Column(name = "order_status",nullable = false)
    private Integer orderStatus;

    /**
     * 支付状态
     */
    @Column(name = "pay_status",nullable = false)
    private Integer payStatus;

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
