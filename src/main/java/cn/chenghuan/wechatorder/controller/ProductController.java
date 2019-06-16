package cn.chenghuan.wechatorder.controller;

import cn.chenghuan.wechatorder.domain.ProductCategory;
import cn.chenghuan.wechatorder.domain.ProductInfo;
import cn.chenghuan.wechatorder.enums.ProductInfoStatusEnum;
import cn.chenghuan.wechatorder.service.IProductCategoryService;
import cn.chenghuan.wechatorder.service.IProductInfoService;
import cn.chenghuan.wechatorder.utils.ResultVOUtil;
import cn.chenghuan.wechatorder.vo.ProductInfoVO;
import cn.chenghuan.wechatorder.vo.ProductVO;
import cn.chenghuan.wechatorder.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 程欢
 * @Description 商品controller
 * @Date 2019/6/11 20:08
 */
@RestController
@RequestMapping("/buyer/product")
public class ProductController {

    /**
     * 商品service
     */
    @Autowired
    private IProductInfoService productInfoService;

    /**
     * 商品类目service
     */
    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //查找出所有的上架商品
        final List<ProductInfo> productInfoList = productInfoService.findByProductStatus
                (ProductInfoStatusEnum.UP.getCode());
        final List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).
                collect(Collectors.toList());
        //查找出对应的商品类目
        final List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        final List<ProductVO> productVOList = buildProductVOList(productInfoList,productCategoryList);
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 构造List<ProductVO>
     * @param productInfoList
     * @param productCategoryList
     * @return List<ProductVO>
     */
    private List<ProductVO> buildProductVOList(final List<ProductInfo> productInfoList,
                                               final List<ProductCategory> productCategoryList){
        final List<ProductVO> productVOList = new ArrayList<>();
        productCategoryList.forEach(productCategory->{
            final ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            final List<ProductInfoVO> productInfoVOLIst = buildProductInfoVOList(productInfoList,productCategory);
            productVO.setProductInfoVOList(productInfoVOLIst);
            productVOList.add(productVO);
        });
        return productVOList;
    }

    /**
     * 构造商品信息VOList
     * @param productInfoList
     * @param productCategory
     * @return  List<ProductInfoVO>
     */
    private  List<ProductInfoVO> buildProductInfoVOList(final List<ProductInfo> productInfoList,
                                                        final ProductCategory productCategory){
        final List<ProductInfoVO> productInfoVOLIst = new ArrayList<>();
        productInfoList.forEach(productInfo->{
            if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                final ProductInfoVO productInfoVO = new ProductInfoVO();
                //属性复制
                productInfoVO.setGid(productInfo.getGid()).setProductName(productInfo.getProductName())
                        .setProductPrice(productInfo.getProductPrice()).setProductDescription
                        (productInfo.getProductDescription()).
                        setProductIcon(productInfo.getProductIcon());
                productInfoVOLIst.add(productInfoVO);
            }
        });
        return productInfoVOLIst;
    }
}
