package ru.itis.inform.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.itis.inform.interfaces.Armor;
import ru.itis.inform.interfaces.Warrior;
import ru.itis.inform.interfaces.Weapon;

/**
 * Created by artur on 14.02.2017.
 */

@Component
public class Geralt implements Warrior {

    @Qualifier("sword")
    @Autowired
    Weapon weapon;

    @Autowired
    Armor armor;

    public Geralt() {
    }

    public Geralt(Weapon weapon, Armor armor) {
        this.weapon = weapon;
        this.armor = armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void attack() {
        System.out.println("Attack with " + weapon.getDamage());
    }

    public void defend() {
        System.out.println("Defend with " + armor.getArmor());
    }
}
