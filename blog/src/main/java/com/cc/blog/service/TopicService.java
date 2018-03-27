package com.cc.blog.service;

import com.cc.blog.mapper.TopicContentMapper;
import com.cc.blog.mapper.TopicMapper;
import com.cc.blog.model.Topic;
import com.cc.blog.model.TopicContent;
import com.cc.blog.model.TopicWithContent;
import com.cc.blog.util.DateUtil;
import com.cc.blog.util.MapFormatUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        if (i > 0 && j > 0) {
            return MapFormatUtil.returnSuccess("发布成功");
        } else {
            return MapFormatUtil.returnFail("请重试！");
        }

    }

    @Transactional
    public Map updateTopic(TopicWithContent topicWithContent) {
        int contentId = topicMapper.getContentId(topicWithContent.getId());
        TopicContent topicContent = new TopicContent();
        topicContent.setContent(topicWithContent.getContent());
        topicContent.setId(contentId);
        int i = topicContentMapper.updateByPrimaryKeySelective(topicContent);
        int j = topicMapper.updateByPrimaryKeySelective(topicWithContent);
        if (i > 0 && j > 0) {
            return MapFormatUtil.returnSuccess("更新成功");
        } else {
            return MapFormatUtil.returnFail("提交失败，请重试");
        }
    }

    public List<Map> getTopicList(Integer plateId, int pageNum, int pageSize, String name, String startTime, String endTime, String orderBy) {
        PageHelper.orderBy(orderBy);
        PageHelper.startPage(pageNum, pageSize);
        return topicMapper.getTopicList(plateId, name, DateUtil.strToDate(startTime), DateUtil.strToDate(endTime));

    }


    public TopicWithContent getTopic(int id){
      return topicMapper.getTopicDetail(id);

    }
}
