/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.happy.sky.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy.sky.common.utils.DateHandler;
import com.happy.sky.common.utils.EchartsHandler;
import com.happy.sky.dao.UserDao;
import com.happy.sky.entity.UserEntity;
import com.happy.sky.entity.UserEntity;
import com.happy.sky.form.LoginForm;
import com.happy.sky.service.UserService;
import com.happy.sky.service.utils.PageUtils;
import com.happy.sky.service.utils.Query;
import com.happy.sky.validator.RRException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

	@Override
	public UserEntity queryByMobile(String mobile) {
		return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
	}

	@Override
	public long login(LoginForm form) {
		UserEntity user = queryByMobile(form.getMobile());
		Assert.isNull(user, "手机号或密码错误");

		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
			throw new RRException("手机号或密码错误");
		}

		return user.getId();
	}

	@Override
	public Map<String, Object> echartsList(Byte dateType, Integer status) {
		List<Map<String, Object>> userRegisterLineChart = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formatDate = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
			Map<String, Object> params = new HashMap<>();
			if (dateType == 1){
				//1、按当日查询-每个小时段
				Date startTime = DateHandler.getStartTime(new Date());
				Date endTime = DateHandler.getEndTime(new Date());
				params.put("startTime", formatter.format(startTime));
				params.put("endTime", formatter.format(endTime));

				params.put("status", status);
				userRegisterLineChart = baseMapper.queryUserLineChartByHours(params);
			}else if (dateType == 2) {
				//1、按天查询-当月每一天
				String endTime = DateHandler.getForDate();
				String startTime = DateHandler.getForDatelast();
				params.put("startTime", formatter.parse(startTime));
				params.put("endTime", formatter.parse(endTime));

				params.put("status", status);
				userRegisterLineChart = baseMapper.queryUserLineChartByDays(params);
			}else if (dateType == 3) {
				//1、按月查询-当年每个月
				Date startTime = DateHandler.getBeginDayOfYear();
				Date endTime = DateHandler.getEndDayOfYear();
				params.put("startTime", startTime);
				params.put("endTime", endTime);
				params.put("status", status);
				userRegisterLineChart = baseMapper.queryUserLineChartByMonths(params);
			}else if (dateType == 4) {
				//1、按天查询-近七天
				Date endTime = sdf.parse(DateHandler.getNowStrDate());
				Date startTime = DateHandler.otherDate(DateHandler.getNowStrDate(), formatDate, -7);
				params.put("startTime", formatter.format(startTime));
				params.put("endTime", formatter.format(endTime));
				params.put("status", status);
				userRegisterLineChart = baseMapper.queryUserLineChartByDays(params);
			}else if (dateType == 5) {
				//1、按天查询-近30天
				Date endTime = sdf.parse(DateHandler.getNowStrDate());
				Date startTime = DateHandler.otherDate(DateHandler.getNowStrDate(), formatDate, -30);
				params.put("startTime", formatter.format(startTime));
				params.put("endTime", formatter.format(endTime));
				params.put("status", status);
				userRegisterLineChart = baseMapper.queryUserLineChartByDays(params);
			}
			return EchartsHandler.completionDate(userRegisterLineChart, Integer.valueOf(dateType));
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");

		IPage<UserEntity> page = this.page(
				new Query<UserEntity>().getPage(params),
				new QueryWrapper<UserEntity>().like(StringUtils.isNotBlank(username),
						"username", username)

		);

		return new PageUtils(page);
	}
}
