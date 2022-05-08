package mineclearance;
import entity.Player;
import javax.swing.*;
import mineclearance.GamePanel;

import java.awt.*;

public class AIScoreBoard extends JPanel {
    int gridSize=Grid.gridSize;
    JLabel score1 = new JLabel();
    JLabel score2= new JLabel();
    JLabel score3 = new JLabel();
    JLabel score4=new JLabel();
    JLabel score5 = new JLabel();
    JLabel imageA = new JLabel();
    ImageIcon image1;
    JLabel imageB = new JLabel();
    ImageIcon image2;
    String[] Team = {"img\\NBA01.jpg",
            "img\\NBA02.jpg",
            "img\\NBA03.jpg",
            "img\\NBA04.jpg",
            "img\\NBA05.jpg",
            "img\\NBA06.jpg",
            "img\\NBA07.jpg",
            "img\\NBA08.jpg",
            "img\\NBA09.jpg",
            "img\\NBA10.jpg",
            "img\\NBA11.jpg",
            "img\\NBA12.jpg",
            "img\\NBA13.jpg",
            "img\\NBA14.jpg",
            "img\\NBA15.jpg",
            "img\\NBA16.jpg",
            "img\\NBA17.jpg",
            "img\\NBA18.jpg",
            "img\\NBA19.jpg",
            "img\\NBA20.jpg",
            "img\\NBA21.jpg",
            "img\\NBA22.jpg",
            "img\\NBA23.jpg",
            "img\\NBA24.jpg",
            "img\\NBA25.jpg",
            "img\\NBA26.jpg",
            "img\\NBA27.jpg",
            "img\\NBA28.jpg",
            "img\\NBA29.jpg",
            "img\\NBA30.jpg"};
    public AIScoreBoard(int xCount, int yCount) {
        this.add(new JLabel("Score Board - "));
        this.setSize(yCount, 40);
        this.setLocation(0, xCount);
        this.setVisible(true);
        this.setLayout(null);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        image2 = new ImageIcon("img\\NBA06.jpg");
        Image img = image2.getImage();
        img = img.getScaledInstance(66, 66,Image.SCALE_DEFAULT);
        image2.setImage(img);
        imageB.setIcon(image2);
        imageB.setSize(66, 66);
        image1 = new ImageIcon("img\\NBA18.jpg");
        Image im = image1.getImage();
        img = im.getScaledInstance(66, 66,Image.SCALE_DEFAULT);
        image1.setImage(im);
        imageA.setIcon(image1);
        imageA.setSize(66, 66);
        panel1.add(imageA);
        panel2.add(imageB);


        panel1.setSize(200, 90);
        panel1.setLocation(-10,      0);
        panel1.add(score1);
        panel1.add(score3);
        panel1.add(score5);
        panel1.setVisible(true);
        panel1.add(imageA);
        panel2.setSize(200, 90);
        panel2.setLocation(-10,      xCount/3);
        panel2.add(score2);
        panel2.add(score4);
        panel2.setVisible(true);
        this.add(panel1);
        this.add(panel2);
        this.setVisible(true);

    }



    public  void update(int score,int mistake,int remainMineCount) {
        score1.setText(String.format(" 分数: %d  \n", score));
        score3.setText(String.format(" %d 失误\n",mistake));
        score5.setText(String.format(" %d 剩余合理框\n",remainMineCount));
    }
    public void AIUpdate(int AIScore,int AIMistake){
        score2.setText(String.format(" 分数: %d  \n", AIScore));
        score4.setText(String.format(" %d 失误\n",AIMistake));}
}