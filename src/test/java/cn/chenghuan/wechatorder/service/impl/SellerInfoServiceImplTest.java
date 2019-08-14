package cn.chenghuan.wechatorder.service.impl;

import cn.chenghuan.wechatorder.domain.SellerInfo;
import cn.chenghuan.wechatorder.service.ISellerInfoService;
import cn.chenghuan.wechatorder.utils.UuidUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author chenghuan
 * @Description 卖家信息service test
 * @Date 2019/8/14 21:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    /**
     * 卖家service
     */
    @Autowired
    private ISellerInfoService sellerInfoService;

    @Test
    public void saveSellerInfo() {
      final SellerInfo sellerInfo = new SellerInfo();
      final Date date = new Date();
      sellerInfo.setGid(UuidUtils.createUUID());
      sellerInfo.setUserName("chenghuan");
      sellerInfo.setPassword("123");
      sellerInfo.setOpenId("88888888");
      sellerInfo.setCreateTime(date);
      sellerInfo.setUpdateTime(date);
      sellerInfoService.saveSellerInfo(sellerInfo);
    }
}