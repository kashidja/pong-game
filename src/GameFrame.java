import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//minimize,maximize and x button
public class GameFrame extends JFrame {

    GamePanel panel;

    GameFrame(){
        panel=new GamePanel();
        //adds game panel to this frame
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when u hit x button it will close app
        this.pack(); //its going to fit around game panel , u don't need to set size of jframe,it will adjust
        this.setVisible(true); //to see it
        this.setLocationRelativeTo(null); //appears in the middle of screen
    }

}
