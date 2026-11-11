package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VistaRecord {

    private final TextField campoNombre = new TextField();
    private final TextField campoPuntaje = new TextField();
    private final Button botonGuardar = new Button("Guardar");
    private final Button botonCargar = new Button("Cargar");
    private final Label etiquetaMensaje = new Label();
    private final VBox raiz = new VBox(10);

    public VistaRecord() {
        campoNombre.setPromptText("Nombre del jugador");
        campoPuntaje.setPromptText("Puntaje (entero >= 0)");

        HBox botones = new HBox(10, botonGuardar, botonCargar);
        botones.setAlignment(Pos.CENTER);

        etiquetaMensaje.setWrapText(true);

        raiz.setPadding(new Insets(20));
        raiz.getChildren().addAll(
                new Label("Nombre:"), campoNombre,
                new Label("Puntaje:"), campoPuntaje,
                botones,
                etiquetaMensaje
        );
    }

    public VBox getRaiz() { return raiz; }
    public TextField getCampoNombre() { return campoNombre; }
    public TextField getCampoPuntaje() { return campoPuntaje; }
    public Button getBotonGuardar() { return botonGuardar; }
    public Button getBotonCargar() { return botonCargar; }

    public void mostrarMensaje(String texto, boolean esError) {
        etiquetaMensaje.setText(texto);
        etiquetaMensaje.setStyle(esError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }
}