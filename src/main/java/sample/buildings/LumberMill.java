package sample.buildings;

public class LumberMill extends Building {

    public LumberMill(Position position)
    {
        super(3000,500, position);
    }

    @Override
    protected void build() {
        System.out.println("Buduje Tartak");
    }

    @Override
    public String toString() {
        return "Tartak";
    }
}
