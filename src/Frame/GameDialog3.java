package Frame;

import controller.GameController;
import controller.GameController3;
import entity.Player;
import mineclearance.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Cursor;

import java.awt.Image;

import java.awt.Point;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
public class GameDialog3 extends JDialog {
    Font f=new Font("Microsoft Yahei",Font.BOLD,30);
    JDialog frame = new JDialog();
    int gridSize=Grid.gridSize;
    public static GameController3 controller;
    Player p1, p2,p3;
    public Sound player;

    public GameDialog3(int Row, int Column, int Mine, int Turns,Player p1,Player p2,Player p3,Sound player) {
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.player=player;
        Color c=new Color(255,255,255);
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
        stopAndPlay.setBounds(Row * Grid.gridSize + 400+Grid.gridSize*Row/6,Column * Grid.gridSize+29,33  ,33);
        stopAndPlay.setBackground(c);
        stopAndPlay.setOpaque(false);
        stopAndPlay.setFocusPainted(false);
        stopAndPlay.setBorder(null);

        stopAndPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.run){player.stop();stopAndPlay.setIcon(image1);
                }
                else {player.continues();stopAndPlay.setIcon(image2);}
            }
        });
        frame.setCursor(createCursor());
        //addP3
        controller = new GameController3(p1, p2,p3,Row,Column,Mine,Turns);
        frame.setBounds(500, 200, Row * Grid.gridSize + 400+Grid.gridSize*Row/6, Column * Grid.gridSize+29);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        GamePanel3 gamePanel = new GamePanel3(Column, Row, Mine,Turns);
        controller.setGamePanel3(gamePanel);
        //setScoreBoard3
        ScoreBoard3 scoreBoard = new ScoreBoard3(p1, p2, p3, Column * Grid.gridSize, Row * Grid.gridSize);
        controller.setScoreBoard3(scoreBoard);

        TimeRun3 timeRun = new TimeRun3(Column * Grid.gridSize,Row * Grid.gridSize);
        timeRun.add(stopAndPlay);
        Box box = Box.createHorizontalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();

        gamePanel.setPreferredSize(new Dimension(Row * Grid.gridSize,Column * Grid.gridSize));
        scoreBoard.setPreferredSize(new Dimension(100,100));
        timeRun.setPreferredSize(new Dimension(100,100));

        box1.add(scoreBoard);
        box2.add(gamePanel);
        box3.add(timeRun);

        box.add(box1);
        box.add(box2);
        box.add(box3);

        frame.setContentPane(box);
        frame.setVisible(true);
        frame.setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        this.setIconImage(NBA);
        pack();

    }
   /* public GameDialog3(int [][]message) {
        frame.setCursor(createCursor());
        Player p1 = new Player();
        Player p2 = new Player();
        p1.setScore(message[8][0]);
        p1.setMistake(message[8][1]);
        p2.setScore(message[9][0]);
        p2.setMistake(message[9][1]);
        controller = new GameController(p1, p2,message);
        frame.setBounds(500, 200, message[0][0] * Grid.gridSize  + 400+Grid.gridSize*message[0][0]/6, message[0][1] * Grid.gridSize + 30);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        GamePanel gamePanel = new GamePanel(message);
        controller.setGamePanel(gamePanel);
        frame.add(gamePanel);
        ScoreBoard scoreBoard = new ScoreBoard(p1, p2, 500 + message[0][0] * Grid.gridSize, 10);
        controller.setScoreBoard(scoreBoard);
        frame.add(scoreBoard);

        TimeRun timeRun = new TimeRun(message[0][0] * Grid.gridSize,message[0][0] * Grid.gridSize);

        Box box = Box.createHorizontalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();

        gamePanel.setPreferredSize(new Dimension(message[0][0] * Grid.gridSize,message[0][1] * Grid.gridSize));
        scoreBoard.setPreferredSize(new Dimension(100,100));
        timeRun.setPreferredSize(new Dimension(100,100));
        box1.add(scoreBoard);
        box2.add(gamePanel);
        box3.add(timeRun);

        box.add(box1);
        box.add(box2);
        box.add(box3);

        frame.setContentPane(box);

    }*/
    public static Cursor createCursor() {
        String fileName = "img\\篮球.png";

        Image cursor = Toolkit.getDefaultToolkit().getImage(fileName);

        return Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(16, 16), "mycursor");

    }

}
