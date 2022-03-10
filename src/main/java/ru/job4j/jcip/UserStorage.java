package ru.job4j.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public final class UserStorage {
    @GuardedBy("this")
    private Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return users.put(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return users.put(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User user1 = users.get(fromId);
        User user2 = users.get(toId);
        if (user1 != null && user2 != null && user1.getAmount() >= amount) {
            user1.setAmount(user1.getAmount() - amount);
            user2.setAmount(user2.getAmount() + amount);
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "UserStorage{"
                + "users="
                + users
                + '}';
    }

    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        storage.transfer(1, 2, 50);
        System.out.println(storage);
    }
}