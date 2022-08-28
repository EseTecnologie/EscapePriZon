package entity;

import main.GamePanel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EntityGestions extends Thread{
    GamePanel gp;
   public EntityGestions(GamePanel gp){
    this.gp=gp;
    }
    ArrayList<Integer> x;
    ArrayList<Integer> y;
    @Override
    public void run() {

        while(true){
            x=new ArrayList<Integer>();
            y=new ArrayList<Integer>();
            File file = new File("C:\\EscapePrizon\\FromServer.csv");
            FileReader fr = null;
            try {
                fr = new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<String> g = new ArrayList<String>();
            BufferedReader br = new BufferedReader(fr);
            String info="";
            try{
                while ((info = br.readLine()) != null) {
                    g.add(info);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

            for (int i=0;i<g.size();i++){
                String[] xy;
                xy=g.get(i).split(";");

                x.add(Integer.parseInt(xy[0]));
                y.add(Integer.parseInt(xy[1]));
            }

            for(int i=0;i<x.size();i++){
                if(i==x.size()-1){
                    if(x.get(i)>gp.player2.worldX/48){
                        gp.player2.direction="down";
                    }else if(x.get(i)<gp.player2.worldX/48){
                        gp.player2.direction="up";
                    }else if(y.get(i)>gp.player2.worldY/48){
                        gp.player2.direction="right";
                    }else if(y.get(i)<gp.player2.worldY/48) {
                        gp.player2.direction="left";
                    }else{
                        gp.player2.direction="stop";
                    }
                }else{
                    if(x.get(i)>gp.npc[i].worldX/48){
                        gp.npc[i].direction="down";
                    }else if(x.get(i)<gp.npc[i].worldX/48){
                        gp.npc[i].direction="up";
                    }else if(y.get(i)>gp.npc[i].worldY/48){
                        gp.npc[i].direction="right";
                    }else if(y.get(i)<gp.npc[i].worldY/48){
                        gp.npc[i].direction="left";
                    }else {
                        gp.npc[i].direction="stop";
                    }
                }

            }

        }
    }
}
