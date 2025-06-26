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

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void voegMonsterToe(Monster monster) {
        monsters.add(monster);
        monster.toonMonster();
        notifyObservers();
    }

    public String getMonsterNamenAlsString() {
        if (monsters.isEmpty()) return "Geen";
        return monsters.stream()
                .map(Monster::getNaam)
                .collect(Collectors.joining(", "));
    }

    public void kiesJoker(Joker joker) {
        this.gekozenJoker = joker;
    }

    public boolean heeftJoker() {
        return gekozenJoker != null && !jokerGebruikt;
    }

    public void gebruikJoker(int kamerNummer) {
        if (heeftJoker() && gekozenJoker.magGebruikenInKamer(kamerNummer)) {
            gekozenJoker.gebruikInKamer(kamerNummer);
            jokerGebruikt = true;
        } else {
            System.out.println("Je kunt deze joker hier niet gebruiken of hebt hem al gebruikt.");
        }
    }

    public Joker getGekozenJoker() {
        return gekozenJoker;
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
