package io.github.guiboava.tibiajesterapi.service;

import io.github.guiboava.tibiajesterapi.controller.mappers.HuntMapper;
import io.github.guiboava.tibiajesterapi.repository.HuntRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HuntService {

    private final HuntRepository huntRepository;
    private final HuntMapper huntMapper;

}
