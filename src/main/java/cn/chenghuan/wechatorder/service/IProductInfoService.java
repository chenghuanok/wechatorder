package cn.chenghuan.wechatorder.service;

import cn.chenghuan.wechatorder.domain.ProductInfo;
import java.util.List;

/**
 * @author 程欢
 * @Description 商品service
 * @Date 2019/6/3 21:50
 */
public interface IProductInfoService {

    /**.
     * 保存商品
     * @param  productInfo
     */
    void saveProductInfo(final ProductInfo productInfo);

    /**
     * 查找对应状态的商品
     * @param productStatus
     * @return List<ProductInfo>
     */
    List<ProductInfo> findByProductStatus(final int productStatus);
}
