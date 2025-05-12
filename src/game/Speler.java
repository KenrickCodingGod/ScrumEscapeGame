package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Speler {
    private int positie = 0;
    private List<Monster> monsters = new ArrayList<>();

    public int getPositie() {
        return positie;
    }

    public void setPositie(int positie) {
        this.positie = positie;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void voegMonsterToe(Monster monster) {
        monsters.add(monster);
        monster.toonMonster(); // Toon meteen uitleg van het monster
    }

    public String getMonsterNamenAlsString() {
        if (monsters.isEmpty()) return "Geen";
        return monsters.stream()
                .map(Monster::getNaam)
                .collect(Collectors.joining(", "));
    }
}
