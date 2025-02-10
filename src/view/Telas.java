
package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas extends Application {

    private static Stage stage;

    private static Scene mainScene;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GerenciamentoClientes.fxml"));

        mainScene = new Scene(root);

        stage.setTitle("Gerenciamento Clientes");
        stage.setResizable(false);
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}