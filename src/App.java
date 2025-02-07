import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Obtém as dimensões da tela principal
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Obtém a altura da barra de título
        double barraTituloAltura = 70; // Valor aproximado da altura da barra de título (pode variar dependendo do sistema)

        // Ajusta a altura da janela para garantir que a barra de título não seja coberta
        double alturaAjustada = screenHeight - barraTituloAltura;

        // Carrega o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));

        Scene scene = new Scene(root/*, screenWidth,alturaAjustada*/);
        System.out.println(screenWidth + " " + screenHeight);

        // Configura o título e a cena
        stage.setTitle("TelaPrincipal");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args); // Inicia o aplicativo JavaFX
    }
}