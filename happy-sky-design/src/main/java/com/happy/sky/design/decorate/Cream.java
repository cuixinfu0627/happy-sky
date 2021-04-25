package com.happy.sky.design.decorate;

/**
 * @name: Cream <tb>
 * @title: 奶油类  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 11:28 <tb>
 */
public class Cream extends Food {

    private Food basic_food;

    public Cream(Food basic_food) {
        this.basic_food = basic_food;
    }

    public String make() {
        return basic_food.make()+"+奶油";
    }
}