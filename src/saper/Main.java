package saper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    static final int FIELD_SIZE = 30;
    static final int NUM_OF_FIELDS = 8;
    Piece[][] piecesArray = new Piece[NUM_OF_FIELDS][NUM_OF_FIELDS];

    private BorderPane createTopMenu(){
        BorderPane menu = new BorderPane();
        Text mainTxt = new Text("SAPER");
        InnerShadow inSh = new InnerShadow();
        Button restartBtn = new Button("Restart");
        restartBtn.setOnMouseClicked(e -> restart());

        mainTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        mainTxt.setStroke(Color.WHITESMOKE);
        mainTxt.setFill(Color.WHITESMOKE);
        mainTxt.setEffect(inSh);
        menu.setPadding(new Insets(10,10,10,10));
        menu.setStyle("-fx-background-color: WHITE");

        menu.setRight(restartBtn);
        menu.setCenter(mainTxt);

        return menu;
    }

    private Parent createGameScene(){
        BorderPane gameScene = new BorderPane();
        gameScene.setTop(createTopMenu());

        Pane batterField = new Pane();
        batterField.setPrefSize(NUM_OF_FIELDS * FIELD_SIZE, NUM_OF_FIELDS * FIELD_SIZE);

        for (int i = 0; i < NUM_OF_FIELDS; i++){
            for (int j=0; j< NUM_OF_FIELDS; j++){
                piecesArray[i][j] = new Piece(i,j, BatterfieldSetUp.plantBomb());
                batterField.getChildren().add(piecesArray[i][j]);
            }
        }

        for (int i = 0; i < NUM_OF_FIELDS; i++){
            for (int j=0; j< NUM_OF_FIELDS; j++){
                piecesArray[i][j].setNeighboursBombsCount(BatterfieldSetUp.countNeighboursBombs(i, j, piecesArray));
            }
        }

        gameScene.setCenter(batterField);

        return gameScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("saper.fxml"));
        primaryStage.setTitle("Bombowa Gra");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(createGameScene(), FIELD_SIZE * NUM_OF_FIELDS, FIELD_SIZE * NUM_OF_FIELDS + 45));
        primaryStage.show();
    }

    private void restart(){
        //to be implemented
    }

    public static void main(String[] args) {
        launch(args);
    }
}
