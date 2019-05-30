import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RunBoard extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS]; //array of all the x coordinates of all the dots
    private final int y[] = new int[ALL_DOTS]; //array of all the y coordinates of all the dots

    private int dots;
    private int food_x; //x coordinate
    private int food_y; //y coordinate
    private int score;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer; //part of event class
    private Image ball; // image class in swing
    private Image food;
    private Image head;

    public RunBoard() {
        initBoard();
       
    }
    
    private void initBoard(){
         TAdapter t = new TAdapter();
        addKeyListener(t);
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }
    private void loadImages() {

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

            g.drawImage(food, food_x, food_y, this);

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
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);

        g.drawString("Start Again? press m", 50, 50);
        g.drawString("Your score was:" + score, 70, 70);

         } 
    

    private void checkFood() {

        if ((x[0] == food_x) && (y[0] == food_y)) {
            dots++;
            locateFood();
            score++;
        }
    }
    
    private void locateFood() {

        int r = (int) (Math.random() * RAND_POS);
        food_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        food_y = ((r * DOT_SIZE));
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkFood();
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
            
            if ((key == KeyEvent.VK_ENTER)) {
                inGame = true;
                
                RunBoard r = new RunBoard();
                r.initBoard();
            }
        }
    }
    
}

