package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_boostSpeed extends SuperObject {
    public OBJ_boostSpeed(String type){


            name=type;
            try{
                image = ImageIO.read(new File("main/res/Object/boostSpeed/boots.png"));
            }catch (IOException e){
                e.printStackTrace();
            }

    }
}
