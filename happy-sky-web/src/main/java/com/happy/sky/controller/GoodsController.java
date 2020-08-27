package com.happy.sky.controller;

import com.happy.sky.config.seckill.MyThread;
import com.happy.sky.service.GoodsService;
import com.happy.sky.service.utils.PageUtils;
import com.happy.sky.view.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @name: RedisController <tb>
 * @title: 1、秒杀解决方案  https://mp.weixin.qq.com/s/89WKMyVGpUtwiF9xk9m9VA <tb>
 * @title: 2、分布式锁 https://tianyalei.blog.csdn.net/article/details/102571736 <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/31 9:24 <tb>
 */
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GoodsService goodsService;

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(100);

    private CyclicBarrier cyclicBarrier1 = new CyclicBarrier(100);

    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = goodsService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * @title: 秒杀解决方案 <tb>
     * @author: cuixinfu@51play.com <tb>
     * @date: 2020/8/26 16:41<tb>
     */
    @GetMapping("/seckill")
    public R send() {
        for (int i = 0; i < 100; i++) {
            int number = 10;
            Runnable thread = new MyThread(number, redisTemplate);
            new Thread(thread).run();
        }
        return R.ok().put("message", "秒杀接口测试ok!");
    }

    /**
     * @title: redisson 分布式锁 <tb>
     * @urlWiki: https://github.com/redisson/redisson/wiki <tb>
     * @author: cuixinfu@51play.com <tb>
     * @date: 2020/8/26 16:41<tb>
     */
    @GetMapping("/redisLock")
    public R redisLock() {
        /**
         * 可以看到，这两个商品在各自更新各自的，互不影响。最终在5秒后，有的超时了。调大等待时间，则能保证每个都是100.
         * 通过这种方式，即完成了分布式锁，简单也便捷。当然这里只是举例，
         * 在实际项目中，倘若要做防止超卖，以追求最大性能的话，也可以考虑使用redis来存储amount，
         * 借助于redis的increase来做数量的增减，能迅速的给出客户端是否抢到了商品的判断，之后再通过消息队列去生成订单之类的耗时操作。
         */
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    goodsService.multi(1L);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                try {
                    cyclicBarrier1.await();
                    goodsService.multi(2L);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok().put("message", "商品超卖-分布式锁接口测试ok!");
    }

    /*********关于redisson做分布式锁的代码在这篇文章。这里我来测试一下分布式锁的性能***********/
    private volatile AtomicInteger index = new AtomicInteger(Integer.MAX_VALUE);

    /**
     * @title: 分布式锁的性能-redis分布式锁 <tb>
     * @date: 2020/8/26 17:25<tb>
     */
    @RequestMapping("/random")
    public R random(@RequestParam(value = "goodsId") Integer goodsId,
                    @RequestParam(value = "count", required = false) Integer count) {
        if (count != null) {
            int i = index.getAndDecrement() % count;
            if (i == 0) {
                goodsService.sell(0);
            } else if (i == 1) {
                goodsService.sell(1);
            } else if (i == 2) {
                goodsService.sell(2);
            } else if (i == 3) {
                goodsService.sell(3);
            } else if (i == 4) {
                goodsService.sell(4);
            }
        }
        goodsService.sell(goodsId);
        return R.ok().put("message", "ok");
    }

    /**
     * @title: 分布式锁的性能-走内存 <tb>
     * @date: 2020/8/26 17:25<tb>
     */
    @RequestMapping("/sellmm")
    public R sellmm(@RequestParam(value = "goodsId") Integer goodsId,
                    @RequestParam(value = "count", required = false) Integer count) {
        if (count != null) {
            int i = index.getAndDecrement() % count;
            if (i == 0) {
                goodsService.sellMemory(0);
            } else if (i == 1) {
                goodsService.sellMemory(1);
            } else if (i == 2) {
                goodsService.sellMemory(2);
            } else if (i == 3) {
                goodsService.sellMemory(3);
            } else if (i == 4) {
                goodsService.sellMemory(4);
            }
        }
        goodsService.sellMemory(goodsId);
        return R.ok().put("message", "ok");
    }

    /**
     * @title: 测试购买商品接口 <tb>
     * @params: goodsId[商品id]   threads[线程数]  number[用户购买数量]<tb
     * @date: 2020/8/26 17:56<tb>
     */
    @GetMapping("/testSell")
    public R testSell(@RequestParam(value = "goodsId") Integer goodsId,
                      @RequestParam(value = "threads") Integer threads,
                      @RequestParam(value = "number") Integer number) {
        //随机生成 goodsId 1-3
        //随机生成 threads 100-10000
        //随机生成 number 1-3
        //初始化先存入redis 库存量： goods:goodsId:stock
        //用户购买多少减去多少，修改redis数据量,然后通过消息去修改数据库
        for (int i = 0; i < threads; i++) {
            this.random(goodsId, null);
        }
        return R.ok().put("message", "接口测试ok!");
    }

}
