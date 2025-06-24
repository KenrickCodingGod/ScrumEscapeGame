package game.voorwerp;

import game.Monster;

public interface Weapon {
    String attack(Monster monster);
    String getNaam();
}
