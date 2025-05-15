package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Speler {
    private int positie = 0;
    private final List<Monster> monsters = new ArrayList<>();
    private final List<SpelerObserver> observers = new ArrayList<>();

    public int getPositie() {
        return positie;
    }

    public void setPositie(int positie) {
        this.positie = positie;
        notifyObservers(); // meld aan observers
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void voegMonsterToe(Monster monster) {
        monsters.add(monster);
        monster.toonMonster();
        notifyObservers(); // meld wijziging aan observers
    }

    public String getMonsterNamenAlsString() {
        if (monsters.isEmpty()) return "Geen";
        return monsters.stream()
                .map(Monster::getNaam)
                .collect(Collectors.joining(", "));
    }

    // 🔔 Observer functionaliteit
    public void voegObserverToe(SpelerObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (SpelerObserver o : observers) {
            o.update(this);
        }
    }
}
