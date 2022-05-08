package Frame;
import entity.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class ChooseTeam extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[30];
    Player p1,p2;
    public Sound player;
    public ChooseTeam(Player p1, Player p2,Sound player){
        this.p1 = p1;
        this.p2 = p2;
        this.player=player;

        String[] Teams = {"img\\NBA01.jpg",
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
        this.setTitle("选择你的主队吧！！");
        Container container = this.getContentPane();
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
        Color c=new Color(0,255,255);
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
        setLayout(new FlowLayout());
        for (int i = 0; i < 30; i++) {
            buttons[i] = new JButton();
        }
        for (int i = 0; i < 30; i++) {
            setImage(i,buttons[i],Teams[i]);
            container.add(buttons[i]);
        }

        buttons[0].setToolTipText("亚特兰大 老鹰");
        buttons[1].setToolTipText("波士顿 凯尔特人");
        buttons[2].setToolTipText("芝加哥 公牛");
        buttons[3].setToolTipText("夏洛特 黄蜂");
        buttons[4].setToolTipText("布鲁克林 篮网");
        buttons[5].setToolTipText("克利夫兰 骑士");
        buttons[6].setToolTipText("迈阿密 热火");
        buttons[7].setToolTipText("纽约 尼克斯");
        buttons[8].setToolTipText("底特律 活塞");
        buttons[9].setToolTipText("奥兰多 魔术");
        buttons[10].setToolTipText("费城 76人");
        buttons[11].setToolTipText("印第安纳 步行者");
        buttons[12].setToolTipText("华盛顿 奇才");
        buttons[13].setToolTipText("多伦多 猛龙");
        buttons[14].setToolTipText("密尔沃基 雄鹿");
        buttons[15].setToolTipText("达拉斯 独行侠");
        buttons[16].setToolTipText("丹佛 掘金");
        buttons[17].setToolTipText("金州 勇士");
        buttons[18].setToolTipText("休斯顿 火箭");
        buttons[19].setToolTipText("明尼苏达 森林狼");
        buttons[20].setToolTipText("洛杉矶 快船");
        buttons[21].setToolTipText("孟菲斯 灰熊");
        buttons[22].setToolTipText("俄克拉荷马城 雷霆");
        buttons[23].setToolTipText("洛杉矶 湖人");
        buttons[24].setToolTipText("新奥尔良 鹈鹕");
        buttons[25].setToolTipText("波特兰 开拓者");
        buttons[26].setToolTipText("菲尼克斯 太阳");
        buttons[27].setToolTipText("圣安东尼奥 马刺");
        buttons[28].setToolTipText("犹他 爵士");
        buttons[29].setToolTipText("萨克拉门托 国王");

        for (int i = 0; i < 30; i++) {
            buttons[i].addActionListener(this);
            MyMouseAdapter myMouseAdapter = new MyMouseAdapter(buttons[i]);
            buttons[i].addMouseListener(myMouseAdapter);
            this.getContentPane().add(buttons[i]);

        }
        pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setBounds(200,200,600,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //修改java咖啡
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        this.setIconImage(NBA);

    }
    int time = 0;
    @Override
    public void actionPerformed(ActionEvent e){
        for (int i = 0; i < 30; i++) {
            if (e.getSource() == buttons[i]){
                time++;
                if (time == 1){
                    p1.setTeam(buttons[i].getToolTipText());
                    p1.teamCode=i;
                }
                if (time == 2){
                    p2.setTeam(buttons[i].getToolTipText());
                    p2.teamCode=i;
                    new Windows().init(p1,p2,player);
                    dispose();
                }

            }
        }


    }

    private void setImage(int num, JButton button, String Team){
        ImageIcon icon = new ImageIcon(Team);
        button.setIcon(icon);
        button.setSize(100,100);
        button.setBorderPainted(false);
        button.setOpaque(true);
    } static class MyMouseAdapter extends MouseAdapter {
       Color c=new Color(0,255,255);
        JButton button;
        public MyMouseAdapter(JButton button){
            this.button = button;
        }

        public void mouseEntered(MouseEvent e) {
            button.setSize(120,120);
            button.setBackground(c);
        }
        public void mouseExited(MouseEvent e) {
            button.setSize(100,100);
            button.setBackground(null);
        }
    }


}
