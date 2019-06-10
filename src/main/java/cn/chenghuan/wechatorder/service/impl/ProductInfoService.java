package cn.chenghuan.wechatorder.service.impl;

import cn.chenghuan.wechatorder.dao.IProductInfoDao;
import cn.chenghuan.wechatorder.domain.ProductInfo;
import cn.chenghuan.wechatorder.service.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
}
