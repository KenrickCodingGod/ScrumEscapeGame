package game;

import game.joker.Joker;

import game.kamer.Kamer;
import game.observer.GameStatusObserver;
import game.observer.GameStatusView;
import game.observer.SpelerObserver;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Speler {
    private Kamer huidigeKamer;
    private final List<Monster> monsters = new ArrayList<>();
    private final List<SpelerObserver> observers = new ArrayList<>();

    private Joker gekozenJoker;
    private boolean jokerGebruikt = false;
    private boolean kamerOvergeslagen = false;

    public Kamer getHuidigeKamer() {
        return huidigeKamer;
    }

    public void setHuidigeKamer(Kamer kamer) {
        this.huidigeKamer = kamer;
        notifyObservers();

        for (SpelerObserver observer : observers) {
            if (observer instanceof GameStatusObserver statusObserver) {
                GameStatusView view = new GameStatusView();
                view.toonStatus(statusObserver.getStatus());
            }
        }
    }

    public void voegMonsterToe(Monster monster) {
        monsters.add(monster);
        monster.toonMonster();
        notifyObservers();
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public String getMonsterNamenAlsString() {
        if (monsters.isEmpty()) return "Geen";
        return monsters.stream()
                .map(Monster::getNaam)
                .collect(Collectors.joining(", "));
    }
    public boolean heeftJoker() {
        return gekozenJoker != null;
    }

    public boolean jokerAlGebruikt() {
        return jokerGebruikt;
    }

    public Joker getGekozenJoker() {
        return gekozenJoker;
    }

    public void kiesJoker(Joker joker) {
        this.gekozenJoker = joker;
    }

    public boolean gebruikJoker(Kamer kamer) {
        if (heeftJoker() && !jokerGebruikt) {
            boolean mag = gekozenJoker.gebruik(this, kamer);
            if (mag) {
                jokerGebruikt = true;
                return true;
            }
        }
        return false;
    }
    public void setKamerOvergeslagen(boolean overgeslagen) {
        this.kamerOvergeslagen = overgeslagen;
    }

    public boolean isKamerOvergeslagen() {
        return kamerOvergeslagen;
    }

    public void voegObserverToe(SpelerObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (SpelerObserver o : observers) {
            o.update(this);
        }
    }
}
