import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
public class SimpleBoard
{
    public static void main(String[] args) {
        
        JFrame frame4 = new JFrame("new");


        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TimerKeys t = new TimerKeys();
        frame4.add(t);       
        frame4.setVisible(true);
        frame4.setSize(800,600);
        new Board();
    }
}
