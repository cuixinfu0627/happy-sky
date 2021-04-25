package com.happy.sky.design.adapter;

/**
 * @name: 适配器模式 <tb>
 * @title: 将两种完全不同的事物联系到一起，就像现实生活中的变压器。
 * 假设一个手机充电器需要的电压是20V，但是正常的电压是220V，这时候就需要一个变压器，
 * 将220V的电压转换成20V的电压，这样，变压器就将20V的电压和手机联系起来了。  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 11:34 <tb>
 */
public class Test {
    public static void main(String[] args) {
        Phone phone = new Phone();
        VoltageAdapter adapter = new VoltageAdapter();
        phone.setAdapter(adapter);
        phone.charge();
    }
}
