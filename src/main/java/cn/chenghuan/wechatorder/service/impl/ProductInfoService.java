package cn.chenghuan.wechatorder.service.impl;

import cn.chenghuan.wechatorder.dao.IProductInfoDao;
import cn.chenghuan.wechatorder.domain.ProductInfo;
import cn.chenghuan.wechatorder.dto.CartDTO;
import cn.chenghuan.wechatorder.service.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 程欢
 * @Description 商品service
 * @Date 2019/6/3 21:51
 */
@Service
public class ProductInfoService implements IProductInfoService {

    /**
     * 商品DAO
     */
    @Autowired
    private IProductInfoDao productInfoDao;

    /**
     * .
     * 保存商品
     */
    @Override
    public void saveProductInfo(final ProductInfo productInfo) {
        productInfoDao.save(productInfo);
    }

    /**
     * 查找对应状态的商品
     *
     * @param productStatus
     * @return List<ProductInfo>
     */
    @Override
    public List<ProductInfo> findByProductStatus(final int productStatus) {
        return productInfoDao.findByProductStatus(productStatus);
    }

    /**
     * 根据商品Id查询商品
     *
     * @param productIds
     * @return List<ProductInfo>
     */
    @Override
    public List<ProductInfo> findByIds(List<String> productIds) {
        return productInfoDao.findByGidIn(productIds);
    }

    /**
     * 扣减库存
     *
     * @param cartDTOS
     */
    @Override
    public void decreaseProductStock(final List<CartDTO> cartDTOS) {
       final List<String> productGids = cartDTOS.stream().map(CartDTO::getProductId).collect(Collectors.toList());
       //商品gid和商品数量对应
       final Map<String,Integer> productIdAndAmountMap = new HashMap<>(cartDTOS.size());
       cartDTOS.forEach(ele->
           productIdAndAmountMap.put(ele.getProductId(),ele.getProductQuantity())
       );
       final List<ProductInfo> productInfoList = findByIds(productGids);
       final List<ProductInfo> newProductInfoList = new ArrayList<>();
       for (int i = 0; i <productInfoList.size() ; i++) {
           Integer productStock = productInfoList.get(i).getProductStock()-
                   productIdAndAmountMap.get(productInfoList.get(i).getGid());
           productInfoList.get(i).setProductStock(productStock);
           newProductInfoList.add(productInfoList.get(i));
       }
       productInfoDao.saveAll(newProductInfoList);
    }
}
