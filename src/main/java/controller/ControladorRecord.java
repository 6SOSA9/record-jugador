package controller;

import model.Jugador;
import model.RepositorioJugador;
import view.VistaRecord;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class ControladorRecord {

    private final VistaRecord vista;
    private final RepositorioJugador repositorio;

    public ControladorRecord(VistaRecord vista, RepositorioJugador repositorio) {
        this.vista = vista;
        this.repositorio = repositorio;

        // Conectamos los botones de la vista con los métodos del controlador
        vista.getBotonGuardar().setOnAction(e -> guardar());
        vista.getBotonCargar().setOnAction(e -> cargar());
    }

    private void guardar() {
        String nombre = vista.getCampoNombre().getText().trim();
        String puntajeTexto = vista.getCampoPuntaje().getText().trim();

        // Validación 1: nombre no vacío
        if (nombre.isEmpty()) {
            vista.mostrarMensaje("Error: el nombre no puede estar vacío.", true);
            return;
        }

        // Validación 2: puntaje debe ser un entero
        int puntaje;
        try {
            puntaje = Integer.parseInt(puntajeTexto);
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Error: el puntaje debe ser un número entero.", true);
            return;
        }

        // Validación 3: puntaje >= 0
        if (puntaje < 0) {
            vista.mostrarMensaje("Error: el puntaje debe ser mayor o igual a cero.", true);
            return;
        }

        // Si todo es válido, se guarda
        try {
            repositorio.guardar(new Jugador(nombre, puntaje));
            vista.mostrarMensaje("Datos guardados correctamente.", false);
        } catch (IOException e) {
            vista.mostrarMensaje("Error al guardar el archivo: " + e.getMessage(), true);
        }
    }

    private void cargar() {
        try {
            Jugador jugador = repositorio.cargar();
            vista.getCampoNombre().setText(jugador.getNombre());
            vista.getCampoPuntaje().setText(String.valueOf(jugador.getPuntaje()));
            vista.mostrarMensaje("Datos cargados correctamente.", false);
        } catch (NoSuchFileException e) {
            vista.mostrarMensaje("Error: todavía no existe un registro guardado.", true);
        } catch (IOException e) {
            vista.mostrarMensaje("Error al leer el archivo: " + e.getMessage(), true);
        }
    }
}