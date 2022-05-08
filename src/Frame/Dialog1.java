package Frame;
import controller.GameController;
import entity.Player;
import mineclearance.GamePanel;
import mineclearance.Grid;
import mineclearance.ScoreBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this dialog generate after clicking user-defined button
public class Dialog1 extends JFrame {


    private TextField row;
    private TextField column;
    private TextField MineCount;
    private TextField CountsOfTurn;
    public String Player01,Player02;
    public Sound player;
    Player p1;
    Player p2;
    BackgroundPanel p;
    public Dialog1(Player p1,Player p2,Sound player){
        Font f=new Font("Microsoft Yahei",Font.BOLD,60);
        Color c = new Color(255,255,255);
        this.player=player;
        this.p1=p1;
        this.p2=p2;
        Container container = this.getContentPane();
        container.setLayout(null);
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
        //textArea
        //JTextArea textArea = new JTextArea(20,50);
        //textArea.setText("row");

        //Panel
        //JScrollPane panel = new JScrollPane(textArea);
        //container.add(panel);
        //row
        TextField textField1 = new TextField();
        textField1.setBounds(200,50,150,20);
        container.add(textField1);
        //set text
        JLabel label1 = new JLabel("column<=30");
        label1.setForeground(c);
        label1.setBounds(50,50,200,20);
        add(label1);

        //column
        TextField textField2 = new TextField();
        textField2.setBounds(200,100,150,20);
        container.add(textField2);
        //set text
        JLabel label2 = new JLabel("row<=24");
        label2.setForeground(c);
        label2.setBounds(50,100,200,20);
        add(label2);

        //Mine Count
        TextField textField3 = new TextField();
        textField3.setBounds(200,150,150,20);
        container.add(textField3);
        //set text
        JLabel label3 = new JLabel("Mine Count<=50%");
        label3.setForeground(c);
        label3.setBounds(50,150,200,20);
        add(label3);

        //counts of turn
        TextField textField4 = new TextField();
        textField4.setBounds(200,200,150,20);
        container.add(textField4);
        //set text
        JLabel label4 = new JLabel("counts of turn(1-5)");
        label4.setForeground(c);
        label4.setBounds(50,200,200,20);
        add(label4);

        //Confirm Button
        JButton button = new JButton("Go");
        button.setFont(f);
        button.setForeground(c);
        button.setBackground(c);
        button.setOpaque(false);
        button.setBorderPainted(false);//设置是否绘制边框
        button.setBorder(null);//设置边框
        button.addActionListener(new MyActionListener(textField1,textField2,textField3,textField4));
        button.setBounds(150,250,90,50);
        container.add(button);
        ImageIcon image3 = new ImageIcon("img\\James.jpg");
        Image i = image3.getImage();
        i = i.getScaledInstance(640, 360,Image.SCALE_DEFAULT);
        image3.setImage(i);
        p=new BackgroundPanel(image3.getImage());
        p.setBounds(0,0,640,360);
        container.add(p);
        this.setVisible(true);
        this.setBounds(500,200,640,360);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image NBA = tk.getImage("img\\NBA.jpg");
        this.setIconImage(NBA);
    }



    class MyActionListener implements ActionListener{
        private final TextField row;
        private final TextField column;
        private final TextField MineCount;
        private final TextField CountsOfTurn;
        public MyActionListener(TextField row, TextField column, TextField MineCount, TextField CountsOfTurn){
            this.row = row;
            this.column = column;
            this.MineCount = MineCount;
            this.CountsOfTurn = CountsOfTurn;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if (row.getText()==null||column.getText()==null
            ||MineCount.getText()==null||CountsOfTurn==null){
                new DialogWrong();
            }
            try {
                int Row = Integer.parseInt(row.getText());
            int Col = Integer.parseInt(column.getText());
            int Mine = Integer.parseInt(MineCount.getText());
            int Turns = Integer.parseInt(CountsOfTurn.getText());
            if(Row<=0||Row>30||Col<=0||Col>24||Mine<=0||Mine>0.5*Row*Col||Turns<1||Turns>5)
                new DialogWrong();
             else    {new GameDialog(Row,Col,Mine,Turns,p1,p2,player);} }
            catch (NumberFormatException a){
                new DialogWrong();
            }

        }
    }


}

