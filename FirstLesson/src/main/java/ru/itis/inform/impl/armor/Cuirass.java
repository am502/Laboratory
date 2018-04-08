package ru.itis.inform.impl.armor;

import org.springframework.stereotype.Component;
import ru.itis.inform.interfaces.Armor;

/**
 * Created by artur on 14.02.2017.
 */

@Component
public class Cuirass implements Armor {

    public String getArmor() {
        return "Cuirass (90 armor)";
    }

}
