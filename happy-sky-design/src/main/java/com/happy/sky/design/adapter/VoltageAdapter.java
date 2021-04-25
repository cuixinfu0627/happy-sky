package com.happy.sky.design.adapter;

/**
 * @name: VoltageAdapter <tb>
 * @title: 变压器  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 11:37 <tb>
 */
public class VoltageAdapter {
    // 改变电压的功能
    public void changeVoltage() {
        System.out.println("正在充电...");
        System.out.println("原始电压：" + Phone.V + "V");
        System.out.println("经过变压器转换之后的电压:" + (Phone.V - 200) + "V");
    }
}