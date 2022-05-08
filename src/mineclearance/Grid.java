package mineclearance;

import Frame.GameDialog;
import entity.GridStatus;

public class Grid  {
    public static int gridSize = 30;
    private GridStatus status = GridStatus.Covered;
    private boolean hasMine=false;
    private int content = 0;
    private int x;

    public static void setGridSize(int gridSize) {
        Grid.gridSize = gridSize;
    }


    public GridStatus getStatus() {
        return status;
    }

    public void setStatus(GridStatus status) {
        this.status = status;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    private int y;
     public Grid(int x, int y) {
         this.x=x;
         this.y=y;
    }
}
