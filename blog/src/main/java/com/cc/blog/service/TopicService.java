package com.cc.blog.service;

import com.cc.blog.mapper.TopicContentMapper;
import com.cc.blog.mapper.TopicMapper;
import com.cc.blog.model.TopicContent;
import com.cc.blog.model.TopicWithContent;
import com.cc.blog.util.MapFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author cc
 * @date 18-3-16 上午10:05
 */
@Service
public class TopicService {


    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private TopicContentMapper topicContentMapper;

    @Transactional
    public Map insertTopic(TopicWithContent topicWithContent) {
        TopicContent topicContent = new TopicContent();
        topicContent.setContent(topicWithContent.getContent());
        int i = topicContentMapper.insertSelective(topicContent);
        topicWithContent.setContentid(topicContent.getId());
        int j = topicMapper.insertSelective(topicWithContent);
        if (i>0&&j>0){
            return MapFormatUtil.returnSuccess("发布成功");
        }else {
            return MapFormatUtil.returnFail("请重试！");
        }

    }
}
