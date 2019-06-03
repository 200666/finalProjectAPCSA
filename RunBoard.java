import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class RunBoard extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 100;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;
    private int score;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image food;
    private Image head;

    public RunBoard() {
        
        initBoard();
    }
    
    private void initBoard() {

        addKeyListener(new TAdapter());
        Color fontc = new Color(0-100-100); 
        setBackground(fontc);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
        //defines images for body head and food 
        //also changes size of images
        
        ImageIcon ii = new ImageIcon(getClass().getResource("dotHead.png"));
        head = ii.getImage().getScaledInstance(12, 12,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon nope3 = new ImageIcon(head);
        head= nope3.getImage();
        
        ImageIcon aa = new ImageIcon(getClass().getResource("dotBody.png"));
        ball = aa.getImage().getScaledInstance(10,10,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon nope2 = new ImageIcon(ball);
        ball = nope2.getImage();
        
        ImageIcon cc = new ImageIcon(getClass().getResource("snakeFood.png"));
        food = cc.getImage().getScaledInstance(10, 10,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon nope = new ImageIcon(food);
        food = nope.getImage();
    }
    

    private void initGame() {
        dots = 3;
        
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        
        locateFood();

        timer = new Timer(DELAY, this);
        timer.start();
        //timer sets the action of the game in place, does not actually time but instead 
        //lets the object continue to move 
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //part of swing class
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(food, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            
            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        // displayed when the snake goes out of bounds
        // 
        String msg = "Game Over";
        Font small = new Font("Superclarendon", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.GREEN);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        
        g.drawString("Your score was:" + score, 70, 70);

         } 

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            score++;
            dots++;
            locateFood();
        }
    }

    private void move() {
        //changes the values of y and/or y coordinate in arrays
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
        //method to check whether or not the snake has moved fully off the screen
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if (!inGame) {
            timer.stop();
        }
    }

     private void locateFood() { 
        //sets new coordinates for food
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            
        }
    }
    
}