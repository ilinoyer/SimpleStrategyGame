package sample.buildings;

public class LoggersLodge extends Building {


    public LoggersLodge(Position position) {
        super(1500,300, position);
    }

    @Override
    protected void build() {
        System.out.println("Buduje Chatkę drwala");
    }

    @Override
    public String toString() {
        return "Chatka Drwala";
    }
}
