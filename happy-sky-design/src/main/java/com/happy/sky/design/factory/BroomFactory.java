package com.happy.sky.design.factory;

/**
 * @name: BroomFactory <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 14:16 <tb>
 */
public class BroomFactory extends VehicleFactory {
    public Moveable create() {
        return new Broom();
    }
}