package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static ru.job4j.pools.RolColSum.*;
import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void whenSum() {
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        Sums[] expected = {new Sums(6, 12), new Sums(15, 15), new Sums(24, 18)};
        assertThat(RolColSum.sum(array), is(expected));
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        Sums[] expected = {new Sums(6, 12), new Sums(15, 15), new Sums(24, 18)};
        assertThat(RolColSum.asyncSum(array), is(expected));
    }
}