package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

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

    /**
     * 查询订单详情
     * @param gid
     * @return
     */
    OrderMaster findByGid(final String gid);

    /**
     * 修改订单状态
     * @param orderStatus
     * @param gid
     * @param updateTime
     * @return int
     */
    @Modifying
    @Query(value = "update OrderMaster om set om.orderStatus=:orderStatus,om.updateTime=:updateTime where om.gid=:gid")
    int updateOrderStatus(@Param(value = "orderStatus") final Integer orderStatus,
                          @Param(value = "gid") final String gid,
                          @Param(value = "updateTime") final Date updateTime);
}
