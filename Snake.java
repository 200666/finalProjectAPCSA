import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {
    private int size;
    private int posX;
    private int posY;
    public Snake(int theSize, int thePosX, int thePosY) {
        size = theSize;
        posX = thePosX;
        posY = thePosY;
        
        
    }
    
    public void resetPos(int xPos, int yPos){
        posX = xPos;
        posY = yPos;
    }
    
    public void resetSize(int newSize){
        size = newSize;
    }
    
    public int getX(){
        return posX;
    }
    
    public int getY(){
        return posY;
    }
    
    public int getTheSize(){
        return size;
    }
 
    public static void main(String[] args) {
        
        
        
    }
}