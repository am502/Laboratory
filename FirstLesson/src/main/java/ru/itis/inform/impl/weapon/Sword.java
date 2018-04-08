package ru.itis.inform.impl.weapon;

import org.springframework.stereotype.Component;
import ru.itis.inform.interfaces.Weapon;

/**
 * Created by artur on 14.02.2017.
 */

@Component
public class Sword implements Weapon {

    public String getDamage() {
        return "Sword (100 damage)";
    }

}
