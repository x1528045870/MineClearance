package Frame;

import controller.GameController;
import entity.Player;

import javax.swing.*;
import java.awt.*;

public class SingleLose extends JDialog {
    Font f=new Font("Microsoft Yahei",Font.BOLD,30);
    JDialog frame = new JDialog();
    public static GameController controller;

    public Sound lose;
    BackgroundPanel v;
    public SingleLose(){

        Container container = frame.getContentPane();
        v=new BackgroundPanel((new ImageIcon("img\\杜兰特.png")).getImage());
        v.setBounds(0,0,394,357);
        container.add(v);
        frame.setVisible(true);
        frame.setBounds(100,100,394,357);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("lose");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        this.setIconImage(NBA);
    }






}