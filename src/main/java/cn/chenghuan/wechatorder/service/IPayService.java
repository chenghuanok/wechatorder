package cn.chenghuan.wechatorder.service;

/**
 * @author 程欢
 * @Description 支付接口
 * @Date 2019/7/16 22:46
 */
public interface IPayService {

    /**
     * 支付订单
     * @param orderId
     * @return String
     */
    String payOrder(final String orderId);
}
