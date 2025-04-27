package com.beiramar.beiramar.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disponibilidades")
@Tag(name = "Disponibilidade", description = "Endpoints relacionados a disponibilidade")
public class DisponibilidadeController {
}
