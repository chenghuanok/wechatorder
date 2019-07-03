package cn.chenghuan.wechatorder.utils;

import java.util.UUID;

/**
 * @author 程欢
 * @Description UUID工具类
 * @Date 2019/7/3 8:11
 */
public final class UuidUtils {

    /**
     * 创建UUID
     * @return String
     */
    public static String createUUID(){
       return UUID.randomUUID().toString().replace("-","");
    }
}
