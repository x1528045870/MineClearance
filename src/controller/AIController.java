package controller;

import Components.Btn;
import entity.Player;
import mineclearance.AIGamePanel;
import mineclearance.AIScoreBoard;
import Frame.Victory;
import entity.GridStatus;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import Frame.GameDialog;
import Frame.TimeRun;
public class AIController {
    private AIGamePanel gamePanel;
    private Player onTurn;
    private int xCount;
    private int yCount;
    private int mineCount;
    private AIScoreBoard scoreBoard;
    public AIController(int Row,int Column,int Mine ) {
        this.xCount=Column;
        this.yCount=Row;
        this.mineCount=Mine;
        this.init();

    }

    public void init() {





    }
    public AIGamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(AIGamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }



    public AIScoreBoard getScoreBoard() {
        return scoreBoard;
    }
    public void setScoreBoard(AIScoreBoard scoreBoard) {
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

