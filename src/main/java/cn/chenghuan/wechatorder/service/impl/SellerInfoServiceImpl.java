package cn.chenghuan.wechatorder.service.impl;

import cn.chenghuan.wechatorder.dao.ISellerInfoDao;
import cn.chenghuan.wechatorder.domain.SellerInfo;
import cn.chenghuan.wechatorder.service.ISellerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenghuan
 * @Description 卖家service
 * @Date 2019/8/14 20:59
 */
@Service
@Transactional(rollbackFor =Exception.class )
@Slf4j
public class SellerInfoServiceImpl implements ISellerInfoService {

    /**
     * 卖家信息DAO
     */
    @Autowired
    private ISellerInfoDao sellerInfoDao;

    /**
     * 保存卖家信息
     * @param sellerInfo
     */
    @Override
    public void saveSellerInfo(final SellerInfo sellerInfo) {
        sellerInfoDao.save(sellerInfo);
    }

    /**
     * 根据openId查找对应的卖家信息
     * @param openId
     * @return
     */
    @Override
    public SellerInfo findByOpenId(final String openId) {
        if(log.isInfoEnabled()){
            log.info("卖家的openId为："+openId);
        }
        return sellerInfoDao.findByOpenId(openId);
    }
}
