package sample.buildings;

public class Quarry extends Building {

    public Quarry(Position position)
    {
        super(500, 200, position);
    }

    @Override
    protected void build() {
        System.out.println("Buduje Kamieniołom");
    }

    @Override
    public String toString() {
        return "Kamieniołom";
    }
}
