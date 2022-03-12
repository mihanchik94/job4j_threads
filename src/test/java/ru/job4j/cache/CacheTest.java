package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CacheTest {
    @Test
    public void whenAddThenTrue() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        Base second = new Base(2, 1);
        assertTrue(cache.add(first));
        assertTrue(cache.add(second));
    }

    @Test
    public void whenAddThenFalse() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        Base second = new Base(1, 1);
        assertTrue(cache.add(first));
        assertFalse(cache.add(second));
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        cache.add(first);
        cache.delete(first);
        assertThat(cache.size(), is(0));
    }

    @Test
    public void whenUpdateThenTrue() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        Base second = new Base(1, 1);
        first.setName("Audi");
        second.setName("Mercedes");
        cache.add(first);
        cache.update(second);
        assertThat(cache.getMemory().get(1).getVersion(), is(2));
        assertThat(cache.getMemory().get(1).getName(), is("Mercedes"));
    }

    @Test (expected = OptimisticException.class)
    public void whenUpdateNotEqualsVersions() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        Base second = new Base(1, 2);
        first.setName("Audi");
        second.setName("Mercedes");
        cache.add(first);
        cache.update(second);
    }
}