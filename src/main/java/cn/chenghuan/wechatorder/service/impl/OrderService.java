package cn.chenghuan.wechatorder.service.impl;

import cn.chenghuan.wechatorder.dao.IOrderDetailDao;
import cn.chenghuan.wechatorder.dao.IOrderMasterDao;
import cn.chenghuan.wechatorder.dao.IProductInfoDao;
import cn.chenghuan.wechatorder.domain.OrderDetail;
import cn.chenghuan.wechatorder.domain.OrderMaster;
import cn.chenghuan.wechatorder.domain.ProductInfo;
import cn.chenghuan.wechatorder.dto.CartDTO;
import cn.chenghuan.wechatorder.dto.OrderDTO;
import cn.chenghuan.wechatorder.enums.OrderStatusEnum;
import cn.chenghuan.wechatorder.enums.PayStatusEnum;
import cn.chenghuan.wechatorder.service.IOrderService;
import cn.chenghuan.wechatorder.service.IProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 程欢
 * @Description 订单service
 * @Date 2019/7/1 17:25
 */
@Service
@Transactional
public class OrderService implements IOrderService {

    /**
     * 商品service
     */
    @Autowired
    private IProductInfoService productInfoService;

    /**
     * 订单DAO
     */
    @Autowired
    private IOrderMasterDao orderMasterDao;

    /**
     * 订单详情DAO
     */
    @Autowired
    private IOrderDetailDao orderDetailDao;

    /**
     * 商品DAO
     */
    @Autowired
    private IProductInfoDao productInfoDao;

    /**
     * 创建订单
     * @param orderDTO
     */
    @Override
    public void createOrder(final OrderDTO orderDTO) {
        //1、构建订单实体
        final OrderMaster orderMaster = buildOrderMaster(orderDTO);
        //2.构建订单详情
        final List<OrderDetail> orderDetailList = buildOrderDetailList(orderDTO.getOrderDetailList(),
                orderMaster.getGid());
        //3、扣减商品库存
        final List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(ele->
            new CartDTO(ele.getProductId(),ele.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseProductStock(cartDTOList);
        //4.保存订单
        orderMasterDao.save(orderMaster);
        orderDetailDao.saveAll(orderDetailList);
    }

    /**
     * 构建订单实体
     * @param orderDTO
     * @return OrderMaster
     */
    private OrderMaster buildOrderMaster(final OrderDTO orderDTO){
        final OrderMaster orderMaster = new OrderMaster();
        final Date date = new Date();
        orderMaster.setGid(UUID.randomUUID().toString().replace("-",""));
        orderMaster.setBuyerName(orderDTO.getBuyerName());
        orderMaster.setBuyerPhone(orderDTO.getBuyerPhone());
        orderMaster.setBuyerAddress(orderDTO.getBuyerAddress());
        orderMaster.setBuyerOpenid(orderDTO.getBuyerOpenid());
        orderMaster.setOrderAmount(calculateOrderAmount(orderDTO.getOrderDetailList()));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.UNFINISH.getCode());
        orderMaster.setCreateTime(date);
        orderMaster.setUpdateTime(date);
        return orderMaster;
    }

    /**
     * 计算订单总金额
     * @param orderDetailList
     * @return BigDecimal
     */
    private BigDecimal calculateOrderAmount(final List<OrderDetail> orderDetailList){
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        final List<String> gidList = orderDetailList.stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        //商品gid和商品数量对应
        final Map<String,Integer> productIdAndAmountMap = new HashMap<>(orderDetailList.size());
        orderDetailList.forEach(ele->
            productIdAndAmountMap.put(ele.getProductId(),ele.getProductQuantity())
        );
        //查询对应id的商品信息
        final List<ProductInfo> productInfoList = productInfoService.findByIds(gidList);
        for(int i = 0;i<productInfoList.size();i++){
           orderAmount = productInfoList.get(i).getProductPrice().
                    multiply(new BigDecimal(productIdAndAmountMap.get(productInfoList.get(i).getGid())
                    )).add(orderAmount);
        }
        return  orderAmount;
    }

    /**
     * 构建订单详情
     * @param orderDetailList
     * @param orderId
     * @return OrderDetail
     */
    private List<OrderDetail> buildOrderDetailList(final List<OrderDetail> orderDetailList,final String orderId){
        final List<OrderDetail> orderDetails = new ArrayList<>();
        final List<String> gidLists = orderDetailList.stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        //商品gid和商品数量对应
        final Map<String,Integer> productIdAndAmountMap = new HashMap<>(orderDetailList.size());
        orderDetailList.forEach(ele->
                productIdAndAmountMap.put(ele.getProductId(),ele.getProductQuantity())
        );
        final List<ProductInfo> productInfoList = productInfoDao.findByGidIn(gidLists);
        for (int i = 0; i < orderDetailList.size(); i++) {
            final Date date = new Date();
            final OrderDetail orderDetail = new OrderDetail();
            final ProductInfo productInfo = productInfoList.get(i);
            orderDetail.setGid(UUID.randomUUID().toString().replace("-",""));
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(productInfo.getGid());
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetail.setProductQuantity(productIdAndAmountMap.get(productInfo.getGid()));
            orderDetail.setProductIcon(productInfo.getProductIcon());
            orderDetail.setCreateTime(date);
            orderDetail.setUpdateTime(date);
            orderDetails.add(orderDetail);
        }
        return  orderDetails;
    }
}
