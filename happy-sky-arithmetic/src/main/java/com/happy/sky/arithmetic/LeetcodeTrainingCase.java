package com.happy.sky.arithmetic;

/**
 * @name: BoTrainingCase <tb>
 * @title: 18 | 真题案例（三）：力扣真题训练  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/7 17:01 <tb>
 */
public class LeetcodeTrainingCase {
    public static void main(String[] args) {
        //例题 1：删除排序数组中的重复项
        arrayDuplicateRemoval();

        //例题 2：查找两个有序数组合并后的中位数
    }

    /**
     * @title: 例题 1：删除排序数组中的重复项 <tb>
     * @params: root  <tb
     */
    public static  void arrayDuplicateRemoval() {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int temp = nums[0];
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != temp) {
                nums[len] = nums[i];
                temp = nums[i];
                len++;
            }
        }
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }

}
