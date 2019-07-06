package cn.chenghuan.wechatorder.service.impl;

import cn.chenghuan.wechatorder.dao.IOrderDetailDao;
import cn.chenghuan.wechatorder.dao.IOrderMasterDao;
import cn.chenghuan.wechatorder.dao.IProductInfoDao;
import cn.chenghuan.wechatorder.domain.OrderDetail;
import cn.chenghuan.wechatorder.domain.OrderMaster;
import cn.chenghuan.wechatorder.domain.ProductInfo;
import cn.chenghuan.wechatorder.dto.CartDTO;
import cn.chenghuan.wechatorder.dto.OrderDTO;
import cn.chenghuan.wechatorder.enums.ExceptionEnum;
import cn.chenghuan.wechatorder.enums.OrderStatusEnum;
import cn.chenghuan.wechatorder.enums.PayStatusEnum;
import cn.chenghuan.wechatorder.exception.EmptyValueException;
import cn.chenghuan.wechatorder.exception.OrderException;
import cn.chenghuan.wechatorder.service.IOrderService;
import cn.chenghuan.wechatorder.service.IProductInfoService;
import cn.chenghuan.wechatorder.utils.UuidUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@Transactional(rollbackFor =Exception.class )
public class OrderServiceImpl implements IOrderService {

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
     * 根据订单GID查找订单
     * @param orderId
     * @return OrderDTO
     */
    @Override
    public OrderDTO findOne(final String orderId) {
        final OrderMaster orderMaster = orderMasterDao.findByGid(orderId);
        if(orderMaster==null){
            throw new EmptyValueException(ExceptionEnum.EMPTY_VALUE,"订单不存在");
        }
        final List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        return buildOrderDTO(orderMaster,orderDetailList);
    }

    /**
     * 分页查找对应用户的订单
     * @param buyerOpenid
     * @return Page<OrderMaster>
     */
    @Override
    public Page<OrderMaster> findListByBuyerOpenid(final String buyerOpenid) {
        final Pageable pageable = PageRequest.of(0,1);
        final Page<OrderMaster> orderMasters = orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);
        return orderMasters;
    }

    /**
     * 取消订单
     * @param orderId
     */
    @Override
    public void cancelOrder(final String orderId) {
        //1.判断对应的订单状态
        final OrderMaster orderMaster = orderMasterDao.findByGid(orderId);
        if(!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
           throw new OrderException(ExceptionEnum.ORDER_STATUE_NO_MATCH,"订单状态");
        }
        //2.修改订单状态
        orderMasterDao.updateOrderStatus(OrderStatusEnum.CANCEL.getCode(),orderId,new Date());
        //3.增加商品库存
        final List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        List<CartDTO> cartDTOList = orderDetailList.stream().map(ele->
            new CartDTO(ele.getProductId(),ele.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.increaseProductStock(cartDTOList);
        //4.若已支付，退款
        if(orderMaster.getPayStatus().equals(PayStatusEnum.FINISHED.getCode())){
            //TODO
        }
    }

    /**
     * 构建订单实体
     * @param orderDTO
     * @return OrderMaster
     */
    private OrderMaster buildOrderMaster(final OrderDTO orderDTO){
        final OrderMaster orderMaster = new OrderMaster();
        final Date date = new Date();
        orderMaster.setGid(UuidUtils.createUUID());
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
        //查询对应id的商品信息
        final List<ProductInfo> productInfoList = productInfoService.findByIds(gidList);
        if(productInfoList==null||productInfoList.size()==0||gidList.size()!=productInfoList.size()) {
                throw new EmptyValueException(ExceptionEnum.EMPTY_VALUE, "对应商品");
        }
        //商品gid和商品数量对应
        final Map<String,Integer> productIdAndAmountMap = new HashMap<>(orderDetailList.size());
        orderDetailList.forEach(ele->
            productIdAndAmountMap.put(ele.getProductId(),ele.getProductQuantity())
        );
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
        final List<String> gidLists = orderDetailList.stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        //商品异常检查
        final List<ProductInfo> productInfoList = productInfoDao.findByGidIn(gidLists);
        if(productInfoList==null||productInfoList.size()==0||gidLists.size()!=productInfoList.size()){
            throw new EmptyValueException(ExceptionEnum.EMPTY_VALUE,"对应商品");
        }
        //商品gid和商品数量对应
        final Map<String,Integer> productIdAndAmountMap = new HashMap<>(orderDetailList.size());
        orderDetailList.forEach(ele->
                productIdAndAmountMap.put(ele.getProductId(),ele.getProductQuantity())
        );
        final List<OrderDetail> orderDetails = new ArrayList<>();
        for (int i = 0; i < productInfoList.size(); i++) {
            final ProductInfo productInfo = productInfoList.get(i);
            final OrderDetail orderDetail = buildOrderDetail(productInfo,
                    orderId,productIdAndAmountMap.get(productInfo.getGid()));
            orderDetails.add(orderDetail);
        }
        return  orderDetails;
    }

    /**
     * 构建订单明细
     * @param productInfo
     * @param orderId
     * @param productAmount
     * @return OrderDetail
     */
    private OrderDetail buildOrderDetail(final ProductInfo productInfo,
                                         final String orderId,final Integer productAmount){
        final Date date = new Date();
        final OrderDetail orderDetail = new OrderDetail();
        orderDetail.setGid(UuidUtils.createUUID());
        orderDetail.setOrderId(orderId);
        orderDetail.setProductId(productInfo.getGid());
        orderDetail.setProductName(productInfo.getProductName());
        orderDetail.setProductPrice(productInfo.getProductPrice());
        orderDetail.setProductQuantity(productAmount);
        orderDetail.setProductIcon(productInfo.getProductIcon());
        orderDetail.setCreateTime(date);
        orderDetail.setUpdateTime(date);
        return orderDetail;
    }

    /**
     * 构建订单
     * @param orderMaster
     * @param orderDetailList
     * @return OrderDTO
     */
    private OrderDTO buildOrderDTO(final OrderMaster orderMaster,final List<OrderDetail> orderDetailList){
        final OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
