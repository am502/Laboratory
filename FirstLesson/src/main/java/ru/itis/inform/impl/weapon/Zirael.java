package ru.itis.inform.impl.weapon;

import org.springframework.stereotype.Component;
import ru.itis.inform.interfaces.Weapon;

/**
 * Created by artur on 15.02.2017.
 */

@Component
public class Zirael implements Weapon {

    public String getDamage() {
        return "Zirael (40 damage)";
    }
}
