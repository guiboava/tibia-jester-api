package io.github.guiboava.tibiajesterapi.util;

import io.github.guiboava.tibiajesterapi.entity.model.Image;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public  class ImageURLBuilderUtils {

    public ImageURLBuilderUtils() {

    }

    public static URI buildImageURL(Image image) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/images/{id}")
                .buildAndExpand(image.getId())
                .toUri();
    }

}
