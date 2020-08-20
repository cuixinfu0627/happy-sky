package com.happy.sky.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * @name: FindDemo <tb>
 * @title: 查找算法  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/7 15:01 <tb>
 */
public class FindDemo {
    public static void main(String[] args) {
        s1_3();
        s2_4();
        int[] arr = {1, 2, 3, 4, 5, 6};
        int target = 4;
        int[] ints = twoSum(arr, target);
        for (int i=0;i<ints.length;i++){
            System.out.println(ints[i]);
        }
    }

    public static void s1_3() {
        int a[] = { 1, 4, 3 ,6};
        int max_val = -1;
        int max_inx = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max_val) {
                max_val = a[i];
                max_inx = i;
            }
        }
        System.out.println("最大值：" +max_val);
    }

    public static void s2_4() {
        int a[] = { 1, 3, 4, 3, 4, 1, 3, 1 };
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (d.containsKey(a[i])) {
                d.put(a[i], d.get(a[i]) + 1);
            } else {
                d.put(a[i], 1);
            }
        }
        int val_max = -1;
        int time_max = 0;
        for (Integer key : d.keySet()) {
            if (d.get(key) > time_max) {
                time_max = d.get(key);
                val_max = key;
            }
        }
        System.out.println("出现次数最多的元素："+ val_max);
    }

    private static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { map.get(complement), i };
            }
        }
        return null;
    }
}
