package com.cc.blog.util;

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
        return  result;
    }

    public static Map<String,Object> returnFail(Object o){
        Map<String,Object> result = new LinkedHashMap<>();
        result.put("0",o);
        return  result;
    }
}
