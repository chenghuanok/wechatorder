package cn.chenghuan.wechatorder.service;

import cn.chenghuan.wechatorder.dto.OrderDTO;

/**
 * @author 程欢
 * @Description 订单service
 * @Date 2019/6/28 22:28
 */
public interface IOrderService {

    /**
     * 创建订单
     * @param orderDTO
     */
    void createOrder(final OrderDTO orderDTO);
}
