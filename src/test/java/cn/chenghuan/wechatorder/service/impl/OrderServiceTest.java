package cn.chenghuan.wechatorder.service.impl;

import cn.chenghuan.wechatorder.domain.OrderDetail;
import cn.chenghuan.wechatorder.dto.OrderDTO;
import cn.chenghuan.wechatorder.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 程欢
 * @Description 订单测试
 * @Date 2019/7/2 22:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    /**
     * 订单service
     */
    @Autowired
    private IOrderService orderService;

    @Test
    public void createOrder() {
       final OrderDTO orderDTO = new OrderDTO();
       orderDTO.setBuyerName("张三");
       orderDTO.setBuyerPhone("13871465451");
       orderDTO.setBuyerAddress("湖北武汉");
       orderDTO.setBuyerOpenid("888888");
       //购物车
       final List<OrderDetail> orderDetailList = new ArrayList<>();
       final OrderDetail orderDetailOne = new OrderDetail();
       orderDetailOne.setProductId("a951a65659e54090a67c14ee83af8f7e");
       orderDetailOne.setProductQuantity(10);
       orderDetailList.add(orderDetailOne);
       final OrderDetail orderDetailTwo = new OrderDetail();
       orderDetailTwo.setProductId("52e28c64-67a5-4139-96ba-76f8a1686ece");
       orderDetailTwo.setProductQuantity(20);
       orderDetailList.add(orderDetailTwo);
       orderDTO.setOrderDetailList(orderDetailList);
       orderService.createOrder(orderDTO);
    }
}