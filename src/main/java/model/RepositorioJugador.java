package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RepositorioJugador {

    private final Path archivo;

    public RepositorioJugador(String ruta) {
        this.archivo = Paths.get(ruta);
    }

    /**
     * Guarda el jugador. Formato del archivo:
     * línea 1 = nombre, línea 2 = puntaje.
     * Al abrir el archivo sin modo "append", se SOBRESCRIBE el registro anterior.
     */
    public void guardar(Jugador jugador) throws IOException {
        try (BufferedWriter escritor = Files.newBufferedWriter(archivo, StandardCharsets.UTF_8)) {
            escritor.write(jugador.getNombre());
            escritor.newLine();
            escritor.write(String.valueOf(jugador.getPuntaje()));
            escritor.newLine();
        }
    }

    /**
     * Lee el jugador guardado.
     * Lanza IOException si el archivo no existe, está incompleto o está dañado.
     */
    public Jugador cargar() throws IOException {
        try (BufferedReader lector = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String nombre = lector.readLine();
            String puntajeTexto = lector.readLine();

            if (nombre == null || puntajeTexto == null) {
                throw new IOException("El archivo está vacío o incompleto.");
            }

            try {
                int puntaje = Integer.parseInt(puntajeTexto.trim());
                return new Jugador(nombre, puntaje);
            } catch (NumberFormatException e) {
                throw new IOException("El puntaje guardado en el archivo no es un número válido.");
            }
        }
    }
}