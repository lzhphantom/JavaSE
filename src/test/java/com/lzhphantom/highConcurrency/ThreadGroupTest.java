package com.lzhphantom.highConcurrency;

import org.junit.jupiter.api.Test;

public class ThreadGroupTest {

    @Test
    public void testThreadGroup() {
        ThreadGroup group = new ThreadGroup("lzhphantom");
        Thread thread = new Thread(group, () -> {
            String groupName = Thread.currentThread().getThreadGroup().getName();
            String threadName = Thread.currentThread().getName();
            System.out.println("group name:" + groupName);
            System.out.println("thread name:" + threadName);
        });
        thread.start();
    }
}
