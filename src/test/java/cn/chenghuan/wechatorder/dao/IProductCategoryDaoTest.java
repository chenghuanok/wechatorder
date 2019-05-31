package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        productCategory.setCategoryName("服饰");
        productCategory.setCategoryType(3);
        productCategory.setCreateTime(date);
        productCategory.setUpdateTime(date);
        productCategoryDao.save(productCategory);
    }

    @Test
    public void getProductCategoryList(){
       final List<Integer> categoryTypeList = Arrays.asList(1,3);
       final List<ProductCategory> productCategoryList = productCategoryDao.findByCategoryTypeIn(categoryTypeList);
       productCategoryList.forEach(ele->
           System.out.println("类目名称："+ele.getCategoryName())
       );
    }
}