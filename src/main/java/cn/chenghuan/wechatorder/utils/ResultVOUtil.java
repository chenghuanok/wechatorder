package cn.chenghuan.wechatorder.utils;

import cn.chenghuan.wechatorder.vo.ResultVO;

/**
 * @author 程欢
 * @Description 返回结果VO工具
 * @Date 2019/6/12 21:38
 */
public final class ResultVOUtil {

    /**
     * 成功
     * @param object
     * @return ResultVO
     */
    public static ResultVO success(final Object object){
         final ResultVO resultVO = new ResultVO();
         resultVO.setCode(0);
         resultVO.setMsg("成功");
         resultVO.setData(object);
         return  resultVO;
    }

    /**
     * 成功(没有参数)
     * @return ResultVO
     */
    public static ResultVO success(){
        return  success(null);
    }
}
