/**
 * @author federico colombo
 * @version 1.0
 * @file OBJ_boostSpeed.java
 * @brief gestione del oggetto boost speed
 */
package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @author Colombo Federico
 * @version 1.0
 * @class OBJ_boostSpeed
 * @brief gestione dell'oggetto per l'aumento della velocità
 */
public class OBJ_boostSpeed extends SuperObject {
    /**
     * metodo per la gestione del boost speed per l'aumento di velocità
     *
     * @param type riceve come parametro il tipo del boost speed
     * @brief costruttore parametrico OBJ_boostSpeed()
     * @since 1.0
     */
    public OBJ_boostSpeed(String type) {


        name = type;
        try {
            image = ImageIO.read(new File("resources/boostSpeed/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
