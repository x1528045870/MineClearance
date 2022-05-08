package Frame;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class TUT extends JFrame {

    public TUT() {
        //修改java
        Toolkit tk = Toolkit.getDefaultToolkit();
        //三种图片格式都可以
        Image img = tk.getImage("img\\NBA.jpg");
        this.setIconImage(img);
        this.setSize(500, 400);
        this.setVisible(true);
    }



}