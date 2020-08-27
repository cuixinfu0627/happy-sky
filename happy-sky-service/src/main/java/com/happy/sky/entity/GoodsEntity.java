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
@TableName("goods")
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 商品名
	 */
	private String name;
	/**
	 * 商品名
	 */
	private Integer stock;
	/**
	 * 版本号
	 */
	private Integer version;
}
