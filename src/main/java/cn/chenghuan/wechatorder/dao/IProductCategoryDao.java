package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 程欢
 * @Description TODO
 * @Date 2019/5/29 22:22
 */
@Repository
public interface IProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    /**
     * 查找符合条件的产品类目
     * @param categoryTypeList
     * @return List<ProductCategory>
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
