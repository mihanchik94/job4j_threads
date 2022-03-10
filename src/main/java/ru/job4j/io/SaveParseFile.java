package ru.job4j.io;

import java.io.*;

public final class SaveParseFile {
    private final File file;

    public SaveParseFile(final File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < content.length(); i++) {
                outputStream.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
