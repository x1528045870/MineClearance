package Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import entity.Player;

public class Start {
    public String Player01,Player02;
    BackgroundPanel b;
    public Sound player;
    public void init(Sound player){
        this.player=player;

        JFrame frame = new JFrame("输入用户名");
        Container container = frame.getContentPane();
        Font f=new Font("Microsoft Yahei",Font.BOLD,30);
        Color c = new Color(255,255,255);
        frame.setBounds(100,100,474,266);
        //Player#1
        TextField textField1 = new TextField();
        textField1.setBounds(300,100,150,20);
        container.add(textField1);
        //set text
        JLabel label1 = new JLabel("Player#1");
        label1.setForeground(c);
        label1.setBounds(200,10,100,200);
        container.add(label1);
        //Player#2
        TextField textField2 = new TextField();
        textField2.setBounds(300,140,150,20);
        container.add(textField2);
        //set text
        JLabel label2 = new JLabel("Player#2");
        label2.setForeground(c);
        label2.setBounds(200,50,100,200);
        container.add(label2);
        //start
        Color c1 = new Color(0,255,255);
        JButton button = new JButton("Go");
        button.setFont(f);
        button.setForeground(c);
        button.setBackground(c1);
        button.setOpaque(false);
        button.setBorderPainted(false);//设置是否绘制边框
        button.setBorder(null);//设置边框
        button.setBounds(380,0,90,50);
        container.add(button);
        MyActionListener myActionListener = new MyActionListener(textField1,textField2);
        button.addActionListener(myActionListener);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
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
        stopAndPlay.setBounds(-20,-20,80  ,80);
        stopAndPlay.setBackground(c);
        stopAndPlay.setOpaque(false);
        stopAndPlay.setFocusPainted(false);
        stopAndPlay.setBorder(null);
        frame.add(stopAndPlay);
        stopAndPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player.run){player.stop();stopAndPlay.setIcon(image1);
                }
                else {player.continues();stopAndPlay.setIcon(image2);}
            }
        });
        b=new BackgroundPanel((new ImageIcon("img\\2kkobe.jpg")).getImage());
        b.setBounds(0,0,474,266);
        container.add(b);
        frame.setVisible(true);
        frame.setResizable(false);
        container.setBackground(Color.black);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        container.setLayout(null);

        //修改java咖啡
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        frame.setIconImage(NBA);




    }


    class MyActionListener implements ActionListener {
        private TextField Player1,Player2;
        public MyActionListener(TextField Player1, TextField Player2){
            this.Player1 = Player1;
            this.Player2 = Player2;

        }



        @Override
        public void actionPerformed(ActionEvent e) {
             Player01 = Player1.getText();
             Player02 = Player2.getText();
             Player p1=new Player(Player01);
             Player p2=new Player(Player02);
             new ChooseTeam(p1,p2,player);

        }
    }



}