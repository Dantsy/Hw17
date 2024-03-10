package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Random random = new Random();
        MedianList<Integer> medianList = new MedianList<>();
        CountDownLatch latch = new CountDownLatch(2);

        Thread producer = new Thread(() -> {
            try {
                latch.countDown();
                latch.await();
                for (int i = 0; i < 20; i++) {
                    var nextValue = random.nextInt(0, 100);
                    medianList.add(nextValue);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                latch.countDown();
                latch.await();
                for (int i = 0; i < 20; i++) {
                    medianList.getMedian();
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Consumer");

        consumer.start();
        producer.start();

    }
}