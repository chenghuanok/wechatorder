package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.ProductInfo;
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
 * @Date 2019/6/2 19:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductInfoDaoTest {

    /**
     * 商品DAO
     */
    @Autowired
    private IProductInfoDao productInfoDao;

    @Test
    public void saveProductInfoTest(){
         final ProductInfo productInfo = new ProductInfo();
         final Date date = new Date();
         System.out.println(UUID.randomUUID().toString());
         productInfo.setGid(UUID.randomUUID().toString());
         productInfo.setProductName("NIKE");
         productInfo.setProductPrice(new BigDecimal(555.82));
         productInfo.setProductStock(200);
         productInfo.setProductDescription("超级好看的运动鞋。");
         productInfo.setProductIcon("https://www.baidu.com/");
         productInfo.setProductStatus(0);
         productInfo.setCategoryType(2);
         productInfo.setCreateTime(date);
         productInfo.setUpdateTime(date);
         productInfoDao.save(productInfo);
    }

    @Test
    public void findByProductStatus() {
        final List<ProductInfo> productInfoList = productInfoDao.findByProductStatus(0);
        productInfoList.forEach(ele->
                System.out.println(ele.getProductName())
        );
    }
}