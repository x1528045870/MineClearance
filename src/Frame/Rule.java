package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rule extends JFrame {
    public Sound player;
    public Rule() {
        this.player=player;
        Container container = this.getContentPane();
        this.setTitle("规则介绍");
        Font f=new Font("Microsoft Yahei",Font.BOLD,20);
        Color c = new Color(255,255,255);
        //textArea
        JLabel label = new JLabel
        ("<html>欢迎来到2Ksweeper，在这个奇幻的世界里，右键点击格子即意味着你在篮球场上出手投篮了一次。此时你将会面对以下情况:"+
        "<br>1. 成功出手并将球投进篮筐，这对应着你在传统扫雷中成功标记出了地雷，此时你的分数会加一。"+
                "<br>2. 成功出手但篮球打铁没有进入篮筐，这对应着你在传统扫雷中标记了错误的格子，此时你的失误数会加一。"+
        "<br>左键点击格子则会出现以下情况："+
        "<br>1.投篮出手但被防守球员盖帽，这对应着传统扫雷中点击到了地雷，此时你的分数会减一。"+
        "<br>2.点击的格子什么也没有则什么也不发生，游戏继续。"+

        "<br>在2ksweeper的世界里你还拥有几项技能："+
        "<br>1.级联，当你点开一个格子后我们会自动帮你掀开在格子附近的九宫格内不存在盖帽的格子，但该技能只有你在达到一定积分后才能解锁。"+
        "<br>2.透视，你可以直接看到所有带有盖帽的格子。"+
        "<br>3.悔棋，在你获得一定的分数后，你有机会使时光倒流撤回自己上一步的操作。"+
        "<br>4.翻开，这是一个大招，你可以直接翻开所有的格子。 鼠标点击任意处跳过。。。<html>");


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
