package sample;

import sample.buildings.Building;
import sample.buildings.LoggersLodge;
import sample.buildings.LumberMill;
import sample.buildings.Quarry;

import java.io.Serializable;
import java.util.ArrayList;

public class BuildingsContainer implements Serializable {

    private int mapSize;
    private ArrayList<Building> buildingsList;

    public BuildingsContainer(int mapSize)
    {
        this.mapSize = mapSize;
        buildingsList = new ArrayList<>(mapSize);
    }

    public boolean add(Building building)
    {
        if(buildingsList.size() < mapSize)
        {
            buildingsList.add(building);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isBuildingCorrect()
    {
        boolean lumberMill = false;
        boolean quarry = false;
        boolean loggersLodge = false;

        for (Building building : buildingsList) {
            if(building instanceof LumberMill)
                lumberMill = true;
            if(building instanceof Quarry)
                quarry = true;
            if(building instanceof LoggersLodge)
                loggersLodge = true;
        }

        return (lumberMill && quarry && loggersLodge);
    }

    public Building getBuilding(int xPos, int yPos) {
        for(Building building :buildingsList)
        {
            if(building.getPosition().getxPos() == xPos && building.getPosition().getyPos() == yPos)
                return building;
        }
        return null;
    }
}
