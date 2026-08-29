package io.github.guiboava.tibiajesterapi.util;

import org.mapstruct.Named;

public class NumberUtils {

    public NumberUtils() {
    }

    @Named("onlyDigits")
    public static String onlyDigits(String value) {
        return value != null ? value.replaceAll("\\D", "") : null;
    }
}