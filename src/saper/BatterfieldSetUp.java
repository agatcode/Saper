package saper;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

import static saper.Main.NUM_OF_FIELDS;

public class BatterfieldSetUp {

    static Boolean gameOver = false;

    static boolean plantBomb(){
        Random rand = new Random();
        int generatedNum;

        generatedNum = rand.nextInt(5);
        if (generatedNum == 0){
            return true;
        }
        return false;
    }

    static int countNeighboursBombs(int x, int y, Piece[][] pieceTab){
        int bombsCount = 0;
        ArrayList<Piece> neighboursList = new ArrayList<>();

        if (x < NUM_OF_FIELDS-1) {
            neighboursList.add(pieceTab[x+1][y]);
            if (y < NUM_OF_FIELDS-1)
                neighboursList.add(pieceTab[x+1][y+1]);
        }
        if (y < NUM_OF_FIELDS-1)
            neighboursList.add(pieceTab[x][y+1]);

        if (x>0){
            neighboursList.add(pieceTab[x-1][y]);
            if (y < NUM_OF_FIELDS-1)
                neighboursList.add(pieceTab[x-1][y+1]);
            if (y>0)
                neighboursList.add(pieceTab[x-1][y-1]);
        }
        if (y>0){
            if (x < NUM_OF_FIELDS-1)
                neighboursList.add(pieceTab[x+1][y-1]);
            neighboursList.add(pieceTab[x][y-1]);
        }

        for (Piece p: neighboursList
             ) {
            if (p.getIsBomb()) bombsCount++;
        }

        return bombsCount;
    }

    static void gameOver(){
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

        gameOver = true;

    }
}

