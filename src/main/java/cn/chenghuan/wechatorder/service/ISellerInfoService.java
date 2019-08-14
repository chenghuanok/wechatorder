package cn.chenghuan.wechatorder.service;

import cn.chenghuan.wechatorder.domain.SellerInfo;

/**
 * @author chenghuan
 * @Description 卖家service
 * @Date 2019/8/14 20:58
 */
public interface ISellerInfoService {

    /**
     * 保存卖家信息
     * @param sellerInfo
     */
    void saveSellerInfo(final SellerInfo sellerInfo);

    /**
     * 根据openId查找对应的卖家信息
     * @param openId
     * @return
     */
    SellerInfo findByOpenId(final String openId);
}
