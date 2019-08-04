package cn.chenghuan.wechatorder.dto;

import cn.chenghuan.wechatorder.domain.OrderDetail;
import cn.chenghuan.wechatorder.enums.OrderStatusEnum;
import cn.chenghuan.wechatorder.enums.PayStatusEnum;
import cn.chenghuan.wechatorder.utils.DateSerializer;
import cn.chenghuan.wechatorder.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@Setter
@Getter
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

    /**
     * 获取对应code的订单状态枚举
     * @return OrderStatusEnum
     */
    public OrderStatusEnum getOrderStatusEnum(){
           return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    /**
     * 获取对应code的支付状态枚举
     * @return PayStatusEnum
     */
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
