import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.lang.Math;

public class EntryPoint extends JFrame {

    public EntryPoint() {
        add(new Board());

        setSize(640, 480);
        setResizable(false);

        setTitle("Differential Drive Bot");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            //@Override
            public void run() {
                EntryPoint ep = new EntryPoint();
            }
        });
    }
}