package sample.buildings;

import java.io.Serializable;

public class Position implements Serializable{
    private int xPos;
    private int yPos;

    public Position(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
