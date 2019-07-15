package cn.chenghuan.wechatorder.controller;

import cn.chenghuan.wechatorder.domain.OrderDetail;
import cn.chenghuan.wechatorder.dto.OrderDTO;
import cn.chenghuan.wechatorder.enums.ExceptionEnum;
import cn.chenghuan.wechatorder.exception.EmptyValueException;
import cn.chenghuan.wechatorder.service.IOrderService;
import cn.chenghuan.wechatorder.utils.ResultVOUtil;
import cn.chenghuan.wechatorder.vo.OrderVO;
import cn.chenghuan.wechatorder.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 程欢
 * @Description 订单controller
 * @Date 2019/7/7 20:11
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class OrderController {

    /**
     * 订单service
     */
    @Autowired
    private IOrderService orderService;


    /**
     * 创建订单
     * @param orderVO
     * @param bindingResult
     * @return ResultVO<Map<String,String>>
     */
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid  @RequestBody  final OrderVO orderVO,
                                               final BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
           throw new EmptyValueException(ExceptionEnum.EMPTY_VALUE.getCode(),
                   bindingResult.getFieldError().getDefaultMessage());
        }
        final OrderDTO orderDTO = orderVOTransferOrderDTO(orderVO);
        final String orderId = orderService.createOrder(orderDTO);
        final Map<String,String> orderIdMap = new HashMap<>(1);
        orderIdMap.put("orderId",orderId);
        return ResultVOUtil.success(orderIdMap);
    }

    /**
     * 完成订单
     * @param orderId
     */
    @PostMapping("/finishOrder")
    public void finishOrder(@RequestParam(value = "orderId",required = true) final String orderId){
          log.info(orderId);
          orderService.finishOrder(orderId);
    }

    /**
     * 取消订单
     * @param orderId
     */
    @PostMapping("/cancelOrder")
    public void cancelOrder(@RequestParam(value = "orderId",required = true) final String orderId){
        log.info(orderId);
        orderService.cancelOrder(orderId);
    }

    /**
     * orderVO转orderDTO
     * @param orderVO
     * @return OrderDTO
     */
    private OrderDTO orderVOTransferOrderDTO(final OrderVO orderVO){
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderVO.getName());
        orderDTO.setBuyerPhone(orderVO.getPhone());
        orderDTO.setBuyerOpenid(orderVO.getOpenid());
        orderDTO.setBuyerAddress(orderVO.getAddress());
        final List<OrderDetail> orderDetailList = JSON.parseArray(orderVO.getItems(),OrderDetail.class);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
