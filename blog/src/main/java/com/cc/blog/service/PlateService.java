package com.cc.blog.service;

import com.cc.blog.mapper.PlateMapper;
import com.cc.blog.model.Plate;
import com.cc.blog.util.MapFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author cc
 * @date 18-3-27 上午11:08
 */
@Service
public class PlateService {

    @Autowired
    private PlateMapper plateMapper;

    public Map getPlates(){
      return MapFormatUtil.returnSuccess(plateMapper.getPlates()) ;
    }

    public Map insertPlate(String name,int monitorId){
        Plate plate = new Plate();
        plate.setPlatename(name);
        plate.setMonitorid(monitorId);
        plateMapper.insertSelective(plate);
        return MapFormatUtil.returnSuccess("新建板块成功");
    }

    public Map updatePlatee(int id,String name,int monitorId){
        Plate plate = new Plate();
        plate.setId(id);
        plate.setPlatename(name);
        plate.setMonitorid(monitorId);
        plateMapper.updateByPrimaryKeySelective(plate);
        return MapFormatUtil.returnSuccess("更新板块成功");
    }
}
