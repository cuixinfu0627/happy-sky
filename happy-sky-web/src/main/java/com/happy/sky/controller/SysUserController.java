/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.happy.sky.controller;

import com.happy.sky.entity.SysUserEntity;
import com.happy.sky.service.SysUserService;
import com.happy.sky.service.utils.Constant;
import com.happy.sky.service.utils.PageUtils;
import com.happy.sky.validator.ValidatorUtils;
import com.happy.sky.entity.validator.group.AddGroup;
import com.happy.sky.entity.validator.group.UpdateGroup;
import com.happy.sky.view.R;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		/*if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}*/
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}

	@GetMapping("/queryByUserName")
	public R queryByUserName(@RequestParam(value = "username") String username) {
		SysUserEntity sysUserEntity = sysUserService.queryByUserName(username);
		return R.ok().put("data", sysUserEntity);
	}

	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/**
	 * 保存用户
	 */
	@PostMapping("/save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.saveUser(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@PostMapping("/update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@PostMapping("/delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}
}
