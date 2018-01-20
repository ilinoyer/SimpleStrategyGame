package sample.save;

import sample.Player;

public class Originator {
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Memento save()
    {
        return new Memento(player);
    }

    public void restore(Memento m)
    {
        player = m.getPlayer();
    }
}
