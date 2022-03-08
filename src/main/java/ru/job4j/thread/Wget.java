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
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong arguments. Arguments should be like <file_url> <download speed: bytes in sec>, <file_name>");
        }
        return false;
    }

    @Override
    public void run() {
        try (BufferedInputStream reader = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream writer = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int downloadSpeed = 0;
            long start = System.currentTimeMillis();
            while ((bytesRead = reader.read(dataBuffer, 0, 1024)) != -1) {
                writer.write(dataBuffer, 0, bytesRead);
                downloadSpeed += bytesRead;
                if (downloadSpeed >= speed) {
                    long time = System.currentTimeMillis() - start;
                    if (1000 > time) {
                        Thread.sleep(1000 - time);
                    }
                }
                start = System.currentTimeMillis();
                downloadSpeed = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String file = args[2];
        Thread wget = new Thread(new Wget(url, speed, file));
        wget.start();
        wget.join();
    }
}
