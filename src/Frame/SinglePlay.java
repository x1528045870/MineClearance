package Frame;

import entity.Player;
import mineclearance.GamePanel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import Frame.SingleGameDialog;

public class SinglePlay {

    public String Player01,Player02;
    BackgroundPanel g;

    public void  init(){

        Font f=new Font("Microsoft Yahei",Font.BOLD,30);

        Color c = new Color(255,255,255);
        Color c1 = new Color(255,255,0);
        Color c2=new Color(255,0,0);
        JFrame frame = new JFrame("GS");
        Container container = frame.getContentPane();
        container.setLayout(null);
        //set button
        JButton button1 = new JButton("第一关");
        button1.setFont(f);
        button1.setForeground(c);
        button1.setBackground(c);
        button1.setOpaque(false);
        button1.setBorderPainted(false);//设置是否绘制边框
        button1.setBorder(null);//设置边框
        JButton button2 = new JButton("第二关");
        button2.setFont(f);
        button2.setForeground(c);
        button2.setBackground(c);
        button2.setOpaque(false);
        button2.setBorderPainted(false);//设置是否绘制边框
        button2.setBorder(null);//设置边框
        JButton button3 = new JButton("第三关");
        button3.setFont(f);
        button3.setForeground(c);
        button3.setBackground(c);
        button3.setOpaque(false);
        button3.setBorderPainted(false);//设置是否绘制边框
        button3.setBorder(null);//设置边框
        button1.setBounds(40,10,200,40);
        button2.setBounds(40,90,200,40);
        button3.setBounds(40,170,200,40);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SingleGameDialog(9,9,10,6,2);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new SingleGameDialog(12,12,13,player,8,1);
                new StoryIntroduction2();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new AIDialog(9,9,9,player);
                new StoryIntroduction3();
            }
        });
        container.add(button1);
        container.add(button2);
        container.add(button3);
        g=new BackgroundPanel((new ImageIcon("img\\curry.jpg")).getImage());
        g.setBounds(0,0,600,303);
        container.add(g);
        frame.setVisible(true);
        frame.setBounds(100,100,600,303);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        frame.setIconImage(NBA);

    }

}