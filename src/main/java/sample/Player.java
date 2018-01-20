package sample;

import sample.buildings.Building;

import java.util.ArrayList;

public class Player {

    private int money;
    private BuildingsContainer buildingsContainer;

    public Player(int mapSize)
    {
        this.money = 2000;
        this.buildingsContainer = new BuildingsContainer(mapSize);
    }
}
