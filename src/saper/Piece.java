package saper;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import static saper.Main.FIELD_SIZE;

class Piece extends StackPane {
    private int x, y;
    private boolean isBomb;
    private int neighboursBombs = 0;
    private boolean checked = false;
    private Label neighbourBombsCount = new Label();
    private Text piecePrint;

    public Piece(int x, int y, boolean isBomb){
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;

        piecePrint = new Text("o");
        piecePrint.setVisible(false);

        Rectangle picIcon = new Rectangle(FIELD_SIZE - 1, FIELD_SIZE - 1);
        picIcon.setFill(new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE,
                new Stop(1, Color.GREY),
                new Stop(0, Color.WHITE)));
        picIcon.setStroke(Color.BLACK);

        this.getChildren().addAll(picIcon, piecePrint);

        setOnMouseClicked(event -> check(event));
    }

    void check(MouseEvent e){
        if ((this.piecePrint.visibleProperty().getValue()==true)
            && (this.piecePrint.getText() != "o"))
            return;

        if ((e.getButton() == MouseButton.SECONDARY)){
            this.piecePrint.setVisible(oppositeBoolValue(this.piecePrint.visibleProperty().getValue()));
        }
        else{

        }

    }

    private boolean oppositeBoolValue(boolean bool){
        if (bool == true) return false;
        else return true;
    }

    private void checkNeighbours(ArrayList<Piece> neighbours){

    }

    private void gameOver(){
        Stage gameOverPopup = new Stage();
        Pane popupPane = new Pane();

        popupPane.getChildren().add(new Text("Game Over!"));
        Scene dialogScene = new Scene(popupPane, 200, 100);

        gameOverPopup.setScene(dialogScene);
        gameOverPopup.show();
    }
}