package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 程欢
 * @Description 订单DAO
 * @Date 2019/6/16 21:45
 */
@Repository
public interface IOrderMasterDao extends JpaRepository<OrderMaster,String> {

    /**
     * 根据买家微信OPENID查询对应的订单
     * @param buyerOpenid
     * @param pageable
     * @return  Page<OrderMaster>
     */
    Page<OrderMaster> findByBuyerOpenid(final String buyerOpenid, final Pageable pageable);
}
