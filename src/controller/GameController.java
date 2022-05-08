package controller;

import Components.Btn;
import entity.Player;
import mineclearance.GamePanel;
import mineclearance.ScoreBoard;
import Frame.Victory;
import entity.GridStatus;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import Frame.GameDialog;
import Frame.TimeRun;
public class GameController {
    private Player p1;//click in player
    private Player p2;
    private Player p3;
    private GamePanel gamePanel;
    private Player onTurn;
    private int xCount;
    private int yCount;
    private int mineCount;
    private int clickTimes;//set as final,get from out
    private int click;//cut it down
    private int turns;

    private ScoreBoard scoreBoard;
    public boolean time=false;
    public GameController(Player p1, Player p2,int Row,int Column,int Mine,int clickTimes ) {
        this.xCount=Column;
        this.yCount=Row;
        this.mineCount=Mine;
        this.clickTimes=clickTimes;
        this.init(p1, p2);

    }

    public GameController(Player p1,Player p2,int[][]message) {
        this.xCount=message[0][1];
        this.yCount=message[0][0];
        this.mineCount=message[0][2];
        this.clickTimes=message[0][3];
        this.p1=p1;
        this.p2=p2;
        if(message[7][1]==1){
            this.onTurn=p1;
        }
        else {
            this.onTurn=p2;
        }
        this.turns=message[7][0];
        this.onTurn.setClicks(message[7][2]);



    }
    public void init(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p1.setScore(0);
        this.p1.setMistake(0);
        this.p2.setScore(0);
        this.p2.setMistake(0);
        this.p1.setVictory(-1);
        this.p2.setVictory(-1);
        this.onTurn = p1;
        this.turns=0;
        time=false;
        this.p1.setClicks(clickTimes);
        this.p2.setClicks(0);




    }
    public void nextTurn() {
        if (onTurn == p1) {
            onTurn = p2;
        } else if (onTurn == p2) {
            onTurn = p1;
        }
        this.turns++;
        time=true;
        this.onTurn.setClicks(clickTimes);
        scoreBoard.update();

    }

    public void setOnTurn(Player onTurn) {
        this.onTurn = onTurn;
    }

    public void goBackTurn() {
        this.onTurn.setClicks(0);
        if (onTurn == p1) {
            onTurn = p2;
        } else if (onTurn == p2) {
            onTurn = p1;
        }
        this.turns--;
        time=true;

    }
    public void victory(){
        if (Math.abs(p1.getScore()- p2.getScore())>gamePanel.getRemainMineCount()){
            if(p1.getScore()> p2.getScore()){
                p1.setVictory(1);
            }
            else {p2.setVictory(1);
            }new Victory(p1,p2);

        }
        else if(gamePanel.getRemainMineCount()==0){
            if (p1.getMistake()< p2.getMistake()){
                p1.setVictory(1);
            }
            else if(p2.getMistake()< p1.getMistake()){
                p2.setVictory(1);
            }
            else{
                p1.setVictory(0);
                p2.setVictory(0);

            } new Victory(p1,p2);
        }
    }
    public Player getOnTurnPlayer() {
        return this.onTurn;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }
    public int getTurns() {
        return turns;
    }
    public void setTurns(int turns) {
        this.turns = turns;
    }
    public int getClickTimes() {
        return clickTimes;
    }
    public int getClick() {
        return click;
    }
    public void setClick(int click) {
        this.click = click;
    }

    public Player getP1(){
        return p1;
    }
    public Player getP2(){
        return p2;
    }
    public int getMineCount() {
        return mineCount;
    }
    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public int getXCount() {
        return xCount;
    }

    public void setXCount(int xCount) {
        this.xCount = xCount;
    }

    public int getYCount() {
        return yCount;
    }

    public void setYCount(int yCount) {
        this.yCount = yCount;
    }

    public boolean isTime() {
        return time;
    }

    public void setTime(boolean time) {
        this.time = time;
    }

    public int getOnTurnPlayerNum(){
        if (this.getOnTurnPlayer()==p1){
            return 1;
        }
        else return 2;
    }
}
