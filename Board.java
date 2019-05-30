import java.awt.*;
//import java.awt.Color;
import javax.swing.*;

//import javax.swing.JPanel;
//import javax.swing.Timer;
//import java.awt.Graphics;
import java.awt.event.*;
//import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener{
    private int randPos = 29; // caluclate rand position
    private int allDots = 900; // max number of points
    private int bHeight = 300;
    private int bWidth = 600;
    
    private final int DELAY = 140; //used for timer
    
    private int x[] = new int[allDots]; // x coordinates of dots
    private int y[] = new int[allDots]; // y coordinates of dots
    
    private int dots;
    private int dotSize = 10;
    private int applex;
    private int appley;
    
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    
    private Timer timer;
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("snake yum yum");
        frame.add(new TestPane());
        JPanel panel = new JPanel();
        JLabel start = new JLabel("start game");
        JButton startButton = new JButton ("start");
                //startButton.setAction();
        panel.add(startButton);
        frame.add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
        TimerKeys t = new TimerKeys();
        frame.add(t);       
        frame.setVisible(true);
        frame.setSize(800,600);
        new Board();
    }


    public Board() {
        //addKeyListener(new TAdapter());
        //setFocusable(true);
        //EventQueue.invokeLater(new Runnable() {
            //@Override
            //public void run() {
                //try {
                   // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               // } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                //    ex.printStackTrace();
                //}

                
                Snake player = new Snake(3,5,10);
                int dots = 3;
                for (int z = 0; z<dots;z++){
                    x[z] = 50-z*10;
                    y[z] = 50;
                }
                
               timer = new Timer(10, this);
               timer.start();
                
                
            }
        
        //});
    

        
 
    private void checkApple(){
        if ((x[0]==applex) && (y[0]==appley)){
        
            dots++;
        }
    
    }
    
    private void move(){
        for (int j = dots; j>0; j--){
            x[j]=x[j-1];
            y[j]=y[j-1];
            
            if (leftDirection){
                
                x[0] -= dotSize;
            }
            if(rightDirection){
                
                x[0] -= dotSize;
            }
            if (upDirection){
                
                y[0] -= dotSize;
            }
            
            if (downDirection){
                
                y[0] -= dotSize;
            }   
        }
    }
    
    private void checkCollision(){
        for(int j=dots; j >0;j--){
            if((j>4)&&(x[0]==x[j])&&(y[0]==y[j])){
              inGame = false;  
            }
            
            //check if x or y coordinates are out of bounds 
            //(0-board dimensions)
            if (y[0]>= bHeight){
               inGame = false; 
            }
            if (y[0]< 0){
               inGame = false; 
            }
            if (x[0]>= bWidth){
               inGame = false; 
            }
            if (x[0]< 0){
               inGame = false; 
            }
            
            
            //stops game/actions 
            if (!inGame){
                timer.stop();
            }
        }
        
    }
    
    private void locateFood(){
        int r = (int) (Math.random()* randPos);
        applex = r* dotSize;
        //sets new x coordinate for new appeared food target
        
        r = (int) (Math.random()*randPos);
        appley = r*dotSize;
        //sets new y coordinate for new appeared food target
        
    }
    
    @Override
    public void actionPerformed(ActionEvent a){
    
        if(inGame){
            
            checkApple();
            checkCollision();
            move();
            //call previously defined methods
        }
        
        repaint();
        //invoked to set up the new drawing
    }
    

    
}