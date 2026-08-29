package io.github.guiboava.tibiajesterapi.util;

public class BytesFormatUtils {

    public BytesFormatUtils() {
    }

    public static String formatBytes(Long size) {

        if (size < 1024) {
            return size + "B";
        }
        if (size < 1024 * 1024) {
            return String.format("%.2f KB", size / 1024.0);
        }
        if (size < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", size / (1024.0 * 1024.0));
        }

        return String.format("%.2f GB",
                size / (1024.0 * 1024.0 * 1024.0));

    }

}
