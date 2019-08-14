package cn.chenghuan.wechatorder.dao;

import cn.chenghuan.wechatorder.domain.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chenghuan
 * @Description 卖家DAO
 * @Date 2019/8/14 20:54
 */
@Repository
public interface ISellerInfoDao extends JpaRepository<SellerInfo,String> {

    /**
     * 通过openId查找卖家信息
     * @param openId
     * @return SellerInfo
     */
    SellerInfo findByOpenId(final String openId);
}
