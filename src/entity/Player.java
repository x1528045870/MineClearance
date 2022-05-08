package entity;
import java.util.Random;

public class Player {
    private static Random ran = new Random();

    private String userName;
    private int score = 0;
    private int mistake = 0;
    private int victory=-1;
    private int clicks;
    private int clickTimes;
    private String team;
    public int teamCode;

    /**
     * 通过特定名字初始化一个玩家对象。
     * @param userName 玩家的名字
     */
    public Player(String userName){
        if(userName==null|userName.equals(""))userName = "User#"+(ran.nextInt(9000)+1000);
        this.userName = userName;
    }
    /**
     * 通过默认名字初始化一个玩家对象。
     */
    public Player(){
        userName = "User#"+(ran.nextInt(9000)+1000);
    }

    public void setTeamCode(int teamCode) {
        this.teamCode = teamCode;
    }

    public int getTeamCode() {
        return teamCode;
    }

    /**
     * 为玩家加一分。
     */

    public void cutClicks(){
        clicks--;
    }
    public void addClicks(){clicks++;}
    public void click(){
        clicks=clickTimes;
    }
    public void addScore(){
        score++;
    }

    /**
     * 为玩家扣一分。
     */
    public void costScore(){
        score--;
    }

    /**
     * 为玩家增加一次失误数。
     */
    public void addMistake() { mistake++; }
    public void cutMistake(){mistake--;}
    public int isVictory() {
        return victory;
    }
    public void setVictory(int victory) {
        this.victory = victory;
    }
    public int getScore(){
        return score;
    }
    public String getUserName(){ return userName; }
    public int getMistake(){ return mistake; }
    public int getClicks() {
        return clicks;
    }
    public void setClicks() {
        this.clicks =this.clickTimes;
    }
    public int getClickTimes() {
        return clickTimes;
    }
    public void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMistake(int mistake) {
        this.mistake = mistake;
    }

    public void setClicks(int i) {
        this.clicks = i;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
