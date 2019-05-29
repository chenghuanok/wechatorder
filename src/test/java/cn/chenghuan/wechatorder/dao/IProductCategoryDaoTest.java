package cn.chenghuan.wechatorder.dao;


import cn.chenghuan.wechatorder.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author 程欢
 * @Description 产品类目测试类
 * @Date 2019/5/29 22:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IProductCategoryDaoTest {

    /**
     * 产品类目DAO
     */
    @Autowired
    private IProductCategoryDao productCategoryDao;


    @Test
    public void saveProductCateGory(){
        final ProductCategory productCategory=new ProductCategory();
        final Date date=new Date();
        productCategory.setCategoryName("热销产品");
        productCategory.setCategoryType(1);
        productCategory.setCreateTime(date);
        productCategory.setUpdateTime(date);
        productCategoryDao.save(productCategory);
    }
}