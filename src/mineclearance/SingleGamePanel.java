package mineclearance;
import Components.Btn;
import entity.GridStatus;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import Frame.SingleGameDialog;
import Frame.SingleVictory3;
import Frame.SingleLose;
import Frame.StoryIntroduction2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;


public class SingleGamePanel extends JPanel {
    int gridSize=Grid.gridSize ;
    private JLabel back[][];
    private Btn face[][];
    public int x;
    public int y;
    public int buttonSize;
    public int mineCount;
    public int remainMineCount;
    private int clickTimes;
    private Grid [][]mineField;
    private int[][] chessboard;
    private Btn XRay;
    private Btn aBtn;
    private Btn meet0;
    private Btn hui;
    private Btn pBtn;
    public int[]lastStep=new int[3];
    private final Random random = new Random();
    Font f;
    public int score;
    public int mistake;
    public int needScore;
    public int loseMistake;
    public boolean first;
    public SingleGamePanel(int xCount, int yCount, int mineCount,int needScore,int loseMistake) {
        this.loseMistake=loseMistake;
        this.needScore=needScore;
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setLocation(190,0);
        buttonSize=xCount*gridSize/6;
        f=new Font("Microsoft Yahei",Font.BOLD,buttonSize/6);
        this.setSize(gridSize * xCount+buttonSize, gridSize * yCount);
        this.x=xCount;
        this.y=yCount;
        this.mineCount=mineCount;
        this.remainMineCount=mineCount;
        addXRay();
        iniGame(xCount, yCount, mineCount);
    }
    public void generateChessBoard(int xCount, int yCount, int mineCount) {
        //这里把棋盘扩大了一下
        chessboard = new int[xCount+2][yCount+2];
        int t=0;int b=1;int a=xCount*yCount/mineCount;
        for (int i = 1; i <= xCount; i++) {
            for (int j = 1; j <= yCount; j++) {
                // suppose -1 represents mine
                chessboard[i][j] = random.nextInt(a) -b;
                if(i>3&&j>3&&checkFullMine(i,j)){j=j-1;continue;}
                if(chessboard[i][j]==-1)t=t+1;
                if(t==mineCount){b=0;}
                if(i==xCount&&j==yCount&&t<mineCount){
                    i=1;j=1;t=0;
                }
            }
        }
        for(int i=1;i<=xCount;i++){
            for (int j=1;j<=yCount;j++){
                if(chessboard[i][j]!=-1)chessboard[i][j]=calculateMine(i,j);
            }
        }
    }

    public void initialGrid(int xCount, int yCount, int mineCount) {
        generateChessBoard(xCount, yCount, mineCount);
        mineField=new Grid[xCount][yCount];
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                Grid grid = new Grid(i, j);
                grid.setContent(chessboard[i+1][j+1]);
                grid.setStatus(GridStatus.Covered);
                if(chessboard[i+1][j+1]==-1) {
                    grid.setHasMine(true);
                }
                mineField[i][j] = grid;
            }
        }
    }
    public void initLabel(int xCount,int yCount,int mineCount) {
        back=new JLabel[xCount][yCount];
        ImageIcon image4=new ImageIcon("img\\手掌.png");
        Image img = image4.getImage();
        img = img.getScaledInstance(gridSize, gridSize,Image.SCALE_DEFAULT);
        image4.setImage(img);
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                JLabel l = new JLabel("", JLabel.CENTER);
                // 设置每个小方格的边界
                l.setBounds(j * gridSize, i * gridSize, gridSize, gridSize);
                // 绘制方格边框
                l.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                // 设置方格为透明,便于我们填充颜色
                l.setOpaque(true);
                // 背景填充为黄色
                l.setBackground(Color.WHITE);
                l.setText(String.valueOf(mineField[i][j].getContent()));
                // 将方格加入到容器中(即面板JPanel)
                if (mineField[i][j].getContent()==0){
                    l.setText("");
                }
                if (mineField[i][j].isHasMine()){
                    l.setIcon(image4);
                    //l.setIcon();
                }
                this.add(l);
                // 将方格存到类变量中,方便公用
                back[i][j] = l;
                back[i][j].setVisible(false);
            }
        }
    }
    /* 绘制按钮 */
    private void iniBtn(int xCount,int yCount) {
        ImageIcon image1=new ImageIcon("img\\篮球框.jpg");
        Image img = image1.getImage();
        img = img.getScaledInstance(gridSize, gridSize,Image.SCALE_DEFAULT);
        image1.setImage(img);
        face=new Btn[xCount][yCount];
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                Btn btn=new Btn();
                btn.i = i;
                btn.j = j;
                btn.setIcon(image1);
                btn.setBounds(j * gridSize, i * gridSize, gridSize, gridSize);
                btn.setVisible(true);
                this.add(btn);
                face[i][j] = btn;
                btn.addMouseListener(new MouseAdapter() {
                                         public void mouseClicked(MouseEvent e) {
                                             /* 左键点击 */
                                             if(e.getButton() == MouseEvent.BUTTON1) left(btn,btn.i,btn.j);
                                             /* 右键点击 */
                                             if(e.getButton() == MouseEvent.BUTTON3) right(btn,btn.i,btn.j);
                                         }

                                     }
                );

            }
        }

    }
    public void iniPanel(int xCount,int yCount,int mineCount){
        initialGrid(xCount, yCount, mineCount);
        initLabel(xCount, yCount, mineCount);
    }

    public void iniGame(int xCount,int yCount,int mineCount){
        initialGrid(xCount, yCount, mineCount);
        initLabel(xCount, yCount, mineCount);
        iniBtn(xCount, yCount);
        score=0;
        mistake=0;
        first=true;
    }
    private void right(Btn btn, int i, int j) {
        if(mineField[i][j].getStatus()==GridStatus.Covered){
            if(mineField[i][j].isHasMine()){
                ImageIcon image2=new ImageIcon("img\\篮球入网.jpg");
                Image img = image2.getImage();
                img = img.getScaledInstance(gridSize, gridSize,Image.SCALE_DEFAULT);
                image2.setImage(img);
                face[i][j].setIcon(image2);
                score++;
                remainMineCount--;
                victory();
            }
            if(!mineField[i][j].isHasMine()){
                ImageIcon image3=new ImageIcon("img\\打铁声声.png");
                Image img = image3.getImage();
                img = img.getScaledInstance(gridSize, gridSize,Image.SCALE_DEFAULT);
                image3.setImage(img);
                face[i][j].setIcon(image3);
                mistake++; lose();}
            mineField[i][j].setStatus(GridStatus.Flag);
            SingleGameDialog.controller.getScoreBoard().update(score,mistake,remainMineCount);


        }
    }

    private void left(Btn btn,int i,int j) {
        if(mineField[i][j].getStatus()==GridStatus.Covered) {
            checkPanel(i,j);first=false;
            if(mineField[i][j].isHasMine()){
                score--;
                remainMineCount--;
            }
            btn.setVisible(false);
            back[i][j].setVisible(true);
            mineField[i][j].setStatus(GridStatus.Clicked);
                meet0(i,j);
            SingleGameDialog.controller.getScoreBoard().update(score,mistake,remainMineCount);
        }

    }
    public void checkPanel(int i,int j){//避免首次触雷by xed
        if(mineField[i][j].isHasMine()&&first){
            iniPanel(x,y,mineCount);
            checkPanel(i,j);
        }
    }//看看是否339雷
    public boolean checkFullMine(int i,int j){
        int t=0;
        for(int a=i;a>i-3;a--){
            for(int b=j;b>j-3;b--){
                if(chessboard[a][b]==-1)t++;
            }
        }
        return t==9;
    }//计算周围雷的个数
    public int calculateMine(int i,int j){
        int t=0;
        if(chessboard[i-1][j-1]==-1)t=t+1;
        if(chessboard[i-1][j]==-1)t=t+1;
        if(chessboard[i][j-1]==-1)t=t+1;
        if(chessboard[i+1][j+1]==-1)t=t+1;
        if(chessboard[i+1][j]==-1)t=t+1;
        if(chessboard[i][j+1]==-1)t=t+1;
        if(chessboard[i-1][j+1]==-1)t=t+1;
        if(chessboard[i+1][j-1]==-1)t=t+1;
        return t;
    }
    /* 透视器 */
    public void addXRay(){
        XRay=new Btn();
        XRay.setFont(f);
        XRay.setText("透");
        XRay.i=x;
        XRay.j=y;
        XRay.setBounds(y* gridSize, 0, buttonSize, buttonSize);
        XRay.setVisible(true);
        this.add(XRay);
        XRay.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                /* 左键点击 */
                if(e.getButton() == MouseEvent.BUTTON1) showMine(x,y);
                if(e.getButton() == MouseEvent.BUTTON3) hideMine(x,y);
            }

        });
    }
    public void meet0(int i,int j){
        if(mineField[i][j].getContent()==0){
            if(i>0&&j>0){open(i-1,j-1);}
            if(i>0){open(i-1,j);}
            if(i>0&&j<y-1){open(i-1,j+1);}
            if(j>0){open(i,j-1);}
            if(j<y-1){open(i,j+1);}
            if(i<x-1){open(i+1,j);}
            if(i<x-1&&j>0){open(i+1,j-1);}
            if(i<x-1&&j<y-1){open(i+1,j+1);}

        }
    }

    public void open(int i,int j){
        if (mineField[i][j].getStatus()==GridStatus.Covered) {
            mineField[i][j].setStatus(GridStatus.Clicked) ;
            face[i][j].setVisible(false);
            back[i][j].setVisible(true);meet0(i,j);}
    }
    public void  showMine(int xCount,int yCount){
        for (int i = 0;i <xCount ;i++){
            for(int j = 0;j < yCount; j++){
                if(mineField[i][j].isHasMine())
                {face[i][j].setVisible(false);
                    back[i][j].setVisible(true); }
            }
        }
    }
    public void  hideMine(int xCount,int yCount){
        for (int i = 0;i <xCount ;i++){
            for(int j = 0;j < yCount; j++){
                if(mineField[i][j].isHasMine()&&(mineField[i][j].getStatus()!=GridStatus.Clicked))
                {face[i][j].setVisible(true);
                    back[i][j].setVisible(false); }
            }
        }
    }
    public void victory(){
        if (score>=needScore){
            new StoryIntroduction2();
        }
    }
    public void lose(){
        if(mistake>loseMistake&&mineCount==0){
            new SingleLose();
        }
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public JLabel[][] getBack() {
        return back;
    }

    public void setBack(JLabel[][] back) {
        this.back = back;
    }

    public Btn[][] getFace() {
        return face;
    }

    public void setFace(Btn[][] face) {
        this.face = face;
    }
    public void setFaceAndBack(int gridSize){
        ImageIcon image4=new ImageIcon("img\\手掌.png");
        Image img = image4.getImage();
        img = img.getScaledInstance(gridSize, gridSize,Image.SCALE_DEFAULT);
        image4.setImage(img);
        ImageIcon image1=new ImageIcon("img\\篮球框.jpg");
        Image im = image1.getImage();
        im = im.getScaledInstance(gridSize, gridSize,Image.SCALE_DEFAULT);
        image1.setImage(im);
        for (int i=0;i<x;i++){
            for (int j=0;j<y;j++ ){
                face[i][j].setBounds(j * gridSize, i * gridSize,gridSize,gridSize);
                back[i][j].setBounds(j * gridSize, i * gridSize,gridSize,gridSize);
                face[i][j].setIcon(image1);
                if(mineField[i][j].isHasMine()){
                    back[i][j].setIcon(image4);
                }
                buttonSize=x*gridSize/6;
                XRay.setBounds(y* gridSize, 0, buttonSize, buttonSize);
            }
        }
    }
    public int getRemainMineCount() {
        return remainMineCount;
    }
}
