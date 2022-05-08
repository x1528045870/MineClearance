package Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
public class RealStart extends JFrame {
    Container ct;
    BackgroundPanel bgp;
    public static void main(String[] args) {
        new RealStart();
    }
        public RealStart(){
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image img = tk.getImage("img\\NBA.jpg");
            this.setIconImage(img);
            ct =this.getContentPane();
            Color c = new Color(0,255,255);
            ImageIcon icon = new ImageIcon("img\\nba_2k212button.jpg");
            JButton button = new JButton("Start",icon);
            Font f=new Font("华文行楷",Font.BOLD,60);
            button.setFont(f);
            button.setForeground(new Color(0,0,255));
            button.setBounds(536,292,196,108);
            button.setVerticalTextPosition(SwingConstants.CENTER);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setFocusPainted(true);
            button.setBorderPainted(true);//设置是否绘制边框
            //button.setBorder(null);//设置边框

            ct.add(button);
            ImageIcon image1=new ImageIcon("img\\播放.png");
            //Image img = image1.getImage();
            //img = img.getScaledInstance(66, 66,Image.SCALE_DEFAULT);
            //image1.setImage(img);
            JButton stopAndPlay=new JButton(image1);
            stopAndPlay.setBounds(380,30,210  ,210);
            stopAndPlay.setBackground(c);
            stopAndPlay.setOpaque(false);
            stopAndPlay.setFocusPainted(false);
            stopAndPlay.setBorder(null);

            ct.add(stopAndPlay);
            bgp=new BackgroundPanel((new ImageIcon("img\\nba_2k212.jpg")).getImage());
            bgp.setBounds(0,0,1280,720);
            ct.add(bgp);
            this.setSize(1280,720);
            this.setLocation(100,100);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);

            this.setLayout(null);
            Sound player = new Sound("music\\Def Leppard - Pour Some Sugar On Me (Remastered 2.wav");
            player.start(true);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //继续开始播放音频
            player.continues();
            stopAndPlay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(player.run){
                    player.stop();}
                    else {player.continues();}

                }
            });
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new BeforeStart(player);
                    dispose();
                }
            });


        }
    }


