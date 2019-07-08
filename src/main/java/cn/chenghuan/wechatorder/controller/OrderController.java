package cn.chenghuan.wechatorder.controller;

import cn.chenghuan.wechatorder.enums.ExceptionEnum;
import cn.chenghuan.wechatorder.exception.EmptyValueException;
import cn.chenghuan.wechatorder.vo.OrderVO;
import cn.chenghuan.wechatorder.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author 程欢
 * @Description 订单controller
 * @Date 2019/7/7 20:11
 */
@RestController
@RequestMapping("/buyer/order/create")
public class OrderController {


    /**
     * 创建订单
     * @param orderVO
     * @param bindingResult
     * @return ResultVO<Map<String,String>>
     */
    @PostMapping
    public ResultVO<Map<String,String>> create(@Valid final OrderVO orderVO, final BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
           throw new EmptyValueException(ExceptionEnum.EMPTY_VALUE.getCode(),
                   bindingResult.getFieldError().getDefaultMessage());
        }
        return null;
    }
}
