package ru.job4j.jcip;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    private UserStorage storage;

    @Before
    public void before() {
        storage = new UserStorage();
    }

    @After
    public void after() {
       storage = null;
    }

    @Test
    public void whenAddAndTrue() {
        assertTrue(storage.add(new User(1, 300)));
    }

    @Test
    public void whenAddAndFalse() {
        storage.add(new User(1, 300));
        assertFalse(storage.add(new User(1, 300)));
    }

    @Test
    public void whenUpdateAndTrue() {
        storage.add(new User(1, 300));
        assertTrue(storage.update(new User(1, 200)));
    }

    @Test
    public void whenUpdateAndFalse() {
        assertFalse(storage.update(new User(1, 200)));
    }

    @Test
    public void whenDeleteAndReturnTrue() {
        this.storage.add(new User(1, 200));
        assertTrue(this.storage.delete(new User(1, 200)));
    }

    @Test
    public void whenDeleteAndReturnFalse() {
        this.storage.add(new User(1, 200));
        assertFalse(this.storage.delete(new User(1, 400)));
    }

    @Test
    public void whenTransferAndTrue() {
        User user1 = new User(1, 200);
        User user2 = new User(2, 100);
        storage.add(user1);
        storage.add(user2);
        assertTrue(storage.transfer(1, 2, 50));
        assertThat(user1.getAmount(), is(150));
        assertThat(user2.getAmount(), is(150));
    }

    @Test
    public void whenTransferAndFalse() {
        User user1 = new User(1, 200);
        User user2 = new User(2, 100);
        storage.add(user1);
        storage.add(user2);
        assertFalse(storage.transfer(1, 2, 300));
    }
}