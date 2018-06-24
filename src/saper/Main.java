package saper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Random;

public class Main extends Application {

    static final int FIELD_SIZE = 30;
    static final int NUM_OF_FIELDS = 8;
    int bombsCountInGame = 0;
    Piece[][] piecesArray = new Piece[NUM_OF_FIELDS][NUM_OF_FIELDS];

    private HBox createTopMenu(){
        HBox menu = new HBox();
        menu.setPadding(new Insets(10,10,10,10));
        menu.setStyle("-fx-background-color: #730099");
        menu.setSpacing(10);

        Button btnRestart = new Button("Restart");

        TextField tfPoints = new TextField("0");
        tfPoints.setEditable(false);
        tfPoints.setMaxWidth(50);

        menu.getChildren().addAll(btnRestart, tfPoints);

        return menu;
    }

    private Parent createGameScene(){
        BorderPane gameScene = new BorderPane();
        gameScene.setTop(createTopMenu());

        Pane batterField = new Pane();
        gameScene.setPrefSize(NUM_OF_FIELDS * FIELD_SIZE, NUM_OF_FIELDS * FIELD_SIZE);

        for (int i = 0; i < NUM_OF_FIELDS; i++){
            for (int j=0; j< NUM_OF_FIELDS; j++){
                piecesArray[i][j] = new Piece(i,j,assignBombOrNot());
                batterField.getChildren().add(piecesArray[i][j]);
            }
        }

        gameScene.setCenter(batterField);

        return gameScene;
    }

    private boolean assignBombOrNot(){
        boolean isBomb;
        Random isBombRand = new Random();
        if (bombsCountInGame > 10) return false;
        else return(isBombRand.nextBoolean());
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("saper.fxml"));
        primaryStage.setTitle("Bombowa Gra");
        primaryStage.setScene(new Scene(createGameScene(), 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
