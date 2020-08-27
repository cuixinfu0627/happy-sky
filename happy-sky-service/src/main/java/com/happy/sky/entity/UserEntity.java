package com.happy.sky.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 系统用户
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-08-21 16:30:57
 */
@Data
@TableName("tab_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 照片url
	 */
	private String pic;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 身高
	 */
	private BigDecimal height;
	/**
	 * 血型。1：A型； 2：B型；3：O型
	 */
	private Integer bloodType;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 头像url
	 */
	private String avatar;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;

	/**
	 * 状态  0：不在线   1：在线
	 */
	private Integer online ;

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 最后一次登录时间
	 */
	private Date lastLoginTime ;

}
