package ru.job4j.pool;

import org.junit.Test;
import ru.job4j.cas.CASCount;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {
    @Test
    public void whenStartThreadPool() throws InterruptedException {
        CASCount count = new CASCount();
        ThreadPool pool = new ThreadPool();
        for (int i = 0; i < 5; i++) {
            pool.work(
                    count::increment
            );
        }
        Thread.sleep(3000);
        pool.shutdown();
        assertThat(count.get(), is(5));
    }
}