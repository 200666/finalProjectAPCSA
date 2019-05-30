import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class change extends JPanel implements ActionListener, KeyListener
{
    
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    
    Timer t = new Timer(5, this);
    
    double x= 0, y= 0, velx = 0,vely=0; 
    
    public change(){
        t.start();
        addKeyListener(this);
        setFocusable(true);
    }
    
    public void paintCompoment(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.fill(new Ellipse2.Double(x,y,40,40));
        
       
    }
    @Override
    public void keyPressed (KeyEvent e){
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT && !rightDirection){
            
            leftDirection = true;
            upDirection = false;
            downDirection = false;
        }
        
        if (key == KeyEvent.VK_RIGHT && !leftDirection){
            
            rightDirection = true;
            upDirection = false;
            downDirection = false;
        }
        
        if (key == KeyEvent.VK_UP && !downDirection){
            
            upDirection = true;
            rightDirection = false;
            leftDirection = false;
        }
        
        if (key == KeyEvent.VK_DOWN && !upDirection){
            
            downDirection = true;
            rightDirection = false;
            leftDirection = false;
        }
        
        
    }
    
    public void keyTyped (KeyEvent e){}
    public void keyReleased (KeyEvent e){}
    
    
}
