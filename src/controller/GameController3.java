package controller;

import Components.Btn;
import entity.Player;
import mineclearance.GamePanel;
import mineclearance.GamePanel3;
import mineclearance.ScoreBoard;
import entity.GridStatus;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import Frame.GameDialog;
import Frame.TimeRun;
import mineclearance.ScoreBoard3;
import Frame.Victory3;

public class GameController3{
    private Player p1;//click in player
    private Player p2;
    private Player p3;
    private GamePanel3 gamePanel;
    private Player onTurn;
    private int xCount;
    private int yCount;
    private int mineCount;
    private int clickTimes;//set as final,get from out
    private int click;//cut it down
    private int turns;

    private ScoreBoard3 scoreBoard;
    public boolean time=false;
    public GameController3(Player p1, Player p2,Player p3,int Row,int Column,int Mine,int clickTimes ) {
        this.xCount=Column;
        this.yCount=Row;
        this.mineCount=Mine;
        this.clickTimes=clickTimes;
        this.init(p1, p2, p3);
        this.onTurn = p1;
    }


    public void init(Player p1, Player p2,Player p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p1.setScore(0);
        this.p1.setMistake(0);
        this.p2.setScore(0);
        this.p2.setMistake(0);
        this.p3.setScore(0);
        this.p3.setMistake(0);
        this.p1.setVictory(-1);
        this.p2.setVictory(-1);
        this.p3.setVictory(-1);
        this.onTurn = p1;
        this.turns=0;
        this.p1.setClicks(clickTimes);
        this.p2.setClicks(0);
        this.p3.setClicks(0);

    }
    public void nextTurn() {
        if (onTurn == p1) {
            onTurn = p2;
        } else if (onTurn == p2) {
            onTurn = p3;
        }else if(onTurn==p3){
            onTurn=p1;
        }
        this.turns++;
        time=true;
        this.onTurn.setClicks(clickTimes);
        victory();

    }
    public void goBackTurn() {
        this.onTurn.setClicks(0);
        if (onTurn == p3) {
            onTurn = p2;
        } else if (onTurn == p2) {
            onTurn = p1;
        }else if(onTurn==p1){
            onTurn = p3;
        }
        this.turns--;
        time=true;

        victory();
    }
    public void victory(){
        //想不出怎么判断胜利了
        if (Math.abs(p1.getScore()- p2.getScore())>gamePanel.remainMineCount&&
                Math.abs(p1.getScore()- p3.getScore())>gamePanel.remainMineCount){
            if(p1.getScore()> p2.getScore()&&p1.getScore()> p3.getScore()){
                p1.setVictory(1);
            }
            else if(p1.getScore()< p2.getScore()&&p2.getScore()> p3.getScore()){
                p2.setVictory(1);
            }
            else if(p1.getScore()== p2.getScore()&&p1.getScore()> p3.getScore()){
                p1.setVictory(1);p2.setVictory(1);
            }
            else if(p1.getScore()== p3.getScore()&&p1.getScore()> p2.getScore()){
                p1.setVictory(1);p2.setVictory(1);
            }
            else if(p2.getScore()== p3.getScore()&&p1.getScore()< p2.getScore()){
                p1.setVictory(1);p3.setVictory(1);
            }
            else{
                p3.setVictory(1);
            }
            new Victory3(p1,p2,p3);
        }
        else if(gamePanel.remainMineCount==0){
            if(p1.getScore()> p2.getScore()&&p1.getScore()> p3.getScore()){
                p1.setVictory(1);
            }
            else if(p1.getScore()< p2.getScore()&&p2.getScore()> p3.getScore()){
                p2.setVictory(1);
            }
            else if(p1.getScore()== p2.getScore()&&p1.getScore()> p3.getScore()){
                p1.setVictory(1);p2.setVictory(1);
            }
            else if(p1.getScore()== p3.getScore()&&p1.getScore()> p2.getScore()){
                p1.setVictory(1);p2.setVictory(1);
            }
            else if(p2.getScore()== p3.getScore()&&p1.getScore()< p2.getScore()){
                p1.setVictory(1);p3.setVictory(1);
            }
            else{
                p3.setVictory(1);
            }
            if(p1.getScore()==p2.getScore()&&p2.getScore()==p3.getScore()){
            if (p1.getMistake()< p2.getMistake()&&p1.getMistake()< p3.getMistake()){
                p1.setVictory(1);
            }
            else if(p2.getMistake()< p1.getMistake()&&p2.getMistake()< p3.getMistake()){
                p2.setVictory(1);
            }
            else if(p3.getMistake()< p1.getMistake()&&p3.getMistake()< p2.getMistake()){
                p3.setVictory(1);
            }
            else if(p3.getMistake()== p1.getMistake()&&p3.getMistake()< p2.getMistake()){
                p1.setVictory(1);
                p3.setVictory(1);
            }
            else if(p3.getMistake()< p1.getMistake()&&p3.getMistake()== p2.getMistake()){
                p3.setVictory(1);
                p2.setVictory(1);
            }
            else if(p2.getMistake()< p3.getMistake()&&p1.getMistake()== p2.getMistake()){
                p1.setVictory(1);
                p2.setVictory(1);
            }
            else{
                p1.setVictory(0);
                p2.setVictory(0);
                p3.setVictory(0);

            }} new Victory3(p1,p2,p3);
        }
    }
    public Player getOnTurnPlayer() {
        return this.onTurn;
    }

    public GamePanel3 getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel3(GamePanel3 gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }
    public ScoreBoard3 getScoreBoard() {
        return scoreBoard;
    }
    public void setScoreBoard3(ScoreBoard3 scoreBoard) {
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
    public Player getP3(){
        return p3;
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
        else if(this.getOnTurnPlayer()==p2) return 2;
        return 3;
    }
}
