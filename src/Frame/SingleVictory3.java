package Frame;

import controller.GameController;
import entity.Player;

import javax.swing.*;
import java.awt.*;

public class SingleVictory3 extends JDialog {
    Font f=new Font("Microsoft Yahei",Font.BOLD,30);
    JDialog frame = new JDialog();
    public static GameController controller;

    public Sound victory;
    BackgroundPanel v;
    public SingleVictory3(){

        Container container = frame.getContentPane();
        v=new BackgroundPanel((new ImageIcon("img\\trophy.jpg")).getImage());
        v.setBounds(0,0,1280,1024);
        container.add(v);
        frame.setVisible(true);
        frame.setBounds(100,100,1280,1024);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

    }






}

