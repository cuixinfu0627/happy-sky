package com.happy.sky.design.adapter;

/**
 * @name: Phone <tb>
 * @title: 手机类  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 11:37 <tb>
 */
public class Phone {

    public static final int V = 220;// 正常电压220v，是一个常量

    private VoltageAdapter adapter;

    // 充电
    public void charge() {
        adapter.changeVoltage();
    }

    public void setAdapter(VoltageAdapter adapter) {
        this.adapter = adapter;
    }
}
