package ru.job4j.singletones.lazy_init;

import ru.job4j.cache.Cache;

public class SingleHolder {
    public SingleHolder() {
    }

    private static final class HOLDER {
        private static final SingleHolder INSTANCE = new SingleHolder();
    }

    public static SingleHolder getInstance() {
        return HOLDER.INSTANCE;
    }

    public Cache add(Cache model) {
        return model;
    }

    public static void main(String[] args) {
        SingleHolder singleHolder = SingleHolder.getInstance();
    }
}
