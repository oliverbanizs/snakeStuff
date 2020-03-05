import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Canvas extends JPanel implements ActionListener {

    private final static int boardWidth = 800;
    private final static int boardHeight = 600;
    private final static int rectSize = 25;
    private final static int boardSize = (boardWidth * boardHeight) / (rectSize * rectSize);
    private boolean isGameRunning;
    private int score;
    private int speed;
    private Timer timer;
    private Body body;
    private Apple apple;
    Image bg;
    private Timer speedFoodTimer;
    private int specFoodSpeed;

    public Canvas() {
        isGameRunning = true;
        score = 0;
        speed = 200;
        timer = new Timer(speed, this);
        body = new Body();
        apple = new Apple();
        bg = Toolkit.getDefaultToolkit().getImage("/Users/oliverbanizs/IdeaProjects/snakeStuff/src/bg.jpg");
        specFoodSpeed = 120;
        speedFoodTimer = new Timer(specFoodSpeed, this);

        addKeyListener(new Keys());
        setFocusable(true);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        initializeGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg,0,0,null);
        draw(g);
    }

    public void draw(Graphics g) {

        if (isGameRunning == true) {
            g.setColor(Color.red);
            g.fillRect(apple.getAppleX(), apple.getAppleY(), rectSize, rectSize);
            g.setColor(Color.YELLOW);
            g.fillRect(apple.getSpeedFoodX(), apple.getSpeedFoodY(), rectSize, rectSize);
            g.setColor(Color.WHITE);
            g.fillRect(apple.getHeadFoodX(), apple.getHeadFoodY(), rectSize, rectSize);
            g.drawString("Score: " + (score - 3), 10, 10);
            g.drawString("Snake: " + (score), 80, 10);
            
            for (score = 0; score < body.getNewRect(); score++) {
                if (score == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(body.getBodyX(score), body.getBodyY(score), rectSize, rectSize);

                } else {
                    g.fillRect(body.getBodyX(score), body.getBodyY(score),
                            rectSize, rectSize);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            endGame(g);
        }
    }
    void initializeGame() {
        body.setNewRect(3);
        for (int i = 0; i < body.getNewRect(); i++) {
            body.setBodyX(boardWidth / 2);
            body.setBodyY(boardHeight / 2);
        }
        body.setRight(true);
        apple.createApple();
        apple.createSpeedFood();
        apple.createHeadFood();
        timer.start();
    }
    void checkFoodCollisions() {
        if ((proximity(body.getBodyX(0), apple.getAppleX(), 20))
                && (proximity(body.getBodyY(0), apple.getAppleY(), 20))) {
            body.setNewRect(body.getNewRect() + 1);
            apple.createApple();
        }if ((proximity(body.getBodyX(0), apple.getSpeedFoodX(), 20))
                && (proximity(body.getBodyY(0), apple.getSpeedFoodY(), 20))) {
            body.setNewRect(body.getNewRect() + 1);
            timer.stop();
            speedFoodTimer.start();

            apple.createSpeedFood();
        }if ((proximity(body.getBodyX(0), apple.getHeadFoodX(), 20))
                && (proximity(body.getBodyY(0), apple.getHeadFoodY(), 20))) {
            body.setNewRect(body.getNewRect() + 2);
            apple.createHeadFood();
        }
    }
    void checkCollisions() {
        for (int i = body.getNewRect(); i > 0; i--) {
            if ((i > 5) && (body.getBodyX(0) == body.getBodyX(i) && (body.getBodyY(0) == body.getBodyY(i)))) {
                isGameRunning = false;
            }
        }
        if (body.getBodyY(0) >= boardHeight) {
            isGameRunning = false;
        }
        if (body.getBodyY(0) < 0) {
            isGameRunning = false;
        }
        if (body.getBodyX(0) >= boardWidth) {
            isGameRunning = false;
        }
        if (body.getBodyX(0) < 0) {
            isGameRunning = false;
        }
        if (!isGameRunning) {
            timer.stop();
            speedFoodTimer.stop();
        }
    }
    void endGame(Graphics g) {
        String message = "Game over";
        Font font = new Font("Times New Roman", Font.BOLD, 25);
        FontMetrics metrics = getFontMetrics(font);
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString(message, (boardWidth - metrics.stringWidth(message)) / 2,
                boardHeight / 2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameRunning == true) {
            checkFoodCollisions();
            checkCollisions();
            body.move();
           }
        repaint();
    }
    private class Keys extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (score <= 13) {
                if ((key == KeyEvent.VK_LEFT) && (!body.isRight())) {
                    body.setLeft(true);
                    body.setUp(false);
                    body.setDown(false);
                }
                if ((key == KeyEvent.VK_RIGHT) && (!body.isLeft())) {
                    body.setRight(true);
                    body.setUp(false);
                    body.setDown(false);
                }
                if ((key == KeyEvent.VK_UP) && (!body.isDown())) {
                    body.setUp(true);
                    body.setRight(false);
                    body.setLeft(false);
                }
                if ((key == KeyEvent.VK_DOWN) && (!body.isUp())) {
                    body.setDown(true);
                    body.setRight(false);
                    body.setLeft(false);
                }
                if ((key == KeyEvent.VK_ENTER) && (isGameRunning == false)) {
                    isGameRunning = true;
                    body.setDown(false);
                    body.setRight(false);
                    body.setLeft(false);
                    body.setUp(false);
                    initializeGame();
                }
            }else {
                if ((key == KeyEvent.VK_LEFT) && (!body.isRight())) {
                    body.setLeft(false);
                    body.setUp(false);
                    body.setDown(true);
                }
                if ((key == KeyEvent.VK_RIGHT) && (!body.isLeft())) {
                    body.setRight(false);
                    body.setUp(true);
                    body.setDown(false);
                }
                if ((key == KeyEvent.VK_UP) && (!body.isDown())) {
                    body.setUp(false);
                    body.setRight(false);
                    body.setLeft(true);
                }
                if ((key == KeyEvent.VK_DOWN) && (!body.isUp())) {
                    body.setDown(false);
                    body.setRight(true);
                    body.setLeft(false);
                }
                if ((key == KeyEvent.VK_ENTER) && (isGameRunning == false)) {
                    isGameRunning = true;
                    body.setDown(false);
                    body.setRight(false);
                    body.setLeft(false);
                    body.setUp(false);
                    initializeGame();
                }
            }
        }
    }
    private boolean proximity(int a, int b, int closeness) {
        return Math.abs((long) a - b) <= closeness;
    }
    public static int getAllDots() {
        return boardSize;
    }
    public static int getDotSize() {
        return rectSize;
    }
}