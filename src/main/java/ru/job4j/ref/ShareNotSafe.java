package ru.job4j.ref;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("user");
        cache.add(user);
                Thread first = new Thread(
                        () -> user.setName("rename")
        );
                first.start();
                first.join();
    }
}
