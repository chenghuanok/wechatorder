package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author 程欢
 * @Description 商品DAO
 * @Date 2019/6/2 19:51
 */
@Repository
public interface IProductInfoDao extends JpaRepository<ProductInfo,String> {

    /**
     * 查找对应状态的商品
     * @param productStatus
     * @return List<ProductInfo>
     */
    List<ProductInfo> findByProductStatus(final Integer productStatus);
}
