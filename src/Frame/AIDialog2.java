package Frame;

import mineclearance.AIGamePanel2;
import mineclearance.Grid;
import mineclearance.AIScoreBoard;
import controller.AIController2;
import javax.swing.*;
import java.awt.*;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AIDialog2 extends JDialog {
    Font f=new Font("Microsoft Yahei",Font.BOLD,30);
    JDialog frame = new JDialog();
    int gridSize=Grid.gridSize;
    public static AIController2 controller;


    public AIDialog2(int Row, int Column, int Mine) {
        Color c=new Color(255,255,255);
        frame.setCursor(createCursor());
        controller = new AIController2(Row,Column,Mine);
        frame.setBounds(500, 200, Row * Grid.gridSize + 400+Grid.gridSize*Row/6, Column * Grid.gridSize+29);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        AIGamePanel2 gamePanel = new AIGamePanel2(Column, Row, Mine);
        controller.setGamePanel(gamePanel);
        AIScoreBoard scoreBoard=new AIScoreBoard(Column*Grid.gridSize,Row*Grid.gridSize);
        scoreBoard.update(0,0,Mine);
        scoreBoard.AIUpdate(0,0);
        controller.setScoreBoard(scoreBoard);
        Box box = Box.createHorizontalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();

        gamePanel.setPreferredSize(new Dimension(Row * Grid.gridSize,Column * Grid.gridSize));
        scoreBoard.setPreferredSize(new Dimension(100,100));
        box1.add(scoreBoard);
        box2.add(gamePanel);
        box.add(box1);
        box.add(box2);
        frame.setContentPane(box);
        frame.setVisible(true);
        frame.setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        frame.setIconImage(NBA);
        pack();

    }
    public static Cursor createCursor() {
        String fileName = "img\\篮球.png";

        Image cursor = Toolkit.getDefaultToolkit().getImage(fileName);

        return Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(16, 16), "mycursor");

    }



}
