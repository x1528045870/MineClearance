package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StoryIntroduction extends JFrame {
    public Sound player;
    public StoryIntroduction(Sound player) {
        this.player=player;
        player.stop();
        Container container = this.getContentPane();
        this.setTitle("故事概要");
        Font f=new Font("Microsoft Yahei",Font.BOLD,30);
        Color c = new Color(255,255,255);
        //textArea
        JLabel label = new JLabel("<html>你穿越到2016年NBA总决赛的第二场，此时你化身为勇士的当家球星史蒂芬·库里。在你" +
                "<br>的记忆中勇士被骑士以1比3翻盘。你需要获得6分才能帮助勇士成功晋级至下一场比赛，" +
                "<br>你需要帮助球队赢下总决赛第二场，第四场以及第七场的胜利。帮助球队成功夺冠。但是也" +
                "<br>不能忽视对手防守。当你用篮球翻开一个球筐时，你有可能被防守球员盖帽也有可能进球更" +
                "<br>有可能投篮打铁（被盖帽对应扫雷中踩雷，即你的分数将会减一，投篮进球则对应着扫雷中" +
                "<br>将地雷正确标记出来，即你将获得一分，投篮打铁则对应着错误的标记地雷，此时你的失误" +
                "<br>数会加一）克利夫兰骑士在来自阿克伦的勒布朗·詹姆斯带领下气势汹汹，誓要让一追四击" +
                "<br>败勇士夺下冠军。你必须小心翼翼的选择投篮的时机，如果在第一场超过2次打铁则被判闯" +
                "<br>关失败。第一场过后骑士的进攻将会更加凶猛，你需要夺得8分并打铁次数不超过1次。而" +
                "<br>在最后一场，对手将会用尽全力阻止你投篮，你必须与狡猾的对手对战并且最终战胜他（ 如" +
                "<br>果双方的分数差距大于游戏区中未揭晓的盖帽数，则直接判定优势方获胜。如果在游戏中所" +
                "<br>有盖帽都被揭晓时双方分数依然相同，则打铁数少的一方（失误包含误触 雷以及标记错误）" +
                "<br>获胜），捧起NBA年度总冠军奖杯。弥补你偶像库里一生的遗憾。加油吧！来自南科大的少" +
                "<br>年！                                  鼠标点击任意处跳过。。。<html>");


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
                new SinglePlay().init ();
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
