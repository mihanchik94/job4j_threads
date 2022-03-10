package ru.job4j.buffer;

public class Buffer {
    private StringBuilder buffer = new StringBuilder();

    public synchronized void add(int value) {
        System.out.println(value);
        buffer.append(value);
    }

    public synchronized String toString() {
        return buffer.toString();
    }
}
