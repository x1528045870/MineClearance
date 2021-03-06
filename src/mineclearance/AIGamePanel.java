package mineclearance;
import Components.Btn;
import entity.GridStatus;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import Frame.AIDialog;
import Frame.SingleVictory3;
import Frame.SingleLose;
import Frame.StoryIntroduction3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;


public class AIGamePanel extends JPanel {
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
    public int AIScore;
    public int AIMistake;
    public AIGamePanel(int xCount, int yCount, int mineCount) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setLocation(190,0);
        buttonSize=xCount*gridSize/6;
        f=new Font("Microsoft Yahei",Font.BOLD,buttonSize/6);
        this.setSize(Grid.gridSize * xCount+buttonSize, Grid.gridSize * yCount);
        this.x=xCount;
        this.y=yCount;
        this.mineCount=mineCount;
        this.remainMineCount=mineCount;
        addXRay();
        iniGame(xCount, yCount, mineCount);
    }
    public void generateChessBoard(int xCount, int yCount, int mineCount) {//
        //θΏιζζ£ηζ©ε€§δΊδΈδΈ
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
        for(int i=1;i<=xCount;i++){//θ?°ε½ε¨ε΄ι·ηδΈͺζ°
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
        ImageIcon image4=new ImageIcon("img\\ζζ.png");
        Image img = image4.getImage();
        img = img.getScaledInstance(Grid.gridSize, Grid.gridSize,Image.SCALE_DEFAULT);
        image4.setImage(img);
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                JLabel l = new JLabel("", JLabel.CENTER);
                // θ?Ύη½?ζ―δΈͺε°ζΉζ ΌηθΎΉη
                l.setBounds(j * gridSize, i * gridSize, gridSize, gridSize);
                // η»εΆζΉζ ΌθΎΉζ‘
                l.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                // θ?Ύη½?ζΉζ ΌδΈΊιζ,δΎΏδΊζδ»¬ε‘«ει’θ²
                l.setOpaque(true);
                // θζ―ε‘«εδΈΊι»θ²
                l.setBackground(Color.WHITE);
                l.setText(String.valueOf(mineField[i][j].getContent()));
                // ε°ζΉζ Όε ε₯ε°ε?Ήε¨δΈ­(ε³ι’ζΏJPanel)
                if (mineField[i][j].getContent()==0){
                    l.setText("");
                }
                if (mineField[i][j].isHasMine()){
                    l.setIcon(image4);
                    //l.setIcon();
                }
                this.add(l);
                // ε°ζΉζ Όε­ε°η±»ειδΈ­,ζΉδΎΏε¬η¨
                back[i][j] = l;
                back[i][j].setVisible(false);
            }
        }
    }
    /* η»εΆζι? */
    private void iniBtn(int xCount,int yCount) {
        ImageIcon image1=new ImageIcon("img\\η―?ηζ‘.jpg");
        Image img = image1.getImage();
        img = img.getScaledInstance(Grid.gridSize, Grid.gridSize,Image.SCALE_DEFAULT);
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
                                             /* ε·¦ι?ηΉε» */
                                             if(e.getButton() == MouseEvent.BUTTON1) left(btn,btn.i,btn.j);
                                             /* ε³ι?ηΉε» */
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
        AIScore=0;
        AIMistake=0;
    }
    private void right(Btn btn, int i, int j) {
        if(mineField[i][j].getStatus()==GridStatus.Covered){
            if(mineField[i][j].isHasMine()){
                ImageIcon image2=new ImageIcon("img\\η―?ηε₯η½.jpg");
                Image img = image2.getImage();
                img = img.getScaledInstance(Grid.gridSize, Grid.gridSize,Image.SCALE_DEFAULT);
                image2.setImage(img);
                face[i][j].setIcon(image2);
                score++;
                remainMineCount--;
                victory();lose();
            }
            if(!mineField[i][j].isHasMine()){
                ImageIcon image3=new ImageIcon("img\\ζιε£°ε£°.png");
                Image img = image3.getImage();
                img = img.getScaledInstance(Grid.gridSize, Grid.gridSize,Image.SCALE_DEFAULT);
                image3.setImage(img);
                face[i][j].setIcon(image3);
                mistake++; lose();victory();}
            AICheck();
            mineField[i][j].setStatus(GridStatus.Flag);
            AIDialog.controller.getScoreBoard().update(score,mistake,remainMineCount);//TODO:εδΊΊscoreboard


        }
    }

    private void left(Btn btn,int i,int j) {
        if(mineField[i][j].getStatus()==GridStatus.Covered) {
            checkPanel(i,j);first=false;
            if(mineField[i][j].isHasMine()){
                score--;
                remainMineCount--;lose();victory();
            }
            face[i][j].setVisible(false);
            back[i][j].setVisible(true);
            mineField[i][j].setStatus(GridStatus.Clicked);
            meet0(i,j);
            AIDialog.controller.getScoreBoard().update(score,mistake,remainMineCount);//TODO:
            AICheck();
        }

    }
    public void checkPanel(int i,int j){//ιΏει¦ζ¬‘θ§¦ι·by xed
        if(mineField[i][j].isHasMine()&&first){
            iniPanel(x,y,mineCount);
            checkPanel(i,j);
        }
    }//ηηζ―ε¦339ι·
    public boolean checkFullMine(int i,int j){
        int t=0;
        for(int a=i;a>i-3;a--){
            for(int b=j;b>j-3;b--){
                if(chessboard[a][b]==-1)t++;
            }
        }
        return t==9;
    }//θ?‘η?ε¨ε΄ι·ηδΈͺζ°
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
    /* ιθ§ε¨ */
    public void addXRay(){
        XRay=new Btn();
        XRay.setFont(f);
        XRay.setText("ι");
        XRay.i=x;
        XRay.j=y;
        XRay.setBounds(y* gridSize, 0, buttonSize, buttonSize);
        XRay.setVisible(true);
        this.add(XRay);
        XRay.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                /* ε·¦ι?ηΉε» */
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
        if (score- AIScore>remainMineCount){
            new StoryIntroduction3();
            }
        else if(remainMineCount==0){
            if(mistake<AIMistake){
                new StoryIntroduction3();
            }
        }
    }
    public void lose(){
        if (AIScore- score>remainMineCount){
            new SingleLose();
        }
        else if(remainMineCount==0){
            if(mistake>AIMistake){
                new SingleLose();
            }
        }
    }
    public void AICheck(){
        if (remainMineCount!=0){
        int i=  random.nextInt(x);
        int j= random.nextInt(y);
        boolean z=random.nextBoolean();
        if (z){ if(mineField[i][j].getStatus()==GridStatus.Covered){
            if(mineField[i][j].isHasMine()){
                ImageIcon image2=new ImageIcon("img\\η―?ηε₯η½.jpg");
                Image img = image2.getImage();
                img = img.getScaledInstance(Grid.gridSize, Grid.gridSize,Image.SCALE_DEFAULT);
                image2.setImage(img);
                face[i][j].setIcon(image2);
                AIScore++;
                remainMineCount--;
                lose();
                victory();
            }
            if(!mineField[i][j].isHasMine()){
                ImageIcon image3=new ImageIcon("img\\ζιε£°ε£°.png");
                Image img = image3.getImage();
                img = img.getScaledInstance(Grid.gridSize, Grid.gridSize,Image.SCALE_DEFAULT);
                image3.setImage(img);
                face[i][j].setIcon(image3);
                AIMistake++; victory();lose();}
            mineField[i][j].setStatus(GridStatus.Flag);
            AIDialog.controller.getScoreBoard().AIUpdate(AIScore,AIMistake);
        }
         else {AICheck();}
        }
        if(!z){
            if(mineField[i][j].getStatus()==GridStatus.Covered) {
                checkPanel(i,j);first=false;
                if(mineField[i][j].isHasMine()){
                    AIScore--;
                    remainMineCount--;
                }
                face[i][j].setVisible(false);
                back[i][j].setVisible(true);
                mineField[i][j].setStatus(GridStatus.Clicked);
                meet0(i,j);
                AIDialog.controller.getScoreBoard().AIUpdate(AIScore,AIMistake);//TODO:
                victory();
                lose();
            }
            else {AICheck();}
        }}
    }
    public int getRemainMineCount() {
        return remainMineCount;
    }
}

