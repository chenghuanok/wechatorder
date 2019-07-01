package cn.chenghuan.wechatorder.dto;

import cn.chenghuan.wechatorder.domain.OrderDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 程欢
 * @Description 订单DTO
 * @Date 2019/6/28 22:30
 */
@Getter
@Setter
@ToString
public class OrderDTO implements Serializable {
    /**
     * 订单GID
     */
    private String gid;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 支付状态
     */
    private Integer payStatus;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 订单详情list
     */
    private List<OrderDetail> orderDetailList;
}
