package sample.buildings;

public class GoldMine extends Building {

    public GoldMine(Position position) {
        super(5000,100, position);
    }

    @Override
    protected void build() {
        System.out.println("Buduje kopalnie złota");
    }
}
