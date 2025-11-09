package com.uceva.edu.ovaarquitectura.service;

import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.*;

@Service
public class OvaArqService {

    static {
        try {
            // Cargar la librería nativa desde resources/native/
            InputStream inputStream = OvaArqService.class.getClassLoader()
                    .getResourceAsStream("native/libovaarq.so");

            if (inputStream == null) {
                throw new FileNotFoundException("No se encontró la librería nativa libovaarq.so en resources/native/");
            }

            File tempFile = File.createTempFile("libovaarq", ".so");
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            tempFile.deleteOnExit();
            System.load(tempFile.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("Error cargando la librería nativa OVA Arq", e);
        }
    }

    // --- Métodos que llaman al código nativo ---
    public String obtenerPreguntaAleatoria() {
        ovaarq.OvaArqJNI ova = new ovaarq.OvaArqJNI();
        return ova.obtenerPreguntaAleatoria();
    }

    public void guardarPregunta(String jsonPregunta) {
        ovaarq.OvaArqJNI ova = new ovaarq.OvaArqJNI();
        ova.guardarPregunta(jsonPregunta);
    }

    public String validarRespuestaPorIndice(String id, int indice) {
        ovaarq.OvaArqJNI ova = new ovaarq.OvaArqJNI();
        return ova.validarRespuestaPorIndice(id, indice);
    }

    public void liberarMemoria(long ptr) {
        ovaarq.OvaArqJNI ova = new ovaarq.OvaArqJNI();
        ova.liberarMemoria(ptr);
    }
}
