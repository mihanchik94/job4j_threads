package ru.job4j.thread;

import java.io.*;
import java.net.URL;

public class Wget implements Runnable {
   private final String url;
   private final int speed;
   private final String file;

    public Wget(String url, int speed, String file) {
        this.url = url;
        this.speed = speed;
        this.file = file;
    }

    public static boolean validate(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Specify the URL, speed and the file");
        }
        if (args[0] == null) {
            throw  new IllegalArgumentException("Incorrect URL");
        }
        if (args[1] == null) {
            throw  new IllegalArgumentException("Incorrect speed");
        }
        if (args[2] == null) {
            throw  new IllegalArgumentException("Incorrect file");
        }

        return false;
    }

    @Override
    public void run() {
        try (BufferedInputStream reader = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream writer = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long expectedTime = 1024 * 1000 / speed;
            long start = System.currentTimeMillis();
            while ((bytesRead = reader.read(dataBuffer, 0, 1024)) != -1) {
                long time = System.currentTimeMillis() - start;
                writer.write(dataBuffer, 0, bytesRead);
                if (expectedTime < time) {
                    Thread.sleep(expectedTime - time);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String file = args[2];
        validate(args);
        Thread wget = new Thread(new Wget(url, speed, file));
        wget.start();
    }
}
