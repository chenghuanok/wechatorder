package cn.chenghuan.wechatorder.service.impl;


import cn.chenghuan.wechatorder.domain.ProductCategory;
import cn.chenghuan.wechatorder.service.IProductCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;



/**
 * @author 程欢
 * @Description 产品类目单元测试
 * @Date 2019/6/2 10:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceTest {

    /**
     * 产品类目service
     */
    @Autowired
    private IProductCategoryService productCategoryService;

    /**
     * 查找符合条件的产品类目
     * @return List<ProductCategory>
     */
    @Test
    public void findByCategoryTypeIn() {
        final List<Integer> categoryTypeList = Arrays.asList(1,3);
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        Assert.assertNotEquals(0,productCategoryList.size());
    }
}