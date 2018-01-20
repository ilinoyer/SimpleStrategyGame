package sample.save;

import sample.Player;

public class Memento {
    private Player player;

    public Memento(Player player)
    {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
