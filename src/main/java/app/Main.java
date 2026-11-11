package app;

import controller.ControladorRecord;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.RepositorioJugador;
import view.VistaRecord;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        VistaRecord vista = new VistaRecord();
        RepositorioJugador repositorio = new RepositorioJugador("record.txt");
        new ControladorRecord(vista, repositorio);

        stage.setTitle("Récord del jugador");
        stage.setScene(new Scene(vista.getRaiz(), 380, 320));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}