package ru.job4j.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    private final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
    private final AtomicInteger value = new AtomicInteger(1);


    private class Producer extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < queue.getLimit(); i++) {
                try {
                    queue.offer(value.getAndIncrement());
                } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                }
            }
        }
    }

    private class Consumer extends Thread {
        @Override
        public void run() {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Test
    public void actionsTest() throws InterruptedException {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.getSize(), is(2));
        assertThat(queue.poll(), is(2));
    }

}