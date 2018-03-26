package com.cc.blog.Controller;

import com.cc.blog.model.TopicWithContent;
import com.cc.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author cc
 * @date 18-3-15 下午5:47
 */
@RestController
@RequestMapping("/topic")
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
    public Map updateTopic(TopicWithContent topicWithContent){
    return  null;
    }
}
