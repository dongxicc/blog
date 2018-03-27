package com.cc.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.cc.blog.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author cc
 * @date 18-3-26 下午5:40
 */
@RestController
@RequestMapping("/plate")
public class PlateController {
    @Autowired
    private PlateService plateService;


    @GetMapping
    public Map getPlates() {
        return plateService.getPlates();
    }

    @PostMapping
    public Map insertPlates(@RequestBody JSONObject params) {
        return plateService.insertPlate(params.getString("name"), params.getInteger("monitorId"));
    }

    @PutMapping
    public Map updatePlates(@RequestBody JSONObject params){
        return plateService.updatePlatee(params.getInteger("id"),params.getString("name"), params.getInteger("monitorId"));
    }

}
