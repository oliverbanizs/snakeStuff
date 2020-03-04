public class Body {
    private final int[] positionX;
    private final int[] positionY;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    public int newRect;

    public Body() {
        positionX = new int[Canvas.getAllDots()];
        positionY = new int[Canvas.getAllDots()];
        left = false;
        right = false;
        up = false;
        down = false;
        newRect = 0;
    }
    public int getBodyX(int index) {
        return positionX[index];
    }
    public int getBodyY(int index) {
        return positionY[index];
    }
    public void setBodyX(int i) {
        positionX[0] = i;
    }
    public void setBodyY(int i) {
        positionY[0] = i;
    }
    public boolean isLeft() {
        return left;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public boolean isRight() {
        return right;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public boolean isUp() {
        return up;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public boolean isDown() {
        return down;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public int getNewRect() {
        return newRect;
    }
    public void setNewRect(int j) {
        newRect = j;
    }
    public void move() {
        for (int i = newRect; i > 0; i--) {
            positionX[i] = positionX[(i - 1)];
            positionY[i] = positionY[(i - 1)];
        }
        if (left) {
            positionX[0] -= Canvas.getDotSize();
        }
        if (right) {
            positionX[0] += Canvas.getDotSize();
        }
        if (down) {
            positionY[0] += Canvas.getDotSize();
        }
        if (up) {
            positionY[0] -= Canvas.getDotSize();
        }
    }
}