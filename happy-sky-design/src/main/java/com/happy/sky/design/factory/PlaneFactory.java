package com.happy.sky.design.factory;

/**
 * @name: PlaneFactory <tb>
 * @title: 具体工厂  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 14:16 <tb>
 */
public class PlaneFactory extends VehicleFactory {
    public Moveable create() {
        return new Plane();
    }
}