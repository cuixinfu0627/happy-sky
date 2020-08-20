package com.happy.sky.concurrent.thread3.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo01 {

    public static void main(String[] args) {
        // 声明HashMap对象
        Map<String, Integer> map = new HashMap<>();

        //添加数据
        map.put("ZhangYi", 98);
        map.put("WangEr", 99);
        map.put("ZhangShan", 89);
        map.put("Lisi", 92);

        //根据键值对键值获取数据‘’
        int value = map.get("Lisi");
        System.out.println("kay:Lisi And value:" + value);

        //获取Map中键值对的个数
        int size = map.size();
        System.out.println("map 中的键值对个数为：" + size);

        //判断Map集合中是否包含键为key的键值对
        boolean b1 = map.containsKey("LiSI");
        boolean b2 = map.containsKey("Lisi");
        System.out.println("是否包含键值为LiSI的键值对数据：" + b1);
        System.out.println("是否包含键值为Lisi的键值对数据：" + b2);

        //判断Map集合中是否包含值为value的键值对
        boolean b3 = map.containsValue(99);
        boolean b4 = map.containsValue(100);
        System.out.println("是否包含值为99的键值对数据：" + b3);
        System.out.println("是否包含值为100的键值对数据：" + b4);

        //判断Map集合中是否没有任何键值对

        boolean b5 = map.isEmpty();
        System.out.println("map中键值对数据是否为空：" + b5);

        //根据键值删除Map中键值对
        int value2 = map.remove("Lisi");
        System.out.println("删除了键为Lisi的键值对数据，其值为：" + value2);

        boolean b6 = map.containsKey("Lisi");
        System.out.println("是否包含键值为Lisi的键值对数据：" + b6);

        //清空Map集合中所有的键值对
        map.clear();
        boolean b7 = map.isEmpty();
        System.out.println("map中键值对数据是否为空：" + b7);

    }
}