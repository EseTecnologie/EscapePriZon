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

public class OBJ_Key extends SuperObject {
    /**
     *@brief  costruttore parametrico OBJ_Key()
     *
     * metodo per la gestione delle chiavi
     *
     * @param type riceve come parametro il tipo delle chiave
     */
    public OBJ_Key(String type){

        if(type=="red"){
            name="redKey";
            try{
                image = ImageIO.read(new File("resources/Object/key/redkey.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }else  if(type=="green"){
            name="GreenKey";
            try{
                image = ImageIO.read(new File("resources/Object/key/greenkey..png"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }else  if(type=="purple"){
            name="PurpleKey";
            try{
                image = ImageIO.read(new File("resources/Object/key/purplekey.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }else  if(type=="white"){
            name="WhiteKey";
            try{
                image = ImageIO.read(new File("resources/Object/key/whitekey.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
