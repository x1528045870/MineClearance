package controller;

import Components.Btn;
import entity.Player;
import mineclearance.SingleGamePanel;
import mineclearance.SingleScoreBoard;
import Frame.Victory;
import entity.GridStatus;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import Frame.GameDialog;
import Frame.TimeRun;
public class SingleController {
    private SingleGamePanel gamePanel;
    private Player onTurn;
    private int xCount;
    private int yCount;
    private int mineCount;
    private SingleScoreBoard scoreBoard;
    public SingleController(int Row,int Column,int Mine ) {
        this.xCount=Column;
        this.yCount=Row;
        this.mineCount=Mine;
        this.init();

    }

    public void init() {





    }
    public SingleGamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(SingleGamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }



    public SingleScoreBoard getScoreBoard() {
        return scoreBoard;
    }
    public void setScoreBoard(SingleScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
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



}

