package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 程欢
 * @Description 订单详情测试类
 * @Date 2019/6/17 22:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IOrderDetailDaoTest {

    @Autowired
    private IOrderDetailDao orderDetailDao;

    @Test
    public void saveOrderDetail(){
       final OrderDetail orderDetail = new OrderDetail();
       final Date date = new Date();
       orderDetail.setGid(UUID.randomUUID().toString().replace("-",""));
       orderDetail.setOrderId("123");
       orderDetail.setProductId("456");
       orderDetail.setProductName("鞋子");
       orderDetail.setProductPrice(new BigDecimal(2.68));
       orderDetail.setProductQuantity(100);
       orderDetail.setProductIcon("123");
       orderDetail.setCreateTime(date);
       orderDetail.setUpdateTime(date);
       orderDetailDao.save(orderDetail);
    }

    @Test
    public void findByOrderId() {
      final List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId("123");
      Assert.assertNotNull(orderDetailList);
    }
}