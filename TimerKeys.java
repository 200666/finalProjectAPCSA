import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class TimerKeys extends JPanel implements ActionListener, KeyListener
{
    Timer t = new Timer(5, this);
    
    double x= 0, y= 0, velx = 0,vely=0; 
    
    public void second(){
        t.start();
        addKeyListener(this);
        setFocusable(true);
    }
    
    public void paintCompoment(Graphics g){
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.fill(new Ellipse2D.Double(x,y,40,40));
        
       
    }
    
    public void actionPerformed (ActionEvent e){
        
        repaint();
        x+= velx;
        y+=vely;
    }
    
    public void up(){
        vely = -1.5;
        velx=0;
    }
    public void down(){
        vely = 1.5;
        velx=0;
    }
    public void left(){
        vely = -1.5;
        velx=0;
    }
    public void right(){
        vely = 1.5;
        velx=0;
    }
    
    
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        
        
    }
    
    public void keyTyped (KeyEvent e){}
    public void keyReleased (KeyEvent e){}
    
}
