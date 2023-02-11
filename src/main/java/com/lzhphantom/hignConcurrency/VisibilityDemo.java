package com.lzhphantom.hignConcurrency;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class VisibilityDemo {
    private static boolean flag = true;

    static class CountThread extends Thread {
        @Override
        public void run() {
            System.out.println(LocalDateTime.now() + "count thread start counting");
            int i = 0;
            while (VisibilityDemo.flag) {
                i++;
            }
            System.out.println(LocalDateTime.now() + "count thread end，value of i is " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(LocalDateTime.now() + "主线程启动计数子线程");
        new CountThread().start();
        Thread.sleep(1000);
        // 设置flag为false，使上面启动的子线程跳出while循环，结束运行
        VisibilityDemo.flag = false;
        System.out.println(LocalDateTime.now() + "主线程将状态标识flag被置为false了");
    }

}
