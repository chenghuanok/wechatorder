package cn.chenghuan.wechatorder.controller;

import cn.chenghuan.wechatorder.vo.ProductInfoVO;
import cn.chenghuan.wechatorder.vo.ProductVO;
import cn.chenghuan.wechatorder.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author 程欢
 * @Description 商品controller
 * @Date 2019/6/11 20:08
 */
@RestController
@RequestMapping("/buyer/product")
public class ProductController {

    @GetMapping("/list")
    public ResultVO list(){
        final ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        final ProductVO productVO = new ProductVO();
        final ProductInfoVO productInfoVO = new ProductInfoVO();
        productVO.setCategoryName("鞋类");
        productVO.setCategoryType(2);
        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        resultVO.setData(Arrays.asList(productVO));
        return resultVO;
    }
}
