package mineclearance;
import Components.Btn;
import entity.GridStatus;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import Frame.GameDialog3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;


public class GamePanel3 extends JPanel {
     int gridSize=Grid.gridSize ;
    private JLabel back[][];
    private Btn face[][];
    public int x;
    public int y;
    public int buttonSize;
    public int mineCount;
    private int clickTimes;
    private Grid [][]mineField;
    private int[][] chessboard;
    private Btn XRay;
    private Btn aBtn;
    private Btn meet0;
    private Btn hui;
    private Btn pBtn;
    private boolean meet;
    public int[]lastStep=new int[3];
    private final Random random = new Random();
    public int remainMineCount;
    Font f;
    int[][] message;
    public GamePanel3(int xCount, int yCount, int mineCount,int clickTimes) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setLocation(190,0);
        buttonSize=xCount*gridSize/6;
        f=new Font("Microsoft Yahei",Font.BOLD,buttonSize/6);
        this.setSize(Grid.gridSize * xCount+buttonSize, Grid.gridSize * yCount);
        this.clickTimes=clickTimes;
        this.x=xCount;
        this.y=yCount;
        this.mineCount=mineCount;
        this.remainMineCount=mineCount;
        addXRay();
        addRecover();
        addSaveIn();
        addMeet0();
        addBack();
        addNew();
        iniGame(xCount, yCount, mineCount);
    }

    public void generateChessBoard(int[][]message){
        chessboard=new int[message[0][0]+2][message[0][1]+2];
        for(int t=0;t<mineCount;t++){
            int i=message[1][t];
            int j=message[2][t];
            chessboard[i+1][j+1]=-1;
        }
        for(int i=1;i<=x;i++){//记录周围雷的个数
            for (int j=1;j<=y;j++){
                if(chessboard[i][j]!=-1)chessboard[i][j]=calculateMine(i,j);
            }
        }
    }
    public void generateChessBoard(int xCount, int yCount, int mineCount) {//
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
        for(int i=1;i<=xCount;i++){//记录周围雷的个数
            for (int j=1;j<=yCount;j++){
                if(chessboard[i][j]!=-1)chessboard[i][j]=calculateMine(i,j);
            }
        }
    }
    public void initialGrid(int [][]message){
        generateChessBoard(message);
        mineField=new Grid[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
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
        img = img.getScaledInstance(Grid.gridSize, Grid.gridSize,Image.SCALE_DEFAULT);
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
    /*public void iniGame(int [][]message){
        initialGrid(message);
        initLabel(x,y,mineCount);
        iniBtn(x,y);
        meet=false;
        int i;int j;
        if(message[3]!=null){
            for(int t=0;t<message[3].length;t++){//将翻开的翻开
                i=message[3][t];
                j=message[4][t];
                face[i][j].setVisible(false);
                back[i][j].setVisible(true);
                mineField[i][j].setStatus(GridStatus.Clicked);
            }}
        //将插旗的插旗
        if(message[5]!=null){
            for (int t=0;t<message[5].length;t++){
                i=message[5][t];
                j=message[6][t];
                face[i][j].setText(".");
                mineField[i][j].setStatus(GridStatus.Flag);
            }}
    }*/
    public void iniGame(int xCount,int yCount,int mineCount){
        initialGrid(xCount, yCount, mineCount);
        initLabel(xCount, yCount, mineCount);
        iniBtn(xCount, yCount);
        meet=false;
    }
    public void iniPanel(int xCount,int yCount,int mineCount){
        initialGrid(xCount, yCount, mineCount);
        initLabel(xCount, yCount, mineCount);
    }
    private void right(Btn btn, int i, int j) {
        if(mineField[i][j].getStatus()==GridStatus.Covered){

            lastStep[0]=i;
            lastStep[1]=j;
            lastStep[2]= GameDialog3.controller.getOnTurnPlayerNum();
            if(mineField[i][j].isHasMine()){
                ImageIcon image2=new ImageIcon("img\\篮球入网.jpg");
                Image img = image2.getImage();
                img = img.getScaledInstance(Grid.gridSize, Grid.gridSize,Image.SCALE_DEFAULT);
                image2.setImage(img);
                face[i][j].setIcon(image2);
                GameDialog3.controller.getOnTurnPlayer().addScore();
                remainMineCount--;
            }
            if(!mineField[i][j].isHasMine()){
                ImageIcon image3=new ImageIcon("img\\打铁声声.png");
                Image img = image3.getImage();
                img = img.getScaledInstance(Grid.gridSize, Grid.gridSize,Image.SCALE_DEFAULT);
                image3.setImage(img);
                face[i][j].setIcon(image3);
                GameDialog3.controller.getOnTurnPlayer().addMistake();}
            GameDialog3.controller.getOnTurnPlayer().cutClicks();
            if (GameDialog3.controller.getOnTurnPlayer().getClicks()==GameDialog3.controller.getClickTimes()){
                meet0.setEnabled(true);
                meet0.setText("联");
            }
            if (GameDialog3.controller.getOnTurnPlayer().getClicks()<GameDialog3.controller.getClickTimes()){
                meet0.setEnabled(false);
                meet0.setText("技");
                stopMeet0();
            }
            if (GameDialog3.controller.getOnTurnPlayer().getClicks()==GameDialog3.controller.getClickTimes()-2){
                hui.setEnabled(true);
            }
            mineField[i][j].setStatus(GridStatus.Flag);
            if(GameDialog3.controller.getOnTurnPlayer().getClicks()==0||remainMineCount==0){
                GameDialog3.controller.nextTurn();}
            GameDialog3.controller.getScoreBoard().update();


        }
    }

    private void left(Btn btn,int i,int j) {
        if(mineField[i][j].getStatus()==GridStatus.Covered) {
            checkPanel(i,j);
            lastStep[0]=i;
            lastStep[1]=j;
            lastStep[2]= GameDialog3.controller.getOnTurnPlayerNum();

            if(mineField[i][j].isHasMine()){
                GameDialog3.controller.getOnTurnPlayer().costScore();
                remainMineCount--;
            }
            GameDialog3.controller.getOnTurnPlayer().cutClicks();


            btn.setVisible(false);
            back[i][j].setVisible(true);
            mineField[i][j].setStatus(GridStatus.Clicked);
            if(meet&&mineField[i][j].getContent()==0){
                meet0(i,j);
            }
            if (GameDialog3.controller.getOnTurnPlayer().getClicks()==GameDialog3.controller.getClickTimes()){
                meet0.setEnabled(true);
                meet0.setText("联");
            }
            if (GameDialog3.controller.getOnTurnPlayer().getClicks()<GameDialog3.controller.getClickTimes()){
                meet0.setEnabled(false);
                meet0.setText("技");
                stopMeet0();
            }
            if (GameDialog3.controller.getOnTurnPlayer().getClicks()==GameDialog3.controller.getClickTimes()-2){
                hui.setEnabled(true);
            }
            if(GameDialog3.controller.getOnTurnPlayer().getClicks()==0||remainMineCount==0){
                GameDialog3.controller.nextTurn();
            }
            GameDialog3.controller.getScoreBoard().update();
        }

    }
    public void checkPanel(int i,int j){//避免首次触雷by xed
        int turn=GameDialog3.controller.getTurns();
        int Clicks=GameDialog3.controller.getClick();

        if(mineField[i][j].isHasMine()&&turn==0&&GameDialog3.controller.getOnTurnPlayer().getClicks()==GameDialog3.controller.getClickTimes()){
            iniPanel(x,y,mineCount);GameDialog3.controller.setTurns(0);
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
    //悔
    public void addBack(){
        hui=new Btn();
        hui.setFont(f);
        hui.setEnabled(false);
        hui.setText("悔");
        hui.i=x+2;
        hui.j=y+2;
        hui.setBounds(y* gridSize, buttonSize*4, buttonSize, buttonSize);
        hui.setVisible(true);
        this.add(hui);
        hui.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                /* 左键点击 */
                if(e.getButton() == MouseEvent.BUTTON1) {
                    back();
                    hui.setEnabled(false);
                }
            }

        });
    }
    public void back(){
        int i=lastStep[0];
        int j=lastStep[1];
        if (mineField[i][j].getStatus()==GridStatus.Clicked){
            mineField[i][j].setStatus(GridStatus.Covered);
            back[i][j].setVisible(false);
            face[i][j].setVisible(true);
            if(lastStep[2]==1){
                if(GameDialog3.controller.getP1().getClicks()==0){
                    GameDialog3.controller.goBackTurn();
                }
                GameDialog3.controller.getP1().addClicks();
                if (mineField[i][j].isHasMine()){
                    GameDialog3.controller.getP1().addScore();
                    remainMineCount++;
                }
            }
            else if(lastStep[2]==2){
                if(GameDialog3.controller.getP1().getClicks()==0){
                    GameDialog3.controller.goBackTurn();
                }
                GameDialog3.controller.getP2().addClicks();
                if (mineField[i][j].isHasMine()){
                    GameDialog3.controller.getP2().addScore();
                    remainMineCount++;
                }
            }
        }
        else if (mineField[i][j].getStatus()==GridStatus.Flag){
            mineField[i][j].setStatus(GridStatus.Covered);
            face[i][j].setText(null);
            if(lastStep[2]==1){
                if(GameDialog3.controller.getP1().getClicks()==0){
                    GameDialog3.controller.goBackTurn();
                }
                GameDialog3.controller.getP1().addClicks();
                if (mineField[i][j].isHasMine()){
                    GameDialog3.controller.getP1().costScore();
                    remainMineCount++;
                }
                else {
                    GameDialog3.controller.getP1().cutMistake();
                }
            }
            else if(lastStep[2]==2){
                if(GameDialog3.controller.getP1().getClicks()==0){
                    GameDialog3.controller.goBackTurn();
                }
                GameDialog3.controller.getP2().addClicks();
                if (mineField[i][j].isHasMine()){
                    GameDialog3.controller.getP2().costScore();
                    remainMineCount++;
                }
                else {
                    GameDialog3.controller.getP2().cutMistake();
                }
            }
        }
        GameDialog3.controller.getScoreBoard().update();
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
    public void addMeet0(){
        meet0=new Btn();
        meet0.setFont(f);
        meet0.setText("联");
        meet0.setEnabled(true);
        meet0.i=x;
        meet0.j=y;
        meet0.setBounds(y* gridSize, 3*buttonSize, buttonSize, buttonSize);
        meet0.setVisible(true);
        this.add(meet0);
        meet0.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                /* 左键点击 */
                if(e.getButton() == MouseEvent.BUTTON1)startMeet0();
                if(e.getButton() == MouseEvent.BUTTON3)stopMeet0();
            }

        });
    }
    public void startMeet0(){
        meet=true;
    }
    public void stopMeet0(){
        meet=false;
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
    public void addRecover(){
        aBtn=new Btn();
        aBtn.setFont(f);
        aBtn.setText("盖");
        aBtn.i=x;
        aBtn.j=y;
        aBtn.setBounds(y* gridSize, buttonSize, buttonSize, buttonSize);
        aBtn.setVisible(true);
        this.add(aBtn);
        aBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                /* 左键点击 */
                if(e.getButton() == MouseEvent.BUTTON1) {recover(x,y);
                    GameDialog3.controller.init(GameDialog3.controller.getP1(), GameDialog3.controller.getP2(),GameDialog3.controller.getP3());
                    remainMineCount = mineCount;
                    GameDialog3.controller.getScoreBoard().update();}
                if(e.getButton() == MouseEvent.BUTTON3) allClick(x,y);
            }

        });
    }
    public void addNew(){
        pBtn=new Btn();
        pBtn.setFont(f);
        pBtn.setText("新");
        pBtn.i=x;
        pBtn.j=y;
        pBtn.setBounds(y* gridSize, 5*buttonSize, buttonSize, buttonSize);
        pBtn.setVisible(true);
        this.add(pBtn);
        pBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                /* 左键点击 */
                if(e.getButton() == MouseEvent.BUTTON1) {recover(x,y);
                    initialGrid(x,y,mineCount);initLabel(x,y,mineCount);
                    GameDialog3.controller.init(GameDialog3.controller.getP1(), GameDialog3.controller.getP2(),GameDialog3.controller.getP3());
                    remainMineCount=mineCount;
                    GameDialog3.controller.getScoreBoard().update();}
            }

        });
    }
    public void  allClick(int xCount,int yCount){
        for (int i = 0;i <xCount ;i++){
            for(int j = 0;j < yCount; j++){
                {face[i][j].setVisible(false);
                    back[i][j].setVisible(true);}
            }
        }
    }
    public void  recover(int xCount,int yCount){
        for (int i = 0;i <xCount ;i++){
            for(int j = 0;j < yCount; j++){
                {face[i][j].setVisible(true);
                    back[i][j].setVisible(false);
                    if(mineField[i][j].getStatus()==GridStatus.Flag){
                        face[i][j].setText("");
                    }
                    mineField[i][j].setStatus(GridStatus.Covered);}
            }
            GameDialog3.controller.setTurns(0);GameDialog3.controller.getOnTurnPlayer().setClicks();
        }
    }//存档
    public void addSaveIn(){
        Btn saveIn=new Btn();
        saveIn.setFont(f);
        saveIn.setText("存");
        saveIn.i=x+1;
        saveIn.j=y+1;
        saveIn.setBounds(y* gridSize, 2*buttonSize, buttonSize, buttonSize);
        saveIn.setVisible(true);
        this.add(saveIn);
        saveIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                /* 左键点击 */
                //if(e.getButton() == MouseEvent.BUTTON1) saveIn();
            }

        });
    }
    //存档的具体内容
   /* public void saveIn(){
        int result = 0;
        File file = null;
        String path = null;
        JFileChooser fileChooser = new JFileChooser();
        FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
        GameDialog3.println(fsv.getHomeDirectory());                //得到桌面路径
        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
        fileChooser.setDialogTitle("请选择要上传的文件...");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        result = fileChooser.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == result) {
            path=fileChooser.getSelectedFile().getPath();
            GameDialog3.println("path: "+path);
        }
        saveWhat();
        File f = new File(path+".txt");
        if(f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0;i<10;i++) {
            for(int j = 0;j<message[i].length;j++) {
                try {
                    fw.write(message[i][j]+"\t");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fw.write("\r\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void saveWhat(){//记录信息
        message=new int[10][];
        //0用来记录基本信息
        message[0]=new int[4];
        message[0][0]=x;
        message[0][1]=y;
        message[0][2]=mineCount;
        message[0][3]=clickTimes;
        //记录雷点
        int t=0;
        message[1]=new int[mineCount];
        message[2]=new int[mineCount];
        for (int i = 0;i <x ;i++){
            for(int j = 0;j < y; j++){
                if(mineField[i][j].isHasMine()){
                    message[1][t]=i;
                    message[2][t]=j;
                    t++;}
            }
        }
        //记录按钮状态
        message[3]=new int[findClickNum()];
        message[4]=new int[findClickNum()];
        int a=0;
        for (int i = 0;i <x ;i++){
            for(int j = 0;j < y; j++){
                if(mineField[i][j].getStatus()==GridStatus.Clicked){
                    message[3][a]=i;
                    message[4][a]=j;
                    a++;}
            }
        }
        message[5]=new int[findFlagNum()];
        message[6]=new int[findFlagNum()];
        int b=0;
        for (int i = 0;i <x ;i++){
            for(int j = 0;j < y; j++){
                if(mineField[i][j].getStatus()==GridStatus.Flag){
                    message[5][b]=i;
                    message[6][b]=j;
                    b++;}
            }
        }
        //记录回合数，计时,当前玩家，悔棋次数等
        message[7]=new int[4];
        message[7][0]=GameDialog3.controller.getTurns();
        message[7][1]=GameDialog3.controller.getOnTurnPlayerNum();
        message[7][2]=GameDialog3.controller.getOnTurnPlayer().getClicks();//记录当前玩家的click次数
        message[7][3]=remainMineCount;
        message[8]=new int[2];
        message[9]=new int[2];
        //记录玩家信息
        message[8][0]=GameDialog3.controller.getP1().getScore();
        message[9][0]=GameDialog3.controller.getP2().getScore();
        message[8][1]=GameDialog3.controller.getP1().getMistake();
        message[9][1]=GameDialog3.controller.getP2().getMistake();
    }*/
    public int findClickNum(){
        int t=0;
        for (int i = 0;i <x ;i++){
            for(int j = 0;j < y; j++){
                if(mineField[i][j].getStatus()==GridStatus.Clicked)
                    t++;
            }
        }
        return t;
    }
    public int findFlagNum(){
        int t=0;
        for (int i = 0;i <x ;i++){
            for(int j = 0;j < y; j++){
                if(mineField[i][j].getStatus()==GridStatus.Flag)
                    t++;
            }
        }
        return t;
    }

    public int getRemainMineCount() {
        return remainMineCount;
    }
}

