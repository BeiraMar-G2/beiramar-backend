package com.beiramar.beiramar.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacotes")
@Tag(name = "Pacotes", description = "Endpoints relacionados a pacotes")
public class PacoteController {
}
