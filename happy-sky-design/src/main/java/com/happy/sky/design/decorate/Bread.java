package com.happy.sky.design.decorate;

/**
 * @name: Bread <tb>
 * @title: 面包类  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 11:27 <tb>
 */
public class Bread extends Food {

    private Food basic_food;

    public Bread(Food basic_food) {
        this.basic_food = basic_food;
    }

    public String make() {
        return basic_food.make()+"+面包";
    }
}