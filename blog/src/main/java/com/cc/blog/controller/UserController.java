package com.cc.blog.controller;


import com.alibaba.fastjson.JSONObject;
import com.cc.blog.interfaces.Authority;
import com.cc.blog.model.User;
import com.cc.blog.service.UserService;
import com.cc.blog.util.EncodeMd5;
import com.cc.blog.util.MapFormatUtil;
import com.cc.blog.util.NetworkIpUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author cc
 * @date 18-3-15 下午2:06
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public Map register(@RequestBody User user) {
        user.setId(null);
        user.setCreatetime(null);
        user.setUpdatetime(null);
        user.setPassword(EncodeMd5.encode(user.getPassword()));
        int i = userService.register(user);
        System.out.println(JSONObject.toJSONString(user));
        if (i > 0) {
            return MapFormatUtil.returnSuccess("注册成功,请验证邮箱");
        } else {
            return MapFormatUtil.returnFail("注册失败");
        }
    }


    @GetMapping
    public Map login(@RequestParam("name") String name, @RequestParam("password") String password) throws IOException {
        Map result = userService.login(name, password, NetworkIpUtil.getClientIpAddr(request));
        if (result != null) {
            return MapFormatUtil.returnSuccess(result);
        } else {
            return MapFormatUtil.returnFail(result);
        }
    }
    @Authority
    @GetMapping("/{id}")
    public Map userInfo(@PathVariable("id") int id) {
        return MapFormatUtil.returnSuccess(userService.userInfo(id));
    }

    @Authority
    @PutMapping
    public Map updateInfo(@RequestBody User user) {
        user.setCreatetime(null);
        user.setUpdatetime(null);
        user.setPassword(null);
        return MapFormatUtil.returnSuccess(userService.updateInfo(user));
    }
}
