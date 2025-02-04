package controller;

import dao.ProdutoDAO;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import model.entity.Produto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CatalogoController implements Initializable {
    @FXML
    private GridPane catalogoGridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Obtém as dimensões da tela principal
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            double screenWidth = screenBounds.getWidth();
            double screenHeight = screenBounds.getHeight();

            // Obtém a altura da barra de título
            double barraTituloAltura = 70; // Valor aproximado da altura da barra de título (pode variar dependendo do sistema)

            // Ajusta a altura da janela para garantir que a barra de título não seja coberta
            double alturaAjustada = screenHeight - barraTituloAltura;

            catalogoGridPane.setPrefHeight(alturaAjustada);
            catalogoGridPane.setPrefWidth(screenWidth);
            catalogoGridPane.setHgap(10);  // Espaço horizontal entre os cards
            catalogoGridPane.setVgap(10);  // Espaço vertical entre os cards
            catalogoGridPane.setStyle("-fx-padding: 10;");

            // Limpar colunas e linhas do GridPane
            catalogoGridPane.getColumnConstraints().clear();
            catalogoGridPane.getRowConstraints().clear();

            List<Produto> produtos = ProdutoDAO.listarProdutos();
            int numCols = 3;  // Defina o número de colunas desejado (por exemplo, 3)
            int numRows = (int) Math.ceil(produtos.size() / (double) numCols);  // Calcula o número de linhas necessárias
            // Configurar colunas
            /*for (int i = 0; i < numCols; i++) {
                ColumnConstraints col2 = new ColumnConstraints();
                col2.setHgrow(Priority.ALWAYS);  // Garante que as colunas cresçam conforme necessário
                col2.setPercentWidth(100.0 / numCols);  // Divide igualmente a largura
                catalogoGridPane.getColumnConstraints().add(col2);
            }

            // Configurar linhas
            for (int i = 0; i < numRows; i++) {
                RowConstraints row2 = new RowConstraints();
                row2.setVgrow(Priority.ALWAYS);  // Garante que as linhas cresçam conforme necessário
                row2.setPercentHeight(100.0 / numRows);  // Divide igualmente a altura
                catalogoGridPane.getRowConstraints().add(row2);
            }*/

            int row = 0;
            int col = 0;

            for (Produto produto : produtos) {
                // Adiciona os cards no GridPane com a lógica de posicionamento em linhas e colunas
                VBox card = criarCardProduto(produto);
                // Ajuste do tamanho do card para ocupar toda a célula
                card.setMaxWidth(Double.MAX_VALUE);
                card.setMaxHeight(Double.MAX_VALUE);
                catalogoGridPane.add(card, col, row);

                col++;
                if(col > numCols){
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    VBox criarCardProduto(Produto produto) {
        VBox card = new VBox();
        card.setStyle("-fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-color: #f9f9f9; -fx-background-radius: 5;");
        card.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView(new Image("icon/image.png"));
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true); // Mantém a proporção da imagem

        Label nomeLabel = new Label(produto.getNome());
        nomeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label precoLabel = new Label("R$ " + String.format("%.2f", produto.getPreco()));
        precoLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");

        Label descricaoLabel = new Label(produto.getDescricao());
        descricaoLabel.setWrapText(true);

        card.getChildren().addAll(imageView,nomeLabel, precoLabel, descricaoLabel);
        return card;
    }
}
