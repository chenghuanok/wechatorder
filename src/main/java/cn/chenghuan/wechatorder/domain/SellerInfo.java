package cn.chenghuan.wechatorder.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author chenghuan
 * @Description 卖家信息实体
 * @Date 2019/8/14 20:43
 */
@Entity
@Table(name = "seller_info")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class SellerInfo implements Serializable {

    /**
     * 主键GID
     */
    @Id
    @Column(name = "gid",nullable = false,length = 36)
    private String gid;

    /**
     * 用户名
     */
    @Column(name = "username",nullable = false,length = 32)
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password",nullable = false,length = 32)
    private String password;

    /**
     * openId
     */
    @Column(name = "openid",nullable = false,length = 64)
    private String openId;

    /**
     *创建时间
     */
    @Column(name = "create_time",nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time",nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updateTime;
}
