/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.happy.sky.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.happy.sky.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    /**
     * 描    述：用户数据 - 按小时统计
     *
     * @param params
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> queryUserLineChartByHours(Map<String, Object> params);

    /**
     * 描    述：用户数据 - 按天统计
     *
     * @param params
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> queryUserLineChartByDays(Map<String, Object> params);

    /**
     * 描    述：用户数据 - 按周统计
     *
     * @param params
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> queryUserLineChartByWeeks(Map<String, Object> params);

    /**
     * 描    述：用户数据 - 按月统计
     *
     * @param params
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> queryUserLineChartByMonths(Map<String, Object> params);

    /**
     * 描    述：用户数据 - 按年统计
     *
     * @param params
     * @return List<Map < String, Object>>
     */
    List<Map<String, Object>> queryUserLineChartByYears(Map<String, Object> params);

}
