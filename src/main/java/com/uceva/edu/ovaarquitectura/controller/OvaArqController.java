package com.uceva.edu.ovaarquitectura.controller;

import com.uceva.edu.ovaarquitectura.service.OvaArqService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ova")
public class OvaArqController {
    private final OvaArqService ovaArqService;

    public OvaArqController(OvaArqService ovaArqService) {
        this.ovaArqService = ovaArqService;
    }

    // ✅ Obtener una pregunta aleatoria
    @GetMapping("/pregunta")
    public String obtenerPreguntaAleatoria() {
        return ovaArqService.obtenerPreguntaAleatoria();
    }

    // ✅ Guardar una nueva pregunta (JSON en body)
    @PostMapping("/pregunta")
    public void guardarPregunta(@RequestBody String jsonPregunta) {
        ovaArqService.guardarPregunta(jsonPregunta);
    }

    // ✅ Validar una respuesta
    @GetMapping("/validar/{id}/{indice}")
    public String validarRespuesta(@PathVariable String id, @PathVariable int indice) {
        return ovaArqService.validarRespuestaPorIndice(id, indice);
    }

    // ✅ Endpoint de prueba
    @GetMapping("/test")
    public String test() {
        return "{ \"estado\": \"OK\", \"mensaje\": \"Servicio OVA Arq activo\" }";
    }
}
