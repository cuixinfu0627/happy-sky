package com.happy.sky.common.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * 列表工具
* @ClassName: ListTool
* @author cuixinfu@xiu8.com
* @date 2014年12月24日 下午1:49:26
 */
public class ListHandler {

	/**
	 * 将字符串以","分隔后返回long型list
	 */
	public static List<Long> str2long(String str){
		if(StringHandler.isEmpty(str)){
			return null;
		}
		List<Long> list = new ArrayList<Long>();
		for(String s : str.split(",")){
			try {
				list.add(Long.parseLong(s));
			} catch (Exception e) {
			}
		}
		return list;
	}
	
	/**
	 * 将字符串以","分隔后返回int型list
	 */
	public static List<Integer> str2Int(String str){
		if(StringHandler.isEmpty(str)){
			return null;
		}
		List<Integer> list = new ArrayList<Integer>();
		for(String s : str.split(",")){
			try {
				list.add(Integer.parseInt(s));
			} catch (Exception e) {
			}
		}
		return list;
	}
	
	/**
	 * 将字符串以","分隔后返回String型list
	 */
	public static List<String> str2str(String str){
		if(StringHandler.isEmpty(str)){
			return null;
		}
		List<String> list = new ArrayList<String>();
		for(String s : str.split(",")){
			list.add(s);
		}
		return list;
	}
	
	/**
	 * 将list以","分隔后组成字符串返回
	 */
	public static String long2str(List<Long> list){
		if(StringHandler.isEmpty(list)){
			return null;
		}
		StringBuffer str = new StringBuffer();
		for(long l : list){
			str.append(","+l);
		}
		return str.substring(1);
	}
	
	/**
	 * 将list<String>转为String
	 * @param list
	 * @return
	 */
	public static String list2str(List<String> list) {
		if(StringHandler.isEmpty(list)) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		for(String s : list) {
			str.append(","+s);
		}
		return str.substring(1);
	}
	
	/**
	
	/**将数字数组转换为以","分割的字符串，如：1001,1002,1003
	 * @param nums
	 * @return
	 */
	public static String num2Str(Number... nums){
		if(nums.length==0)
			return null;
		StringBuilder sb = new StringBuilder();
		for(Number num : nums){
			if(num!=null)
				sb.append(',').append(num.toString());
		}
		if(sb.length()==0)
			return null;
		return sb.substring(1);
	}
	
	/**将数字列表转换为以","分割的字符串，如：1001,1002,1003
	 * @param nums
	 * @return
	 */
	public static String num2Str(Collection<? extends Number> nums){
		if(nums==null||nums.size()==0)
			return null;
		StringBuilder sb = new StringBuilder();
		for(Number num : nums){
			if(num!=null)
				sb.append(',').append(num.toString());
		}
		if(sb.length()==0)
			return null;
		return sb.substring(1);
	}
	
	/**
	  * 使用 Map按key进行排序
	  * @param
	  * @return
	  */
   @SuppressWarnings("unchecked")
   public static List<Object> sortList(List<Object> list) {
       if (list == null || list.isEmpty()) {
           return null;
       }
       List<Object> sortList = new ArrayList<Object>((Collection<? extends Object>) new ListComparator());
       sortList.sort((Comparator<? super Object>) sortList);
       return sortList;
   }
}
