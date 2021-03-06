package com.happy.sky.controller;

import com.happy.sky.common.utils.JSONHandler;
import com.happy.sky.entity.User;
import com.happy.sky.view.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sky")
public class SkyController {

	private static final Logger logger = LoggerFactory.getLogger(SkyController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("forward:/sky/welcome.json");//默认为forward模式
		//ModelAndView mv = new ModelAndView("redirect:/#/sky/welcome.json");//redirect模式
		return mv;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("forward:/sky/welcome.json");//默认为forward模式
		//ModelAndView mv = new ModelAndView("redirect:/#/sky/welcome.json");//redirect模式
		return mv; 
	} 
	
	@RequestMapping(value = "welcome.json", method = RequestMethod.GET)
    public R welcome(HttpServletRequest request, Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", "Hi,Nice to meet you.!");
		map.put("keyword", "welcome to Come to this system!");
		String result = JSONHandler.objectToJson(map);
    	return R.ok().put("result", result);
    }

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
