package com.happy.sky.design.decorate;

/**
 * @name: Vegetable <tb>
 * @title: 蔬菜类  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 11:28 <tb>
 */
public class Vegetable extends Food {

    private Food basic_food;

    public Vegetable(Food basic_food) {
        this.basic_food = basic_food;
    }

    public String make() {
        return basic_food.make()+"+蔬菜";
    }

}