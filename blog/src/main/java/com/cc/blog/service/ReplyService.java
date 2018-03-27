package com.cc.blog.service;

import com.cc.blog.interfaces.Authority;
import com.cc.blog.mapper.ReplyMapper;
import com.cc.blog.model.Reply;
import com.cc.blog.util.MapFormatUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cc
 * @date 18-3-26 下午4:58
 */
@Service
@Authority
public class ReplyService {


    @Autowired
    private ReplyMapper replyMapper;

    public Map insertReply(int userId, int topicId, String content) {
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setUserid(userId);
        reply.setTopicid(topicId);
        replyMapper.insertSelective(reply);
        return MapFormatUtil.returnSuccess("回复成功");
    }

    public Object getReplyList(int pageNum, int pageSize, int topicId,String orderBy){
        PageHelper.orderBy(orderBy);
        PageHelper.startPage(pageNum,pageSize);
       List<Reply> ls = replyMapper.getReplyList(topicId);
       return new PageInfo<>(ls);
    }

}
