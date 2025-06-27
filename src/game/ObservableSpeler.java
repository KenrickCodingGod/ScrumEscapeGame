package game;

import game.observer.SpelerObserver;

import java.util.ArrayList;
import java.util.List;

public class ObservableSpeler {
    private final List<SpelerObserver> observers = new ArrayList<>();

    public void attach(SpelerObserver observer) {
        observers.add(observer);
    }

    protected void notifyObservers(Speler speler) {
        for (SpelerObserver observer : observers) {
            observer.update(speler);
        }
    }
}
