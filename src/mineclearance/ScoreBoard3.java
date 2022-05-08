package mineclearance;
import entity.Player;
import javax.swing.*;
import mineclearance.GamePanel;

import java.awt.*;


public class ScoreBoard3 extends JPanel {
    int gridSize=Grid.gridSize;
    Player p1;
    Player p2;
    Player p3;


    JLabel score1 = new JLabel();
    JLabel score2 = new JLabel();
    JLabel score3 = new JLabel();
    JLabel score4 = new JLabel();
    JLabel score5 = new JLabel();
    JLabel score6 = new JLabel();
    JLabel score7 = new JLabel();
    JLabel score8 = new JLabel();
    JLabel score9 = new JLabel();
    JLabel image=new JLabel();
    JLabel imageA = new JLabel();
    JLabel imageB = new JLabel();
    JLabel imageC = new JLabel();
    ImageIcon image1;
    ImageIcon image2;
    ImageIcon image3;
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


    public ScoreBoard3(Player p1, Player p2,Player p3, int xCount, int yCount) {
        this.add(new JLabel("Score Board - "));
        this.setSize(yCount, 40);
        this.setLocation(0, xCount);
        this.setVisible(true);
        this.setLayout(null);

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3=new JPanel();
        image1 = new ImageIcon(Team[p1.teamCode]);
        Image img = image1.getImage();
        img = img.getScaledInstance(66, 66,Image.SCALE_DEFAULT);
        image1.setImage(img);
        imageA.setIcon(image1);
        imageA.setSize(66, 66);
        panel1.add(imageA);

        //p2
        image2 = new ImageIcon(Team[p2.teamCode]);
        Image img1 = image2.getImage();
        img1 = img1.getScaledInstance(66,66,Image.SCALE_DEFAULT);
        image2.setImage(img1);
        imageB.setIcon(image2);
        imageB.setSize(66,66);
        panel2.add(imageB);
        //p3
        image3 = new ImageIcon(Team[p3.teamCode]);
        Image img2 = image3.getImage();
        img2 = img2.getScaledInstance(66,66,Image.SCALE_DEFAULT);
        image3.setImage(img2);
        imageC.setIcon(image3);
        imageC.setSize(66,66);
        panel3.add(imageC);
        panel1.setSize(200, 90);
        panel2.setSize(200, 90);
        panel3.setSize(200, 90);
        panel1.setLocation(-10,      0);
        panel2.setLocation(-10,       xCount/3);
        panel3.setLocation(-10,       xCount/3*2);
        panel1.add(score1);
        panel1.add(score3);
        panel1.add(score5);
        panel2.add(score2);
        panel2.add(score4);
        panel2.add(score6);
        panel3.add(score7);
        panel3.add(score8);
        panel3.add(score9);
        panel1.setVisible(true);
        panel2.setVisible(true);
        panel3.setVisible(true);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.setVisible(true);
        update();

    }


    /**
     * 刷新计分板的数据。
     * 计分板会自动重新获取玩家的分数，并更新显示。
     */
    public void update() {
        score1.setText(String.format("%s 分数: %d  \n", p1.getUserName(), p1.getScore()));
        score3.setText(String.format(" %d 失误\n",p1.getMistake()));
        score5.setText(String.format("%d 剩余投篮次数\n",p1.getClicks()));
        score2.setText(String.format("%s 分数: %d  \n", p2.getUserName(), p2.getScore()));
        score4.setText(String.format("%d 失误数\n",p2.getMistake()));
        score6.setText(String.format("%d 剩余投篮次数\n",p2.getClicks()));
        score7.setText(String.format("%s 分数: %d  \n", p3.getUserName(), p3.getScore()));
        score8.setText(String.format("%d 失误数\n",p3.getMistake()));
        score9.setText(String.format("%d 剩余投篮次数\n",p3.getClicks()));
    }

}

