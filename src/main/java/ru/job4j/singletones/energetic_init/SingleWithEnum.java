package ru.job4j.singletones.energetic_init;

import ru.job4j.cache.Cache;

public enum SingleWithEnum {
    INSTANCE;

    public Cache add(Cache model) {
        return model;
    }

    public static void main(String[] args) {
        SingleWithEnum single = SingleWithEnum.INSTANCE;
    }

}
