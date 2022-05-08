package Frame;

import controller.GameController;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Victory3 extends JDialog {
    Font f=new Font("Microsoft Yahei",Font.BOLD,30);
    JDialog frame = new JDialog();
    public static GameController controller;
    Player p1;
    Player p2;
    Player p3;
    public Sound victory;
    BackgroundPanel v;
    HashMap<String,Integer>billboard=new HashMap<String,Integer>();
    public Victory3(Player p1,Player p2,Player p3){
        Container container = frame.getContentPane();
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        if (p1.isVictory() != -1 || p2.isVictory() != -1||p3.isVictory()!=-1) {

            if (p1.isVictory() == 1&&p2.isVictory()==1) {
                read();
                if (billboard.containsKey(p1.getUserName())){
                    billboard.put(p1.getUserName(),billboard.get(p1.getUserName())+1);
                }
                else{
                    billboard.put(p1.getUserName(),1);
                }
                if (billboard.containsKey(p2.getUserName())){
                    billboard.put(p2.getUserName(),billboard.get(p2.getUserName())+1);
                }
                else{
                    billboard.put(p2.getUserName(),1);
                }
                saveIn();
                frame.setTitle(p1.getUserName()+","+p2.getUserName()+" is victory");}
            else if (p1.isVictory() == 1&&p3.isVictory()==1) {
                read();
                if (billboard.containsKey(p1.getUserName())){
                    billboard.put(p1.getUserName(),billboard.get(p1.getUserName())+1);
                }
                else{
                    billboard.put(p1.getUserName(),1);
                }
                if (billboard.containsKey(p3.getUserName())){
                    billboard.put(p3.getUserName(),billboard.get(p3.getUserName())+1);
                }
                else{
                    billboard.put(p3.getUserName(),1);
                }
                saveIn();
                    frame.setTitle(p1.getUserName()+","+p3.getUserName()+" is victory");}
            else if (p2.isVictory() == 1&&p3.isVictory()==1) {
                read();
                if (billboard.containsKey(p2.getUserName())){
                    billboard.put(p2.getUserName(),billboard.get(p2.getUserName())+1);
                }
                else{
                    billboard.put(p2.getUserName(),1);
                }
                if (billboard.containsKey(p3.getUserName())){
                    billboard.put(p3.getUserName(),billboard.get(p3.getUserName())+1);
                }
                else{
                    billboard.put(p3.getUserName(),1);
                }
                saveIn();
                frame.setTitle(p2.getUserName()+","+p3.getUserName()+" is victory");}
            else if (p1.isVictory() == 1) {
                read();
                if (billboard.containsKey(p1.getUserName())){
                    billboard.put(p1.getUserName(),billboard.get(p1.getUserName())+1);
                }
                else{
                    billboard.put(p1.getUserName(),1);
                }
                saveIn();
                frame.setTitle(p1.getUserName()+" is victory");
            } else if (p2.isVictory() == 1) {
                read();
                if (billboard.containsKey(p2.getUserName())){
                    billboard.put(p2.getUserName(),billboard.get(p2.getUserName())+1);
                }
                else{
                    billboard.put(p2.getUserName(),1);
                }
                saveIn();
                frame.setTitle(p2.getUserName()+" is victory");
            }  else if (p3.isVictory() == 1) {
                read();
                if (billboard.containsKey(p3.getUserName())){
                    billboard.put(p3.getUserName(),billboard.get(p3.getUserName())+1);
                }
                else{
                    billboard.put(p3.getUserName(),1);
                }
                saveIn();
                frame.setTitle(p3.getUserName()+" is victory");}
            else {
                frame.setTitle("This game was a draw!");
            }
        }
        v=new BackgroundPanel((new ImageIcon("img\\trophy.jpg")).getImage());
        v.setBounds(0,0,1280,1024);
        container.add(v);
        frame.setVisible(true);
        frame.setBounds(100,100,1280,1024);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        frame.setIconImage(NBA);
    }
    public void saveIn(){
        File f = new File("排行榜\\save.txt");
        if(f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Map.Entry<String,Integer>> set=billboard.entrySet();
        for(Map.Entry<String,Integer>entry:set){
            try {
                fw.write(entry.getKey()+"\t"+entry.getValue()+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void read(){
        File f = new File("排行榜\\save.txt");
        Reader r = null;
        try {
            r = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(r!=null){
            BufferedReader br = new BufferedReader(r);

            if(f.exists()) {
                String s=null;
                while(true) {
                    try {
                        if ((s = br.readLine()) == null) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert s != null;
                    String[] split = s.split("\t");
                    billboard.put(split[0],Integer.parseInt(split[1]));
                }
            }

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

