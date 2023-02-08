package com.lzhphantom.se;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchTest {

    @Test
    public void testCountDownLatch() {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("player" + Thread.currentThread().getName() + "is waiting order");
                        cdOrder.await();
                        System.out.println("player" + Thread.currentThread().getName() + "got order");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("player" + Thread.currentThread().getName() + "arrive home");
                        cdAnswer.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("order" + Thread.currentThread().getName() + "will take a order");
            cdOrder.countDown();
            System.out.println("order" + Thread.currentThread().getName() + "sent");
            cdOrder.await();
            System.out.println("all player arrived home");
            System.out.println("裁判" + Thread.currentThread().getName() + "汇总成绩排名");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
