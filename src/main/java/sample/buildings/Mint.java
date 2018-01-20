package sample.buildings;

public class Mint extends Building {

    public Mint(Position position)
    {
        super(10000, 3000, position);
    }

    @Override
    protected void build() {
        System.out.println("Buduje Mennice");
    }

    @Override
    public String toString() {
        return "Mennica";
    }
}
