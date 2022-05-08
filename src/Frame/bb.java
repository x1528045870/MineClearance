package Frame;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bb extends JDialog {
    List<String>name;
    List<Integer>times;
    public bb(){
        Container container = this.getContentPane();
        this.setTitle("排行榜");
       name=new ArrayList<>();
        times=new ArrayList<>();
        read();
        Collections.sort(name);
        Collections.reverse(name);
        Font f=new Font("Microsoft Yahei",Font.BOLD,30);
        Color c = new Color(255,255,255);
        StringBuilder p=new StringBuilder("<html>排名 获胜次数用户名"+
                "<br>1 ？？");
        for (String s:name){
            p.append("<br>").append(name.indexOf(s)+2).append(" ").append(s);
        }
        p.append("<html>");
        //textArea
        JLabel label = new JLabel(String.valueOf(p));


        //Panel
        label.setLocation(0, 0);
        label.setFont(f);
        label.setForeground(c);
        label.setBackground(Color.BLACK);
        container.add(label);
        container.setBackground(Color.BLACK);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        this.setIconImage(NBA);
        this.setVisible(true);
        this.setBounds(500, 200, 800, 1000);
        pack();


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
                    times.add(Integer.parseInt(split[1]));
                    name.add(split[1]+split[0]);
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
