package cn.chenghuan.wechatorder.utils;

import cn.chenghuan.wechatorder.enums.CodeEnum;

/**
 * @author chenghuan
 * @Description 枚举工具
 * @Date 2019/8/3 21:36
 */
public class EnumUtil {

    /**
     * 根据code获取对应的枚举
     * @param code
     * @param enumClass
     * @param <T>
     * @return T
     */
    public static <T extends CodeEnum> T getByCode(final Integer code , final Class<T> enumClass){
        for(T each : enumClass.getEnumConstants()){
             if(code.equals(each.getCode())){
                 return each;
             }
        }
        return null;
    }
}
