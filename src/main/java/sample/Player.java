package sample;

import sample.buildings.Building;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    private int money;
    private int totalIncome;
    private int turnCounter;
    private BuildingsContainer buildingsContainer;

    public Player(int mapSize) {
        this.money = 2000;
        this.buildingsContainer = new BuildingsContainer(mapSize);
    }

    public int getMoney() {
        return money;
    }

    public void collectIncome()
    {
        money += totalIncome;
        turnCounter += 1;
    }

    public void addBuilding(Building building)
    {
        if(buildingsContainer.add(building))
        {
            totalIncome += building.getIncome();
            money -= building.getCost();
            System.out.printf("dodano");
        }
    }

    public boolean isBuildingCorrect()
    {
       return buildingsContainer.isBuildingCorrect();
    }

    public int getIncome() {
        return totalIncome;
    }

    public int getTurnCounter() {
        return turnCounter;
    }
}
