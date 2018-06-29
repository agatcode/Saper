package saper;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static saper.Main.FIELD_SIZE;

class Piece extends StackPane {
    private int x, y;
    private boolean isBomb;
    private Text piecePrint;
    private String neighboursBombsCount;
    private Rectangle picIcon;

    public Piece(int x, int y, boolean isBomb){
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;

        piecePrint = new Text("x");
        piecePrint.setVisible(false);

        picIcon = new Rectangle(FIELD_SIZE - 1, FIELD_SIZE - 1);
        picIcon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.GREY)}));

        this.getChildren().addAll(picIcon, piecePrint);

        setOnMouseClicked(event -> check(event));

        setTranslateX(FIELD_SIZE * x);
        setTranslateY(FIELD_SIZE * y);
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
        if (BatterfieldSetUp.gameOver) {
            this.setDisable(true);
            return;
        }

        if ((this.piecePrint.visibleProperty().getValue()==true)
            && (this.piecePrint.getText() != "x"))
            return;

        if ((e.getButton() == MouseButton.SECONDARY)){
            this.piecePrint.setVisible(oppositeBoolValue(this.piecePrint.visibleProperty().getValue()));
        }
        else{
            if(isBomb){
             this.picIcon.setFill(Color.RED);
             BatterfieldSetUp.gameOver();
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

}