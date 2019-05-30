import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartOver extends JFrame implements ActionListener {

    public StartOver() {
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(100, 100);
        //setLocation(100, 100);

        
    }

    public static void main(String[] args) {
        new StartOver();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("button1")) {
            myMethod();
        }
    }

    public void myMethod() {
        JOptionPane.showMessageDialog(this, "Hello, World!!!!!");
        RunSnake go = new RunSnake();
       
    }
}