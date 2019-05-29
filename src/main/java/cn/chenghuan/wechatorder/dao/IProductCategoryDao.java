package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 程欢
 * @Description TODO
 * @Date 2019/5/29 22:22
 */
@Repository
public interface IProductCategoryDao extends JpaRepository<ProductCategory,String> {
}
