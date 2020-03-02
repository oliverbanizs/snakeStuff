public class Food {
    private Snake snake;
    private int foodX;
    private int foodY;
    private final int randomPosition;
    private int location;

    public Food() {
        snake = new Snake();
        foodX = 0;
        foodY = 0;
        randomPosition = 20;
        location = 0;
    }
    public void createFood() {
        location = (int) (Math.random() * randomPosition);
        foodX = ((location * Board.getDotSize()));
        location = (int) (Math.random() * randomPosition);
        foodY = ((location * Board.getDotSize()));
        if ((foodX == snake.getSnakeX(0)) && (foodY == snake.getSnakeY(0))) {
            createFood();
        }
    }
    public int getFoodX() {
        return foodX;
    }
    public int getFoodY() {
        return foodY;
    }
}
