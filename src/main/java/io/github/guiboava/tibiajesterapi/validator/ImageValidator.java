package io.github.guiboava.tibiajesterapi.validator;

import io.github.guiboava.tibiajesterapi.entity.model.Image;
import io.github.guiboava.tibiajesterapi.repository.ImageRepository;
import io.github.guiboava.tibiajesterapi.util.ImagePlaceholderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageValidator {

    private final ImageRepository imageRepository;

    public void validate(Image image) {

        linkedToHunt(image);

    }

    private void linkedToHunt(Image image) {

    }

}
