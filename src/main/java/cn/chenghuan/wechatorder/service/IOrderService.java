package cn.chenghuan.wechatorder.service;

import cn.chenghuan.wechatorder.domain.OrderMaster;
import cn.chenghuan.wechatorder.dto.OrderDTO;
import org.springframework.data.domain.Page;

/**
 * @author 程欢
 * @Description 订单service
 * @Date 2019/6/28 22:28
 */
public interface IOrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @Return String
     */
    String createOrder(final OrderDTO orderDTO);

    /**
     * 根据订单GID查找订单
     * @param orderId
     * @return OrderDTO
     */
    OrderDTO findOne(final String orderId);

    /**
     * 分页查找对应用户的订单
     * @param buyerOpenid
     * @return  Page<OrderMaster>
     */
    Page<OrderMaster> findListByBuyerOpenid(final String buyerOpenid);

    /**
     * 取消订单
     * @param orderId
     */
    void cancelOrder(final String orderId);

    /**
     * 完成订单
     * @param orderId
     */
    void finishOrder(final String orderId);

    /**
     * 支付订单
     * @param orderId
     */
    void payOrder(final String orderId);
}
