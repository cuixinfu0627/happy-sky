package com.happy.sky.concurrent.thread3.hashmap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo02 {
    public static void main(String[] args) {
        // 声明HashMap对象
        Map<String, Integer> map = new HashMap<>();

        //添加数据
        map.put("ZhangYi", 98);
        map.put("WangEr", 99);
        map.put("ZhangShan", 89);
        map.put("Lisi", 92);

        //（1）将Map中所有的键装到Set集合中返回
        Set<String> keys = map.keySet();
        for (String key : keys) {
            int value = map.get(key);
            System.out.println("key:" + key + "  and  value:" + value);
        }

        //（2）返回集合中所有的value的值的集合
        Collection<Integer> values = map.values();
        for (int value : values) {
            System.out.print(value + "   ");
        }
        System.out.println();

        //（3）将每个键值对封装到一个个Entry对象中,再把所有Entry的对象封装到Set集合中返回
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println("key:" + key + "  and  value:" + value);
        }
    }

}