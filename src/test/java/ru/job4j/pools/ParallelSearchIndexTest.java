package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParallelSearchIndexTest {
    private static Integer[] getArray(int capacity) {
        Integer[] array = new Integer[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = i;
        }
        return array;
    }

    @Test
    public void whenIndexWasFound() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer[] array = getArray(100);
        assertThat(ParallelSearchIndex.findIndex(array, 15), is(15));
    }

    @Test
    public void whenIndexWasNotFound() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer[] array = getArray(20);
        assertThat(ParallelSearchIndex.findIndex(array, 70), is(-1));
    }
}