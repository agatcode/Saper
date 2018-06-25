package saper;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import static saper.Main.FIELD_SIZE;

class Piece extends StackPane {
    private int x, y;
    private boolean isBomb;
    private boolean checked = false;
    private Text piecePrint;
    private String neighboursBombsCount;
    private Rectangle picIcon;
    private boolean gameOver = false;

    public Piece(int x, int y, boolean isBomb){
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;

        piecePrint = new Text("o");
        piecePrint.setVisible(false);

        picIcon = new Rectangle(FIELD_SIZE - 1, FIELD_SIZE - 1);
        picIcon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.GREY)}));

        this.getChildren().addAll(picIcon, piecePrint);

        setOnMouseClicked(event -> check(event));

        setTranslateX(FIELD_SIZE * x);
        setTranslateY(FIELD_SIZE * y);
    }

    public boolean getGameOver(){
        return this.gameOver;
    }

    public boolean getIsBomb(){
        return this.isBomb;
    }

    public void setPiecePrint(String txt){
        this.piecePrint.setText(txt);
    }

    public void setNeighboursBombsCount(int count){
        this.neighboursBombsCount = String.valueOf(count);
    }

    void check(MouseEvent e){
        if ((this.piecePrint.visibleProperty().getValue()==true)
            && (this.piecePrint.getText() != "o"))
            return;

        if ((e.getButton() == MouseButton.SECONDARY)){
            this.piecePrint.setVisible(oppositeBoolValue(this.piecePrint.visibleProperty().getValue()));
        }
        else{
            if(isBomb){
             this.picIcon.setFill(Color.RED);
             gameOver();
             return;
            }
            this.piecePrint.setText(this.neighboursBombsCount);
            this.piecePrint.setVisible(true);
            this.piecePrint.setDisable(true);
        }
    }

    private boolean oppositeBoolValue(boolean bool){
        if (bool == true) return false;
        else return true;
    }

    private void gameOver(){
        Stage gameOverPopup = new Stage();
        gameOverPopup.setResizable(false);
        gameOverPopup.setTitle("Boom!");

        Text gameOverTxt = new Text("BOOOOM! Game Over!");
        gameOverTxt.setVisible(true);
        BorderPane popupPane = new BorderPane();

        popupPane.setCenter(gameOverTxt);

        gameOverPopup.setScene(
                new Scene(popupPane, 200, 100));
        gameOverPopup.show();
    }
}