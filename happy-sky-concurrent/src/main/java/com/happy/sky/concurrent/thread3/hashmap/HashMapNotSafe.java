package com.happy.sky.concurrent.thread3.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @name: HashMapNotSafe <tb>
 * @title: 扩容时为空
 * 什么说 HashMap 不是线程安全的呢？我们先来讲解下原理。
 * HashMap 本身默认的容量不是很大，如果不停地往 map 中添加新的数据，它便会在合适的时机进行扩容。
 * 而在扩容期间，它会新建一个新的空数组，并且用旧的项填充到这个新的数组中去。
 * 那么，在这个填充的过程中，如果有线程获取值，很可能会取到 null 值，
 * 而不是我们所希望的、原来添加的值。所以我们程序就想演示这种情景，我们来看一下这段代码：<tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/8/17 11:10 <tb>
 */
public class HashMapNotSafe {

    public static void main(String[] args) {
        final Map<Integer, String> map = new HashMap<>();

        final Integer targetKey = 0b1111_1111_1111_1111; // 65 535
        final String targetValue = "v";
        map.put(targetKey, targetValue);

        new Thread(() -> {
            IntStream.range(0, targetKey).forEach(key -> map.put(key, "someValue"));
        }).start();

        while (true) {
            if (null == map.get(targetKey)) {
                throw new RuntimeException("HashMap is not thread safe.");
            }
        }
    }
}

