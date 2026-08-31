package io.github.guiboava.tibiajesterapi.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class ImagePlaceholderUtils {

    private static final byte[] IMAGE;

    static {
        try {
            ClassPathResource resource =
                    new ClassPathResource("images/tibia_jester_no_image.png");

            IMAGE = resource.getInputStream().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar imagem placeholder", e);
        }
    }

    private void ImagePlaceholder() {
    }

    public static byte[] getImage() {
        return IMAGE;
    }
}



