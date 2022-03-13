package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T element;

    public ParallelSearchIndex(T[] array, int from, int to, T element) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            return search();
        }
        int mid = (from + to) / 2;
        ParallelSearchIndex<T> leftSide = new ParallelSearchIndex<>(array, from, mid, element);
        ParallelSearchIndex<T> rightSide = new ParallelSearchIndex<>(array, mid + 1, to, element);
        leftSide.fork();
        rightSide.fork();
        int leftArrayIndex = leftSide.join();
        int rightArrayIndex = rightSide.join();
        return Math.max(leftArrayIndex, rightArrayIndex);
    }

    public static <T> int findIndex(T[] array, T element) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelSearchIndex<>(
                array, 0, array.length - 1, element));
    }

    public int search() {
        int index = -1;
        for (int i = from; i <= to; i++) {
            if (array[i].equals(element)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
