package com.happy.sky.design.observer;

/**https://mp.weixin.qq.com/s?__biz=MzIwMTY0NDU3Nw==&mid=2651938221&idx=1&sn=9cb29d1eb0fdbdb5f976306b08d5bdcc&chksm=8d0f32e3ba78bbf547c6039038682706a2eaf83002158c58060d5eb57bdd83eb966a1e223ef6&scene=21#wechat_redirect
 * @name: Test <tb>
 * @title: 对象间一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 看不懂图的人端着小板凳到这里来，给你举个栗子：
 * 假设有三个人，小美（女，22），小王和小李。小美很漂亮，小王和小李是两个程序猿，
 * 时刻关注着小美的一举一动。
 * 有一天，小美说了一句：“谁来陪我打游戏啊。”这句话被小王和小李听到了，结果乐坏了，
 * 蹭蹭蹭，没一会儿，小王就冲到小美家门口了，
 * 在这里，小美是被观察者，小王和小李是观察者，
 * 被观察者发出一条信息，然后观察者们进行相应的处理，看代码：<tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/4/25 11:22 <tb>
 */
public class Test {

    public static void main(String[] args) {

        XiaoMei xiao_mei = new XiaoMei();
        LaoWang lao_wang = new LaoWang();
        LaoLi lao_li = new LaoLi();

        //小王和小李在小美那里都注册了一下
        xiao_mei.addPerson(lao_wang);
        xiao_mei.addPerson(lao_li);

        //小美向小王和小李发送通知
        xiao_mei.notifyPerson();
    }

}
