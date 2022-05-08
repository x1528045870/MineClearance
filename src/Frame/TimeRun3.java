package Frame;
import mineclearance.GamePanel;
import mineclearance.Grid;
import mineclearance.ScoreBoard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;
import javax.swing.Timer;
import java.text.*;
public class TimeRun3 extends JPanel implements ActionListener{
    public Timer time;

    public Timer getTime() {
        return time;
    }

    JTextField text;
    JButton Start,Stop,Clean;
    JLabel remainMineCount;
    //SimpleDateFormat m;
    long t = 0;
    public TimeRun3(int xCount,int yCount){
        time= new Timer(1000,this);              //timer(int a,object b)  time 对象做计时器的监视器;
        //m=new SimpleDateFormat("hh:mm:ss");         //设置时钟样式;
        text = new JTextField(10);
        Start = new JButton("start");
        Stop = new JButton("stop");
        Clean =new JButton("clean");
        remainMineCount=new JLabel("剩余"+String.valueOf(GameDialog3.controller.getGamePanel().getRemainMineCount()));
        Start.addActionListener(this);
        Stop.addActionListener(this);
        Clean.addActionListener(this);
        setLayout(null);
        remainMineCount.setLocation(0,140);
        Start.setLocation(0,110);
        Stop.setLocation(0, 40);
        Clean.setLocation(0,70);
        text.setLocation(0,10);
        remainMineCount.setSize(100,30);
        Start.setSize(70,30);
        Stop.setSize(70,30);
        Clean.setSize(70,30);
        text.setSize(100,30);
        add(remainMineCount);
        // add(Start);
        // add(Stop);
        // add(Clean);
        add(text);
        setSize(310,30);
        setLocation(yCount,10);
        validate();
        setVisible(true);
        //setLocation(xCount/2,yCount);
        time.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==time)
        { if(t==24|GameDialog3.controller.isTime()){
            time.stop();
            text.setText("");
            t=0;
            GameDialog3.controller.setTime(false);
            time.start();
        }   update();
            t=t+1;
            int s=(int)t%60;
            int m=(int)t/60%60;
            int h=(int)t/3600;

            text.setText("时间：  "+h+":"+m+":"+s);


        }
        if(e.getSource()==Start)
        {
            time.start();
        }
        if(e.getSource()==Stop)
        {
            time.stop();
        }
        if(e.getSource()==Clean)
        {
            text.setText("");
            time.stop();
        }
    }  public void update() {
        remainMineCount.setText("剩余"+ GameDialog3.controller.getGamePanel().getRemainMineCount());
    }


}




