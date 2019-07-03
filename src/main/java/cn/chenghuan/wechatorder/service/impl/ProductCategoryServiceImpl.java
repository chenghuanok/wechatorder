package cn.chenghuan.wechatorder.service.impl;


import cn.chenghuan.wechatorder.annotation.MethodExecuteTime;
import cn.chenghuan.wechatorder.dao.IProductCategoryDao;
import cn.chenghuan.wechatorder.domain.ProductCategory;
import cn.chenghuan.wechatorder.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @author 程欢
 * @Description 商品类别service
 * @Date 2019/6/2 10:30
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    /**
     * 商品目录DAO
     */
    @Autowired
    private IProductCategoryDao productCategoryDao;

    /**
     * 查找符合条件的产品类目
     * @param  categoryTypeList
     * @return List<ProductCategory>
     */
    @Override
    public List<ProductCategory> findByCategoryTypeIn(final List<Integer> categoryTypeList) {
        List<ProductCategory> productCategoryList = productCategoryDao.findByCategoryTypeIn(categoryTypeList);
        return productCategoryList;
    }
}
