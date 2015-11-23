import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private final int DELAY = 10;
    private Bot bot;

    private double phi = Math.PI/4; //init an angle of orientation relative to x axis; choose upwards vertical

    private double x = 240;
    private double y= 320;

    public Board() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.ORANGE);

        bot = new Bot();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    //@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        x = bot.getX();
        y = bot.getY();
        bot.updateAngle();
        phi = bot.getAngle();
        bot.updatePosition();
        int xpos_next = bot.getX();
        int ypos_next = bot.getY();

        g2d.rotate(phi, x+25, y+25);
        g2d.drawImage(bot.getImage(), (int) xpos_next, (int) ypos_next, this);


        System.out.printf("L: %f, R: %f, Angle: %f, X: %f, Y %f \n", bot.getvL(), bot.getvR(), phi, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bot.accelerate();
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            bot.keyReleased(e);
        }
        @Override
        public void keyPressed(KeyEvent e) {
            bot.keyPressed(e);
        }
    }
}