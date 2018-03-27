package com.cc.blog.controller;

import com.cc.blog.interfaces.GetTokenInterface;
import com.cc.blog.model.UserToken;
import com.cc.blog.service.ReplyService;
import com.cc.blog.util.MapFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author cc
 * @date 18-3-26 下午4:55
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping
    public Map insertReply(@GetTokenInterface UserToken userToken,
                            @RequestParam("topicId")Integer topicId,
                           @RequestParam("content")String content
                           ){
        return MapFormatUtil.returnSuccess(replyService.insertReply(userToken.getId(),topicId,content));
    }

    @GetMapping
    public Map getReplyList(@RequestParam(value ="pageNum" ,required =true ) int pageNum,
                            @RequestParam(value = "pageSize",required =true )int pageSize,
                            @RequestParam(value = "topicId",required =true ) int topicId,
                            @RequestParam(value = "orderBy",required = false,defaultValue = "createTime ASC")String orderBy){
        return  MapFormatUtil.returnSuccess(replyService.getReplyList(pageNum,pageSize,topicId,orderBy));
    }


}
