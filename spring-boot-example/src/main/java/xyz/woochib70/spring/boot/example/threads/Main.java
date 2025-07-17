package xyz.woochib70.spring.boot.example.threads;

import java.util.concurrent.atomic.AtomicReference;

public class Main {

    static Integer count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (count) {
                    count++;
                }
            }
        });

        thread.start();
        for (int i = 0; i < 1000; i++) {
            synchronized (count) {
                count++;
            }
        }
        thread.join();
        System.out.println(count);
    }


}
