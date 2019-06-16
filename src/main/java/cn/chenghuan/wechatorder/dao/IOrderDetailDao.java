package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author 程欢
 * @Description 订单详情DAO
 * @Date 2019/6/16 21:45
 */
@Repository
public interface IOrderDetailDao extends JpaRepository<OrderDetail,String> {

    /**
     * 根据订单ID查询订单详情
     * @param orderId
     * @return List<OrderDetail>
     */
    List<OrderDetail> findByOrderId(final String orderId);
}

