package com.happy.sky.design.factory;

/**
 * @name: 工厂模式 <tb>
 * @简单工厂模式：一个抽象的接口，多个抽象接口的实现类，一个工厂类，用来实例化抽象的接口
 * @工厂方法模式：有四个角色，抽象工厂模式，具体工厂模式，抽象产品模式，具体产品模式。不再是由一个工厂类去实例化具体的产品，而是由抽象工厂的子类去实例化产品  <tb>
 * @抽象工厂模式：与工厂方法模式不同的是，工厂方法模式中的工厂只生产单一的产品，而抽象工厂模式中的工厂生产多个产品
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 13:54 <tb>
 */

public class Test {
    public static void main(String[] args) {
        Car c = Factory.getCarInstance("Benz");
        if (c != null) {
            c.run();
            c.stop();
        } else {
            System.out.println("造不了这种汽车。。。");
        }

        VehicleFactory factory = new BroomFactory();
        Moveable m = factory.create();
        m.run();

    }
}
