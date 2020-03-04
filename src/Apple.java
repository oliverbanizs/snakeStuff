public class Apple {
    private Body body;
    private int appleX;
    private int appleY;
    private final int randomPos;
    private int location;
    private int speedFoodX;
    private int speedFoodY;
    private int headFoodX;
    private int headFoodY;

    public Apple() {
        body = new Body();
        appleX = 0;
        appleY = 0;
        randomPos = 20;
        location = 0;
        speedFoodX = 0;
        speedFoodY = 0;
        headFoodX = 0;
        headFoodY = 0;
    }
    public void createApple() {
        location = (int) (Math.random() * randomPos);
        appleX = ((location * Canvas.getDotSize()));
        location = (int) (Math.random() * randomPos);
        appleY = ((location * Canvas.getDotSize()));
        if ((appleX == body.getBodyX(0)) && (appleY == body.getBodyY(0))) {
            createApple();
        }
    }
    public void createSpeedFood() {
        location = (int) (Math.random() * randomPos);
        speedFoodX = ((location * Canvas.getDotSize()));
        location = (int) (Math.random() * randomPos);
        speedFoodY = ((location * Canvas.getDotSize()));
        if ((speedFoodX == body.getBodyX(0)) && (speedFoodY == body.getBodyY(0))) {
            createSpeedFood();
        }
    }
    public void createHeadFood() {
        location = (int) (Math.random() * randomPos);
        headFoodX = ((location * Canvas.getDotSize()));
        location = (int) (Math.random() * randomPos);
        headFoodY = ((location * Canvas.getDotSize()));
        if ((headFoodX == body.getBodyX(0)) && (headFoodY == body.getBodyY(0))) {
            createHeadFood();
        }
    }
    public int getAppleX() {
        return appleX;
    }
    public int getAppleY() {
        return appleY;
    }
    public int getSpeedFoodX() {
        return speedFoodX;
    }
    public int getSpeedFoodY() {
        return speedFoodY;
    }
    public int getHeadFoodX() {
        return headFoodX;
    }
    public int getHeadFoodY() {
        return headFoodY;
    }
}
