package Frame;

import controller.GameController;
import entity.Player;
import mineclearance.GamePanel;
import mineclearance.SingleGamePanel;
import mineclearance.Grid;
import mineclearance.SingleScoreBoard;
import controller.SingleController;
import javax.swing.*;
import java.awt.*;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
public class SingleGameDialog extends JDialog {
    Font f=new Font("Microsoft Yahei",Font.BOLD,30);
    JDialog frame = new JDialog();
    int gridSize=Grid.gridSize;
    public static SingleController controller;

    public int needScore;
    public int loseMistake;
    public SingleGameDialog(int Row, int Column, int Mine,int needScore,int loseMistake) {

        this.loseMistake=loseMistake;
        this.needScore=needScore;
        Color c=new Color(255,255,255);



        frame.setCursor(createCursor());
        controller = new SingleController(Row,Column,Mine);
        frame.setBounds(500, 200, Row * Grid.gridSize + 400+Grid.gridSize*Row/6, Column * Grid.gridSize+29);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        SingleGamePanel gamePanel = new SingleGamePanel(Column, Row, Mine,needScore,loseMistake);
        controller.setGamePanel(gamePanel);
        SingleScoreBoard scoreBoard=new SingleScoreBoard(Column*Grid.gridSize,Row*Grid.gridSize);
        scoreBoard.update(0,0,Mine);
        controller.setScoreBoard(scoreBoard);



        Box box = Box.createHorizontalBox();
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();

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
        frame.setResizable(true);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int Width = frame.getWidth();
                int Height = frame.getHeight();
                gamePanel.setGridSize(Height/Column);

                gamePanel.setBounds(100,0,Row*Height/Column,Height);
                gamePanel.setFaceAndBack(Height/Column);

            }
        });
        pack();

    }
    public static Cursor createCursor() {
        String fileName = "img\\篮球.png";

        Image cursor = Toolkit.getDefaultToolkit().getImage(fileName);

        return Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(16, 16), "mycursor");

    }


}

