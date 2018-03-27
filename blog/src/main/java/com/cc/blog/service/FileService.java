package com.cc.blog.service;

import com.cc.blog.config.log;
import com.cc.blog.util.DateUtil;
import com.cc.blog.util.MapFormatUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author cc
 * @date 18-3-27 上午11:39
 */
@Service
public class FileService {
    public static List<String> IMAGETYPE = new ArrayList<>(Arrays.asList(".jpeg", ".JPEG", ".png", ".PNG", ".gif", ".GIF", ".JPG", ".jpg"));

    public Map uploadFile(MultipartFile file){
        final   String fileDir =" /file/"+ DateUtil.getDateNumber()+"/";

        if (file==null){
            return MapFormatUtil.returnFail("上传失败，没有文件");
        }
        String[] str = file.getOriginalFilename().split(".");
        String type = str[str.length-1];
        if (type==null||!IMAGETYPE.contains(type)){
            return MapFormatUtil.returnFail("上传失败，不支持的类型");
        }
        String fileName =fileDir+ String.valueOf(System.currentTimeMillis())+type;
        File saveFile = new File(fileName);
        if (!saveFile.getParentFile().exists()) {
//                    saveFile.mkdirs();
            if (!saveFile.getParentFile().mkdirs()) {
                log.error("创建文件夹失败，请检查！");
            }
        }
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return MapFormatUtil.returnFail("上传失败，请重试");
        }
        return MapFormatUtil.returnSuccess("https://dongxicc.cn/blog"+fileName);
    }
}
