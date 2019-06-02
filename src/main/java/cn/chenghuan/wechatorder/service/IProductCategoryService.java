package cn.chenghuan.wechatorder.service;

import cn.chenghuan.wechatorder.domain.ProductCategory;
import java.util.List;

/**
 * @author 程欢
 * @Description 商品类别service
 * @Date 2019/6/2 10:29
 */
public interface IProductCategoryService {
    /**
     * 查找符合条件的产品类目
     * @param  categoryTypeList
     * @return List<ProductCategory>
     */
    List<ProductCategory> findByCategoryTypeIn(final List<Integer> categoryTypeList);
}
