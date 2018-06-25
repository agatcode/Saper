package saper;

import java.util.ArrayList;
import java.util.Random;

public class BatterfieldSetUp {

    static boolean plantBomb(){
        Random rand = new Random();
        int generatedNum;

        generatedNum = rand.nextInt(5);
        if(generatedNum == 0){
            return true;
        }
        return false;
    }

    static int countNeighboursBombs(int x, int y, Piece[][] pieceTab){
        int bombsCount = 0;
        ArrayList<Piece> neighboursList = new ArrayList<>();

        neighboursList.add(pieceTab[x+1][y]);
        neighboursList.add(pieceTab[x+1][y+1]);
        neighboursList.add(pieceTab[x][y+1]);

        if (x>0){
            neighboursList.add(pieceTab[x-1][y]);
            neighboursList.add(pieceTab[x-1][y+1]);
            if (y>0)
                neighboursList.add(pieceTab[x-1][y-1]);
        }
        if (y>0){
            neighboursList.add(pieceTab[x+1][y-1]);
            neighboursList.add(pieceTab[x][y-1]);
        }

        for (Piece p: neighboursList
             ) {
            if (p.getIsBomb()) bombsCount++;
        }

        return bombsCount;
    }

}

