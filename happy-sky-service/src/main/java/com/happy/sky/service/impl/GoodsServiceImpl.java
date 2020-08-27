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
import com.happy.sky.dao.GoodsDao;
import com.happy.sky.entity.GoodsEntity;
import com.happy.sky.service.GoodsService;
import com.happy.sky.service.RedissonLock;
import com.happy.sky.service.utils.PageUtils;
import com.happy.sky.service.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

	private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("name");

		IPage<GoodsEntity> page = this.page(
				new Query<GoodsEntity>().getPage(params),
				new QueryWrapper<GoodsEntity>().like(StringUtils.isNotBlank(username),
						"name", username)

		);

		return new PageUtils(page);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@RedissonLock(lockIndex = 0)
	public void multi(long goodId) {
		GoodsEntity goods = baseMapper.selectById(1);
		logger.info("------stock:{}",goods.getStock());

		goods.setStock(goods.getStock() + 1);
		baseMapper.updateById(goods);
	}


	@Resource
	private StringRedisTemplate stringRedisTemplate;

	private static final String GOODS_PREFIX = "goods:id:";

	private volatile AtomicInteger count1 = new AtomicInteger(500000);
	private volatile AtomicInteger count2 = new AtomicInteger(500000);
	private volatile AtomicInteger count3 = new AtomicInteger(500000);
	private volatile AtomicInteger count4 = new AtomicInteger(500000);
	private volatile AtomicInteger count5 = new AtomicInteger(500000);


	/**
	 * 上分布式锁
	 */
	@Override
	@RedissonLock(lockIndex = 0)
	public void sell(Integer goodsId) {
		logger.info("goodId：" + goodsId);
		stringRedisTemplate.opsForValue().set(GOODS_PREFIX+getRandomString(8), System.currentTimeMillis() + "abc");
		logger.info(System.currentTimeMillis() + "");
	}


	/**
	 * 直接读内存
	 */
	@Override
	public void sellMemory(Integer goodsId) {
		logger.info("goodId：" + goodsId);
		switch (goodsId) {
			case 0:
				logger.info("count1：" + count1.getAndDecrement());
				break;
			case 2:
				logger.info("count2：" + count2.getAndDecrement());
				break;
			case 3:
				logger.info("count3：" + count3.getAndDecrement());
				break;
			case 4:
				logger.info("count4：" + count4.getAndDecrement());
				break;
			default:
				logger.info("count5：" + count5.getAndDecrement());
				break;
		}
		stringRedisTemplate.opsForValue().set(GOODS_PREFIX+getRandomString(8), System.currentTimeMillis() + "cba");
		logger.info(System.currentTimeMillis() + "");
	}

	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

}
