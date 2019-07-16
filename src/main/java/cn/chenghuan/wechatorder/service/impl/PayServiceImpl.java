package cn.chenghuan.wechatorder.service.impl;

import cn.chenghuan.wechatorder.service.IPayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 程欢
 * @Description 支付service
 * @Date 2019/7/16 22:54
 */
@Service
@Transactional(rollbackFor =Exception.class )
public class PayServiceImpl implements IPayService {

    /**
     * 支付订单
     * @param orderId
     * @return String
     */
    @Override
    public String payOrder(final String orderId) {
        return null;
    }
}
