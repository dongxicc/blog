package com.cc.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author cc
 * @date 18-3-26 下午5:41
 */

public abstract class BaseController {


    Map returnSuccess(Object o) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("status", "1");
        resultMap.put("result", o);
        return resultMap;
    }

    Map returnFail(Object o) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("result", "0");
        resultMap.put("result", o);
        return resultMap;
    }

    @RequestMapping
    @ResponseBody
    abstract Map insert();

    @RequestMapping
    @ResponseBody
    abstract Map select();

    @RequestMapping
    @ResponseBody
    abstract Map update();
}
