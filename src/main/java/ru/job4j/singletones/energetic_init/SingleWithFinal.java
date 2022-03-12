package ru.job4j.singletones.energetic_init;

import ru.job4j.cache.Cache;

public class SingleWithFinal {
    private static final SingleWithFinal INSTANCE = new SingleWithFinal();

    public SingleWithFinal() {
    }

    public static SingleWithFinal getINSTANCE() {
        return INSTANCE;
    }

    public Cache add(Cache model) {
        return model;
    }

    public static void main(String[] args) {
        SingleWithFinal single = SingleWithFinal.getINSTANCE();
    }
}
