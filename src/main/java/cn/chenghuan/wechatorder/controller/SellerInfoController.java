package cn.chenghuan.wechatorder.controller;

import cn.chenghuan.wechatorder.domain.SellerInfo;
import cn.chenghuan.wechatorder.service.ISellerInfoService;
import cn.chenghuan.wechatorder.utils.UuidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

/**
 * @author chenghuan
 * @Description 卖家controller
 * @Date 2019/8/14 22:20
 */
@Api(description = "卖家接口")
@RestController
@RequestMapping("/buyer/seller")
@Slf4j
public class SellerInfoController {

    /**
     * 卖家service
     */
    @Autowired
    private ISellerInfoService sellerInfoService;

    /**
     * redisTemplate
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 卖家登录
     * @param openId
     */
    @ApiOperation(value = "卖家登录" ,  notes="卖家登录")
    @GetMapping("/login")
    public void login(@RequestParam(name = "openId") final String openId){
       if(log.isInfoEnabled()){
           log.info("卖家openId为："+openId);
       }
      final SellerInfo sellerInfo = sellerInfoService.findByOpenId(openId);
      final String token = UuidUtils.createUUID();
      redisTemplate.opsForValue().set("token",token,7200,TimeUnit.SECONDS);
    }
}
