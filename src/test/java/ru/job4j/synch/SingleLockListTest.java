package ru.job4j.synch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SingleLockListTest {
    @Test
    public void add() throws InterruptedException {
        List<Integer> workList = new ArrayList<>();
        SingleLockList<Integer> list = new SingleLockList<>(workList);
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(()-> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> result = new TreeSet<>();
        list.iterator().forEachRemaining(result::add);
        assertThat(result, is(Set.of(1, 2)));
    }


}