package sample;

import sample.buildings.Building;

import java.util.ArrayList;
import java.util.Collection;

public class BuildingsContainer {

    private int mapSize;
    private ArrayList<Building> buildingsList;

    public BuildingsContainer(int mapSize)
    {
        this.mapSize = mapSize;
        buildingsList = new ArrayList<>(mapSize);
    }

    public void remove(Building building)
    {
        buildingsList.remove(building);
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

    public boolean contains(Collection buildings)
    {
        return buildingsList.containsAll(buildings);
    }
}
