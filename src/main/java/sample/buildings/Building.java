package sample.buildings;

import java.io.Serializable;

public abstract class Building implements Serializable {

    private int cost;
    private int income;
    private Position position;

    public Building(int cost, int income, Position position)
    {
        this.cost = cost;
        this.income = income;
        this.position = position;
        build();
    }

    protected abstract void build();

    public int getCost() {
        return cost;
    }

    public int getIncome() {
        return income;
    }

    public Position getPosition() {
        return position;
    }
}
