package com.happy.sky.design.observer;

/**
 * @name: LaoLi <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 11:21 <tb>
 */
public class LaoLi implements Person {

    private String name = "小李";

    public LaoLi() {
    }

    @Override
    public void getMessage(String s) {
        System.out.println(name + "接到了小美打过来的电话，电话内容是：->" + s);
    }

}