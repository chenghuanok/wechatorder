package cn.chenghuan.wechatorder.service.impl;

import cn.chenghuan.wechatorder.domain.ProductInfo;
import cn.chenghuan.wechatorder.enums.ProductInfoStatusEnum;
import cn.chenghuan.wechatorder.service.IProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 程欢
 * @Description TODO
 * @Date 2019/6/10 15:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceTest {


    /**
     * 商品信息service
     */
    @Autowired
   private IProductInfoService productInfoService;

    @Test
    public void saveProductInfo() {
        final ProductInfo productInfo = new ProductInfo();
        final Date date = new Date();
        productInfo.setGid(UUID.randomUUID().toString().replace("-",""));
        productInfo.setProductName("李宁男士服装");
        productInfo.setProductPrice(new BigDecimal(666.82));
        productInfo.setProductStock(300);
        productInfo.setProductDescription("超级好看的李宁男士休闲服");
        productInfo.setProductIcon("https://www.baidu.com/");
        productInfo.setProductStatus(ProductInfoStatusEnum.UP.getCode());
        productInfo.setCategoryType(3);
        productInfo.setCreateTime(date);
        productInfo.setUpdateTime(date);
        productInfoService.saveProductInfo(productInfo);
    }


    @Test
    public void findByProductStatus() {
       final List<ProductInfo> productInfoList =  productInfoService.
               findByProductStatus(ProductInfoStatusEnum.DOWN.getCode());
        Assert.assertNotNull(productInfoList);
    }
}