package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.OrderMaster;
import cn.chenghuan.wechatorder.enums.OrderStatusEnum;
import cn.chenghuan.wechatorder.enums.PayStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author 程欢
 * @Description 订单DAO
 * @Date 2019/6/17 21:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IOrderMasterDaoTest {

    @Autowired
    private IOrderMasterDao orderMasterDao;

    @Test
    public void saveOrderMaster(){
        final OrderMaster orderMaster = new OrderMaster();
        final Date date = new Date();
        orderMaster.setGid(UUID.randomUUID().toString().replace("-",""));
        orderMaster.setBuyerName("xiaoxu");
        orderMaster.setBuyerPhone("13871465451");
        orderMaster.setBuyerAddress("武汉");
        orderMaster.setBuyerOpenid("111000");
        orderMaster.setOrderAmount(new BigDecimal(2.38));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.UNFINISH.getCode());
        orderMaster.setCreateTime(date);
        orderMaster.setUpdateTime(date);
        orderMasterDao.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        final PageRequest pageRequest = PageRequest.of(0,1);
        final Page<OrderMaster> orderMastersList =  orderMasterDao.findByBuyerOpenid("111000",
                 pageRequest);
        orderMastersList.getContent().forEach(ele->{
            System.out.println(ele.getBuyerName());
        });
    }
}