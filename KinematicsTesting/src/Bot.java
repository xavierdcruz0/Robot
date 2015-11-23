/**
 * Created by xavi on 08/10/15.
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.lang.Math;

public class Bot {

    private int x;
    private int y;

    private double vL;
    private double vR;
    private double dvL;
    private double dvR;
    private double dt = 1;

    private Image im;

    private double L = 20; // init a bot wheelbase
    private double R = 3; // init a bot wheel radius
    private double phi = 0; //init an angle of orientation relative to x axis; choose upwards vertical

    private boolean acceleratingL;
    private boolean acceleratingR;

    public Bot(){
        x = 320;
        y = 240;
        ImageIcon ii = new ImageIcon("/home/xavi/Documents/bot1.png");
        im = ii.getImage();
        L = 20;
    }

    /**
     * setters and getters
     */
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public double getvL() {
        return vL;
    }
    public double getvR() {
        return vR;
    }
    public Image getImage() {return im;}
    public double getAngle() {return phi;}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_P) { dvL = 0.1; acceleratingL=true; }
        if (key == KeyEvent.VK_L) { dvL = -0.1; acceleratingL=true; }
        if (key == KeyEvent.VK_Q) { dvR = 0.1; acceleratingR=true;}
        if (key == KeyEvent.VK_A) { dvR = -0.1; acceleratingR=true;}
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_P) { acceleratingL = false; }
        if (key == KeyEvent.VK_L) { acceleratingL = false; }
        if (key == KeyEvent.VK_Q) { acceleratingR = false; }
        if (key == KeyEvent.VK_A) { acceleratingR = false; }
    }

    /**
     * for the bot's currently held values of wheel rotations speeds, will return the bot's change in angle of
     * orientation (in radians)
     * @return double, dPhi
     */
    public double dPhi(){
        return (R/L) * (this.vR-this.vL) * dt;
    }

    /**
     * updateAngle method invokes the getRotationChange() method to get the next period angle of orientation, by
     * adding phi to dPhi.
     */
    public void updateAngle(){
        phi = phi + dPhi();
    }

    public double dX(){
        return (R/2) * (vR+vL) * Math.cos(phi) * dt;
    }

    public double dY(){
        return (R/2) * (vR+vL) * Math.sin(phi) * dt;
    }

    public void updatePosition(){
        double xpos = x + dX();
        double ypos = y + dY();
        x = (int) xpos;
        y = (int) ypos;
    }

    /**
     * accelerate method controls the incrementing and decrementing of the wheel rotation speeds, vL and vR
     */
    public void accelerate(){
        if(acceleratingL){
            vL = Math.min(Math.max(-0.5, vL + dvL), 0.5);
        }else{
            vL = vL-(vL/50);
            //vL = 0;
        }
        if(acceleratingR){
            vR = Math.min(Math.max(-0.5, vR + dvR), 0.5);
        }else{
            vR = vR -(vR/50);
            //vR = 0;
        }
    }
}
