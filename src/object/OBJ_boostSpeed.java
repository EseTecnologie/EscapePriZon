/**
 * @author  federico colombo
 * @version 1.0
 * @file OBJ_boostSpeed.java
 *
 * @brief gestione del oggetto boost speed
 *
 */
package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_boostSpeed extends SuperObject {
    /**
     *@brief  costruttore parametrico OBJ_boostSpeed()
     *
     * metodo per la gestione del boost speed per l'aumento di velocità
     *
     * @param type riceve come parametro il tipo del boost speed
     */
    public OBJ_boostSpeed(String type){


            name=type;
            try{
                image = ImageIO.read(new File("resources/boostSpeed/boots.png"));
            }catch (IOException e){
                e.printStackTrace();
            }

    }
}
