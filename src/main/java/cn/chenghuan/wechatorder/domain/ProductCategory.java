package cn.chenghuan.wechatorder.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 程欢
 * @Description TODO
 * @Date 2019/5/29 22:09
 */
@Entity
@Table(name = "product_category")
@Getter
@Setter
@Accessors(chain = true)
public class ProductCategory implements Serializable {

    /**
     * 主键GID
     */
    @Id
    @GeneratedValue
    @Column(name = "GID",nullable = false)
    private Integer gid;
    /**
     * 类目名字
     */
    @Column(name = "category_name",nullable = false,length = 20)
    private String categoryName;

    /**
     * 类目编号
     */
    @Column(name = "category_type",nullable = false)
    private Integer categoryType;

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
