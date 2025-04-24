import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle {

    int id;  //for each player 1 or 2
    int yVelocity; //how fast paddle is moving up and down
    int speed=10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                //if w is pressed
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed); //moves up on y axis
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed); //moves up on y axis
                    move();
                }
                break;
            case 2:
                //if w is pressed
                if (e.getKeyCode() == KeyEvent.VK_UP) { //arrow
                    setYDirection(-speed); //moves up on y axis
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed); //moves up on y axis
                    move();
                }
                break;
        }

    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                //if w is pressed
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0); //moves up on y axis
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0); //moves up on y axis
                    move();
                }
                break;
            case 2:
                //if w is pressed
                if (e.getKeyCode() == KeyEvent.VK_UP) { //arrow
                    setYDirection(0); //moves up on y axis
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0); //moves up on y axis
                    move();
                }
                break;
        }
    }


        public void setYDirection (int yDirection){
            yVelocity = yDirection;
        }

        public void move() {
            y=y+yVelocity;
        }

        public void draw (Graphics g){
            if (id == 1) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }
            g.fillRect(x, y, width, height);
        }
}