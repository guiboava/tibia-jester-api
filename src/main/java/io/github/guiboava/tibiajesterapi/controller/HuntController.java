package io.github.guiboava.tibiajesterapi.controller;

import io.github.guiboava.tibiajesterapi.service.HuntService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hunts")
@RequiredArgsConstructor
@Tag(name = "Hunts")
@Slf4j
public class HuntController implements GenericController{

    private final HuntService service;

}
