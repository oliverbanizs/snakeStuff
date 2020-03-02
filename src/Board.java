import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private final static int boardwidth = 800;
    private final static int boardheigth = 600;
    private final static int pixelSize = 25;
    private final static int totalPixels = (boardwidth * boardheigth) / (pixelSize * pixelSize);
    private boolean inGame;
    private int intScore;
    private int speed;
    private Timer timer;
    private Snake snake;
    private Food food;
    Image bg;

    public Board() {
        inGame = true;
        intScore = 0;
        speed = 120;
        timer = new Timer(speed, this);
        snake = new Snake();
        food = new Food();
        bg = Toolkit.getDefaultToolkit().getImage("/Users/oliverbanizs/IdeaProjects/snakeStuff/src/bg.jpg");

        addKeyListener(new Keys());
        setFocusable(true);
        setPreferredSize(new Dimension(boardwidth, boardheigth));
        initializeGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg,0,0,null);
        draw(g);
    }

    void draw(Graphics g) {

        if (inGame == true) {
            g.setColor(Color.red);
            g.fillRect(food.getFoodX(), food.getFoodY(), pixelSize, pixelSize);
            g.drawString("Score: " + (intScore - 3), 10, 10);

            for (intScore = 0; intScore < snake.getJoints(); intScore++) {
                if (intScore == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(snake.getSnakeX(intScore), snake.getSnakeY(intScore),
                            pixelSize, pixelSize);
                } else {
                    g.fillRect(snake.getSnakeX(intScore), snake.getSnakeY(intScore),
                            pixelSize, pixelSize);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            endGame(g);
        }
    }
    void initializeGame() {
        snake.setJoints(3);
        for (int i = 0; i < snake.getJoints(); i++) {
            snake.setSnakeX(boardwidth / 2);
            snake.setSnakeY(boardheigth / 2);
        }
        snake.setMovingRight(true);
        food.createFood();
        timer.start();
    }
    void checkFoodCollisions() {
        if ((proximity(snake.getSnakeX(0), food.getFoodX(), 20))
                && (proximity(snake.getSnakeY(0), food.getFoodY(), 20))) {
            snake.setJoints(snake.getJoints() + 1);
            food.createFood();
        }
    }
    void checkCollisions() {
        for (int i = snake.getJoints(); i > 0; i--) {
            if ((i > 5)
                    && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake
                    .getSnakeY(0) == snake.getSnakeY(i)))) {
                inGame = false; // then the game ends
            }
        }
        if (snake.getSnakeY(0) >= boardheigth) {
            inGame = false;
        }
        if (snake.getSnakeY(0) < 0) {
            inGame = false;
        }
        if (snake.getSnakeX(0) >= boardwidth) {
            inGame = false;
        }
        if (snake.getSnakeX(0) < 0) {
            inGame = false;
        }
        if (!inGame) {
            timer.stop();
        }
    }
    void endGame(Graphics g) {
        String message = "Game over";
        Font font = new Font("Times New Roman", Font.BOLD, 25);
        FontMetrics metrics = getFontMetrics(font);
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString(message, (boardwidth - metrics.stringWidth(message)) / 2,
                boardheigth / 2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame == true) {
            checkFoodCollisions();
            checkCollisions();
            snake.move();
           }
        repaint();
    }
    private class Keys extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
                snake.setMovingLeft(true);
                snake.setMovingUp(false);
                snake.setMovingDown(false);
            }
            if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
                snake.setMovingRight(true);
                snake.setMovingUp(false);
                snake.setMovingDown(false);
            }
            if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
                snake.setMovingUp(true);
                snake.setMovingRight(false);
                snake.setMovingLeft(false);
            }
            if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
                snake.setMovingDown(true);
                snake.setMovingRight(false);
                snake.setMovingLeft(false);
            }
            if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {
                inGame = true;
                snake.setMovingDown(false);
                snake.setMovingRight(false);
                snake.setMovingLeft(false);
                snake.setMovingUp(false);
                initializeGame();
            }
        }
    }
    private boolean proximity(int a, int b, int closeness) {
        return Math.abs((long) a - b) <= closeness;
    }
    public static int getAllDots() {
        return totalPixels;
    }
    public static int getDotSize() {
        return pixelSize;
    }
}