package com.yuanhui.tutorial.multithreading.state;

/**
 * 1. 建议线程正常停止 -> 利用次数，不建议死循环
 * 2. 建议使用标志位 -> 设置一个标志位
 * 3. 不要使用 stop 或 destroy 等过时或者 JDK 不建议使用的方法。
 */
public class TestStop implements Runnable {
    // 1. 设置一个标志位
    private boolean flag = true;

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main " + i);
            if (i == 900) {
                // 3. 调用 stop 方法切换标志位，停止线程
                testStop.stop();
                System.out.println("thread should stop");
            }
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run thread " + i++);
        }
    }

    // 2. 设置一个公开的方法停止线程，转换标志位
    public void stop() {
        this.flag = false;
    }
}
