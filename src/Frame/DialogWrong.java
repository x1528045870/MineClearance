package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWrong extends JDialog {
    public DialogWrong(){
        setVisible(true);
        this.setBounds(500,500,500,200);
        Container container = this.getContentPane();
        container.setLayout(null);

        JLabel label = new JLabel("Wrong Input, Please send the right data");
        label.setBounds(150,35,500,50);

        container.add(label);

        JButton button = new JButton("Understand");
        button.setBounds(170,100,100,20);
        container.add(button);
        MyActionListener myActionListener = new MyActionListener();
        button.addActionListener(myActionListener);

    }
    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           dispose();
        }
    }
}

