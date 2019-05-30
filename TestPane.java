
import java.awt.*;
import java.awt.Color;
import javax.swing.*;


public class TestPane extends JPanel {
        private boolean inGame = true;
        private int allDots = 900;
         private Image headDot;
         private Image bodyDot;
         private Image foodDot;
         
        private int dots;
        private int dotSize = 10;
        private int applex;
        private int appley;
        
         private int x[] = new int[allDots]; // x coordinates of dots
         private int y[] = new int[allDots]; // y coordinates of dots
    
        public TestPane() {
            
        }

        

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(600, 300);
        }

        protected void paintComponent(Graphics g){ 
       
           
            super.paintComponent(g);
              Graphics2D g2d = (Graphics2D) g.create();
            int size = Math.min(getWidth() - 4, getHeight() - 4) / 10;
            int width = getWidth() - (size);
            int height = getHeight() - (size);

            int y = (getHeight() - (size * 10)) / 2;
            for (int horz = 0; horz < 10; horz++) { //set horizontal size
                int x = (getWidth() - (size * 20)) / 2;
                for (int vert = 0; vert < 20; vert++) { // set vertical size
                    g.drawRect(x, y, size, size);
                    x += size;
                }
                
                y += size;
            }
            
            for (int horz = 0; horz < 10; horz++) { //set horizontal size
                int x = (getWidth() - (size * 20)) / 2;
                for (int vert = 0; vert < 20; vert++) { // set vertical size
                    if (x == 3 && y==4){
                        g.fillRect(0, 0, 5, 4);
                    }
                    x += size;
                }
                
                y += size;
            }

            
            g2d.dispose();
       
           
           
        }
        

    }