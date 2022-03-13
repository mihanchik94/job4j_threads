package ru.job4j.cas;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {
    private static class ThreadCASCount extends Thread {
        private final CASCount count;

        private ThreadCASCount(CASCount count) {
            this.count = count;
        }

        @Override
        public void run() {
            count.increment();
        }
    }

    @Test
    public void when2IncrementsThen2() throws InterruptedException {
        final CASCount count = new CASCount();
        Thread first = new ThreadCASCount(count);
        Thread second = new ThreadCASCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(2));
    }

}