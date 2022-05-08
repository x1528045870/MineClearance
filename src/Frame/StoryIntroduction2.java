package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StoryIntroduction2 extends JFrame {

    public StoryIntroduction2() {

        Container container = this.getContentPane();
        this.setTitle("故事概要");
        Font f=new Font("Microsoft Yahei",Font.BOLD,30);
        Color c = new Color(255,255,255);
        //textArea
        JLabel label = new JLabel("<html>第四场\n" +
                "<br>恭喜你赢得了第二场比赛，接下来你将出现在总决赛的第四场，"+
                "<br>由于第三场勇士负于骑士，因此你需要赢得这一场比赛来保证球队的优势。"+
                "<br>在这场比赛中你需要打败对手。 鼠标点击任意处跳过。。。\n<html>");


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
                new AIDialog(9,9,9);
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