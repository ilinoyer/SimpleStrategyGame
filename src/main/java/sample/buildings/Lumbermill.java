package sample.buildings;

public class Lumbermill extends Building {

    public Lumbermill(Position position)
    {
        super(3000,500, position);
    }

    @Override
    protected void build() {
        System.out.println("Buduje Tartak");
    }
}
