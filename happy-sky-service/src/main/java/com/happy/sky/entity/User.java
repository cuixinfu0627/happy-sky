/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.happy.sky.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userId;

	private String username;

	private String nickname;

	private String email;

	private String mobile;

	private String avatar;
}
