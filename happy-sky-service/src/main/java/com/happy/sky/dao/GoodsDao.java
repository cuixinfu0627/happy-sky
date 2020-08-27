/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.happy.sky.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.happy.sky.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface GoodsDao extends BaseMapper<GoodsEntity> {

    GoodsEntity queryGoodsByName(String name);

    int updateByPrimaryKey(GoodsEntity goodsEntity);

}
