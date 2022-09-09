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
                if(i==g.size()-1){
                    String[] xy;
                    xy=g.get(i).split(";");
                    if(xy[0]=="1"){
                        gp.aSetter.stopNpc();
                        gp.aSetter.setNpc();
                        this.interrupt();
                    }
                }

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

    public void updatePlayer(){

            int x=gp.player.worldX/48;
            int y=gp.player.worldY/48;
        System.out.println(x+";"+y);
        try{
                String c=x+";"+y+"\n0";
                File file=new File("C:\\EscapePrizon\\FromClient.csv");
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                fw.write(c);
                fw.flush();
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void updateContact(){
        int x=gp.player.worldX/48;
        int y=gp.player.worldY/48;
        System.out.println(x+";"+y+"\n1");
        try{
            String c=x+";"+y+"\n1";
            File file=new File("C:\\EscapePrizon\\FromClient.csv");
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(c);
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
