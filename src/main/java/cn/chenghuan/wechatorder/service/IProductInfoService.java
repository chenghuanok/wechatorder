package cn.chenghuan.wechatorder.service;

import cn.chenghuan.wechatorder.domain.ProductInfo;
import cn.chenghuan.wechatorder.dto.CartDTO;

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

    /**
     * 根据商品Id查询商品
     * @param productIds
     * @return List<ProductInfo>
     */
    List<ProductInfo> findByIds(final List<String> productIds);

    /**
     * 扣减库存
     * @param cartDTOS
     */
    void decreaseProductStock(final List<CartDTO> cartDTOS);
}
