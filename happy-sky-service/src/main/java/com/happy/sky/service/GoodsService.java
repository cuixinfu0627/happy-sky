/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.happy.sky.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.happy.sky.entity.GoodsEntity;
import com.happy.sky.service.utils.PageUtils;

import java.util.Map;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface GoodsService extends IService<GoodsEntity> {
	PageUtils queryPage(Map<String, Object> params);

	void multi(long goodId);

    void sell(Integer goodsId);

	void sellMemory(Integer goodsId);
}
