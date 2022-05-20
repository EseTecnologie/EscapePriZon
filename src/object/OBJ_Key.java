/**
 * @author  federico colombo
 * @version 1.0
 * @file OBJ_Key.java
 *
 * @brief gestione del oggetto key
 *
 */
package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * @author Colombo Federico
 * @version 1.0
 * @class OBJ_Key
 * @brief estione dell'oggetto chiave, per l'apertura delle porte
 */
public class OBJ_Key extends SuperObject {
    /**
     * metodo per la gestione delle chiavi
     *@brief  costruttore parametrico OBJ_Key()
     * @param type riceve come parametro il tipo delle chiave
     * @since 1.0
     */
    public OBJ_Key(String type){

        switch (type) {
            case "red" -> {
                name = "redKey";
                try {
                    image = ImageIO.read(new File("resources/Object/key/redkey.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "green" -> {
                name = "GreenKey";
                try {
                    image = ImageIO.read(new File("resources/Object/key/greenkey..png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "purple" -> {
                name = "PurpleKey";
                try {
                    image = ImageIO.read(new File("resources/Object/key/purplekey.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case "white" -> {
                name = "WhiteKey";
                try {
                    image = ImageIO.read(new File("resources/Object/key/whitekey.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
