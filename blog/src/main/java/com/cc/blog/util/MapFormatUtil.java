package com.cc.blog.util;

import com.alibaba.fastjson.JSONObject;
import com.cc.blog.config.log;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author cc
 * @date 18-3-16 上午10:41
 */
public class MapFormatUtil {

    public static Map<String,Object> returnSuccess(Object o){
        Map<String,Object> result = new LinkedHashMap<>();
        result.put("1",o);
        log.info2("-->>返回成功： "+ JSONObject.toJSONString(result));
        return  result;
    }

    public static Map<String,Object> returnFail(Object o){
        Map<String,Object> result = new LinkedHashMap<>();
        result.put("0",o);
        log.info2("-->>返回失败： "+ JSONObject.toJSONString(result));
        return  result;
    }
}
