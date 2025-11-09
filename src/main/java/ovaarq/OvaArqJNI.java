package ovaarq;

import java.io.*;
import java.nio.file.*;

public class OvaArqJNI {

    static {
        try {
            // Cargar librería nativa desde resources/native/
            InputStream inputStream = OvaArqJNI.class.getClassLoader()
                    .getResourceAsStream("native/libovaarq.so");

            if (inputStream == null) {
                throw new FileNotFoundException("No se encontró libovaarq.so en el classpath.");
            }

            File tempFile = File.createTempFile("libovaarq", ".so");
            Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            tempFile.deleteOnExit();
            System.load(tempFile.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("Error cargando la librería nativa libovaarq.so", e);
        }
    }

    // --- Métodos nativos (de tu C) ---
    public native String obtenerPreguntaAleatoria();
    public native void guardarPregunta(String jsonStr);
    public native String validarRespuestaPorIndice(String id, int indice);
    public native void liberarMemoria(long ptr);
}