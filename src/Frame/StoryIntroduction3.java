package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StoryIntroduction3 extends JFrame {
    public StoryIntroduction3() {
        Container container = this.getContentPane();
        this.setTitle("故事概要");
        Font f=new Font("Microsoft Yahei",Font.BOLD,30);
        Color c = new Color(255,255,255);
        //textArea
        JLabel label = new JLabel("<html>第七场\n" +
                "<br>这是决胜局，你将面对骑士的疯狂进攻，他们的进攻将变得越来越凶猛，"+
                "<br>因此你需要运用你自己的智慧去击败他们，向前冲吧！击败对手获得总冠军" +
                "<br> 鼠标点击任意处跳过。。。\n<html>");


        //Panel
        label.setLocation(0, 0);
        label.setFont(f);
        label.setForeground(c);
        label.setBackground(Color.BLACK);


        //JScrollPane panel = new JScrollPane(label);
        //panel.setBackground(Color.BLACK);
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AIDialog2(9,9,9);
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        container.add(label);
        container.setBackground(Color.BLACK);
//修改java咖啡
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        this.setIconImage(NBA);
        this.setVisible(true);
        this.setBounds(200, 200, 800, 1000);
        pack();

    }
}
