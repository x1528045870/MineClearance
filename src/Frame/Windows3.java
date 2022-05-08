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

public class Windows3 {
    int [][]message;
    public String Player01,Player02;
    BackgroundPanel g;
    Player p1,p2,p3;
    public Sound player;
    public void  init(Player p1,Player p2,Player p3,Sound player){
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.player=player;
        Font f=new Font("Microsoft Yahei",Font.BOLD,30);

        Color c = new Color(255,255,255);
        Color c1 = new Color(255,255,0);
        Color c2=new Color(255,0,0);
        JFrame frame = new JFrame("棋盘选择");

        //set Text
        //JLabel label = new JLabel("MineSweeper");
        //frame.add(label);
        //set color
        //Container container = frame.getContentPane();
        //container.setBackground(Color.CYAN);
        Container container = frame.getContentPane();
        container.setLayout(null);
        ImageIcon image1=new ImageIcon("img\\播放 (2).png");
        ImageIcon image2=new ImageIcon("img\\停止(1).png");
        Image img = image1.getImage();
        img = img.getScaledInstance(33, 33,Image.SCALE_DEFAULT);
        image1.setImage(img);
        Image im = image2.getImage();
        im = im.getScaledInstance(33, 33,Image.SCALE_DEFAULT);
        image2.setImage(im);
        JButton stopAndPlay=new JButton(image2);
        if(!player.run){
            stopAndPlay.setIcon(image1);
        }
        stopAndPlay.setBounds(0,0,33  ,33);
        stopAndPlay.setBackground(c);
        stopAndPlay.setOpaque(false);
        stopAndPlay.setFocusPainted(false);
        stopAndPlay.setBorder(null);
        container.add(stopAndPlay);
        stopAndPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.run){player.stop();stopAndPlay.setIcon(image1);
                }
                else {player.continues();stopAndPlay.setIcon(image2);}
            }
        });
        //set button
        JButton button1 = new JButton("primer");
        button1.setFont(f);
        button1.setForeground(c);
        button1.setBackground(c);
        button1.setOpaque(false);
        button1.setBorderPainted(false);//设置是否绘制边框
        button1.setBorder(null);//设置边框
        JButton button2 = new JButton("medium");
        button2.setFont(f);
        button2.setForeground(c);
        button2.setBackground(c);
        button2.setOpaque(false);
        button2.setBorderPainted(false);//设置是否绘制边框
        button2.setBorder(null);//设置边框
        JButton button3 = new JButton("senior");
        button3.setFont(f);
        button3.setForeground(c);
        button3.setBackground(c);
        button3.setOpaque(false);
        button3.setBorderPainted(false);//设置是否绘制边框
        button3.setBorder(null);//设置边框
        JButton button4 = new JButton("2k By You");
        button4.setFont(f);
        button4.setForeground(c);
        button4.setBackground(c);
        button4.setOpaque(false);
        button4.setBorderPainted(false);//设置是否绘制边框
        button4.setBorder(null);//设置边框
        button1.setBounds(40,10,200,40);
        button2.setBounds(40,90,200,40);
        button3.setBounds(40,170,200,40);
        button4.setBounds(40,250,200,40);
        container.add(button1);
        container.add(button2);
        container.add(button3);
        container.add(button4);
        g=new BackgroundPanel((new ImageIcon("img\\077.jpg")).getImage());
        g.setBounds(0,0,560,340);
        container.add(g);
        frame.setVisible(true);
        frame.setBounds(100,100,560,340);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        setActionListenerUserDefined(button4);
        setActionListenerGrade(button1);
        setActionListenerGrade(button2);
        setActionListenerGrade(button3);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        frame.setIconImage(NBA);
    }



    private void setActionListenerUserDefined(JButton button){
        // Listener
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button){
                    new Dialog3(p1,p2,p3,player);
                }
            }
        });
    }
    private void setActionListenerGrade(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String event = e.getActionCommand();
                if (event == "primer"){
                    new GameDialog3(9,9,10,1,p1,p2,p3,player);
                }
                else if (event == "medium"){
                    new GameDialog3(16,16,40,1,p1,p2,p3,player);
                }
                else if (event == "senior"){
                    new GameDialog3(30,16,99,1,p1,p2,p3,player);
                }
            }
        });
    }

}