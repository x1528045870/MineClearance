package Frame;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.util.concurrent.TimeUnit;

public class BeforeStart implements ActionListener {
    JFrame frame = new JFrame();
    JButton story = new JButton("故事模式");
    JButton pvp = new JButton("PVP");
    JButton triple = new JButton("三人对战");
    JButton load = new JButton("读档");
    JButton bb=new JButton("排行榜");
    JButton rule=new JButton("规则");
    BackgroundPanel bg;
    int [][]message;
    Sound player;
    String[]nameSplit;
    public BeforeStart(Sound player){

        this.player=player;
        Font f=new Font("华文行楷",Font.BOLD,30);
        Color c = new Color(0,255,255);
        Color c1 = new Color(255,255,0);
        Color c2=new Color(255,0,0);
        story.setFont(f);
        story.setForeground(c);
        story.setBackground(c);
        story.setOpaque(false);
        //story.setBorderPainted(false);//设置是否绘制边框
        //story.setBorder(null);//设置边框
        pvp.setFont(f);
        pvp.setForeground(c);
        pvp.setBackground(c1);
        pvp.setOpaque(false);
        //pvp.setBorderPainted(false);//设置是否绘制边框
        //pvp.setBorder(null);//设置边框
        triple.setFont(f);
        triple.setForeground(c);
        triple.setBackground(c2);
        triple.setOpaque(false);
        //triple.setBorderPainted(false);//设置是否绘制边框
        //triple.setBorder(null);//设置边框
        load.setFont(f);
        load.setForeground(c);
        load.setBackground(c);
        load.setOpaque(false);
        //load.setBorderPainted(false);//设置是否绘制边框
        //load.setBorder(null);//设置边框
        story.setBounds(1068,0,196,108);
        pvp.setBounds(801,144,196,108);
        triple.setBounds(264,432,196,108);
        load.setBounds(0,576,196,108);
        setActionListenerLoad(load);
        frame.add(story);
        frame.add(pvp);
        pvp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Start().init(player);
            }
        });
        triple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Start3().init(player);
            }
        });
        story.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StoryIntroduction(player);
            }
        });
        frame.add(triple);
        frame.add(load);
        bb.setFont(f);
        bb.setForeground(c);
        bb.setBackground(c);
        bb.setOpaque(false);
        frame.add(bb);
        bb.setBounds(0,0,12,12);
        bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new bb();
            }
        });
        rule.setFont(f);
        rule.setForeground(c);
        rule.setBackground(c);
        rule.setOpaque(false);
        frame.add(rule);
        rule.setBounds(536,292,196,108);
        rule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rule();
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
        stopAndPlay.setBounds(1220,634,80  ,80);
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
        bg=new BackgroundPanel((new ImageIcon("img\\nba_2k212.jpg")).getImage());
        bg.setBounds(0,0,1280,720);
        frame.add(bg);
        frame.setSize(1280,720);
        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);
        //修改java咖啡
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        frame.setIconImage(NBA);


    }



    @Override
    public void actionPerformed(ActionEvent e){
        //TODO add actionListener to four buttons
        if (e.getSource() == story){

        }



    }
    public void setActionListenerLoad(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button){
                    readIt();
                    if(message!=null){
                    new GameDialog(nameSplit[0],nameSplit[1],message);}
                }
            }
        });
    }
    public void readIt(){
        int result = 0;
        File file = null;
        String path = null;
        JFileChooser fileChooser = new JFileChooser();
        FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句//得到桌面路径
        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
        fileChooser.setDialogTitle("请选择要上传的文件...");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        result = fileChooser.showOpenDialog(null);

        if (JFileChooser.APPROVE_OPTION == result) {
            path = fileChooser.getSelectedFile().getPath();

            File f = new File(path);
            try {
                Reader reader = new FileReader(f);
                message = new int[10][];
                BufferedReader read = new BufferedReader(reader);
                if (f.exists()) {
                    String name = null;
                    try {
                        name = read.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    nameSplit = name.split("\t");
                    int i = 0;
                    String sMessage = null;
                    while (true) {
                        try {
                            if ((sMessage = read.readLine()) == null) break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (sMessage != null && !sMessage.equals("")) {
                            String[] split = sMessage.split("\t");
                            message[i] = new int[split.length];
                            for (int j = 0; j < split.length; j++) {
                                if (!split[j].equals("")) {
                                    message[i][j] = Integer.parseInt(split[j]);

                                }
                            }
                        }
                        i++;
                    }

                }


                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }}


}