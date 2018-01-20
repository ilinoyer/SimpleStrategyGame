package sample.save;

import sample.Player;

import java.util.*;

public class CareTaker {
    private Deque<Memento> mementoList;

    public CareTaker()
    {
        mementoList = new ArrayDeque<>() {
        };
    }

    public void add(Memento memento)
    {
        mementoList.add(memento);
    }

    public Memento get()
    {
        Memento memento = mementoList.getLast();
        mementoList.removeLast();
        return memento;
    }
}
