package com.cc.blog.controller;

import com.cc.blog.interfaces.Authority;
import com.cc.blog.model.TopicWithContent;
import com.cc.blog.service.TopicService;
import com.cc.blog.util.MapFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author cc
 * @date 18-3-15 下午5:47
 */
@RestController
@RequestMapping("/topic")
@Authority
public class TopicController {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TopicService topicService;

    /**
     *
     */
    @PostMapping()
    public Map insertTopic(TopicWithContent topicWithContent) {
        topicWithContent.setId(null);
        topicWithContent.setCreatetime(null);
        topicWithContent.setUpdatetime(null);
        return topicService.insertTopic(topicWithContent);
    }

    @PutMapping
    public Map updateTopic(TopicWithContent topicWithContent) {
        topicWithContent.setUpdatetime(null);
        topicWithContent.setCreatetime(null);
        topicWithContent.setStatus(null);
        return topicService.updateTopic(topicWithContent);
    }

    @GetMapping
    public Map getTopicList(@RequestParam(value = "plateId", required = true) int plateId,
                            @RequestParam(value = "pageNum", required = true) int pageNum,
                            @RequestParam(value = "pageSize", required = true) int pageSize,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "startTime", required = false) String startTime,
                            @RequestParam(value = "endTime", required = false) String endTime,
                            @RequestParam(value = "orderBy", required = false, defaultValue = "creatTime ASC") String orderBy
    ) {
        return MapFormatUtil.returnSuccess(topicService.getTopicList(plateId, pageNum, pageSize, name, startTime, endTime, orderBy));
    }

    @GetMapping("/id")
    public Map getTopic(@PathVariable("id") int id) {
        return MapFormatUtil.returnSuccess(topicService.getTopic(id));
    }

}
