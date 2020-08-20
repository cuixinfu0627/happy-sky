package com.happy.sky.controller;

import com.happy.sky.entity.User;
import com.happy.sky.view.R;
import org.springframework.web.bind.annotation.*;

/**
 * @name: HelloWorldController <tb>
 * @title: 测试接口  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/17 15:00 <tb>
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/getUser")
    public R getUser(@RequestParam(value = "userId") Long userId,
                     @RequestParam(value = "username") String username,
                     @RequestParam(value = "nickname") String nickname,
                     @RequestParam(value = "email") String email,
                     @RequestParam(value = "mobile") String mobile) throws Exception {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setMobile(mobile);
        return R.ok().put("user",user);
    }

    @PostMapping("/saveUser")
    public R saveUser(@RequestParam(value = "userId") Long userId,
                      @RequestParam(value = "username") String username,
                      @RequestParam(value = "nickname") String nickname,
                      @RequestParam(value = "email") String email,
                      @RequestParam(value = "mobile") String mobile) throws Exception {
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setMobile(mobile);
        return R.ok().put("user",user);
    }

}
