package saper;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

import static saper.Main.NUM_OF_FIELDS;

public class BatterfieldSetUp {

    static Boolean gameFinished = false;
    static int openedPieces = 0;
    static int bombsInGame = 0;

    static boolean plantBomb(){
        Random rand = new Random();
        int generatedNum;

        generatedNum = rand.nextInt(5);
        if (generatedNum == 0){
            bombsInGame++;
            return true;
        }
        return false;
    }

    static int countNeighboursBombs(int x, int y, Piece[][] pieceTab){
        ArrayList<Piece> neighboursList = new ArrayList<>();
        int bombsCount = 0;

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

    static void gameEnd(Boolean gameOver){
        Stage gameEndPopup = new Stage();
        Text gameEndTxt;
        gameEndPopup.setResizable(false);
        if (gameOver){
            gameEndPopup.setTitle("Boom!");
            gameEndTxt = new Text("BOOOOM! Game Over!");
        }
        else{
            gameEndPopup.setTitle("Congrats!");
            gameEndTxt = new Text("You won!");
        }

        gameEndTxt.setVisible(true);
        BorderPane popupPane = new BorderPane();

        popupPane.setCenter(gameEndTxt);

        gameEndPopup.setScene(
                new Scene(popupPane, 200, 100));
        gameEndPopup.show();

        gameFinished = true;

    }

}

