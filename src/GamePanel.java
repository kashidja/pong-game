import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//runs on threads with runnable
public class GamePanel extends JPanel implements Runnable{


    static final int GAME_WIDTH= 1000;
    static final int GAME_HEIGHT= (int)(GAME_WIDTH*(0.555));
    static final Dimension SCREEN_SIZE=new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER=20;
    static final int PADDLE_WIDTH=25;
    static final int PADDLE_HEIGHT=100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    //player1
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    GamePanel(){
        newPaddles();
        newBall();
        score=new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);   //if u press any keys its going to have focus, it will read keypresses/strokes
        this.addKeyListener(new AL()); //so this will respond to key strokes
        this.setPreferredSize(SCREEN_SIZE);
        gameThread=new Thread(this);
        gameThread.start();
    }

    //creates new ball on the screen
    public void newBall(){
        random=new Random();
        ball=new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }

    //when resetting level or game call both methods
    public void newPaddles(){
        //x-very left,y-middle
        paddle1=new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2=new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }
 //painting stuff on screen
    public void paint(Graphics g){
        image=createImage(getWidth(),getHeight()); //dimension of this panel
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }


    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
  //move stuff after each iteration
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision(){
        //ball bounces off top and bottom window edges
        if(ball.y<=0){
            ball.setYDirection(-ball.yVelocity);//opposite direction with minus
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //check if ball collides with paddle
        if(ball.intersects(paddle1)){
            ball.xVelocity=Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if(ball.yVelocity>0){
                ball.yVelocity++;
            }else ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)){
            ball.xVelocity=Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if(ball.yVelocity>0){
                ball.yVelocity++;
            }else ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //this stops paddles at window edges
        if(paddle1.y <=0){
            paddle1.y=0;
        }
        if(paddle1.y>=(GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
        }
        if(paddle2.y <=0){
            paddle2.y=0;
        }
        if(paddle2.y>=(GAME_HEIGHT-PADDLE_HEIGHT)){
            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;
        }

        //give a player 1 point & creates new paddles and ball
        if(ball.x<=0){ //touched left boundary
            score.player2++;
            newPaddles();
            newBall();
        }
        if(ball.x>=GAME_WIDTH-BALL_DIAMETER){ //touched left boundary
            score.player1++;
            newPaddles();
            newBall();
        }
    }

    public void run(){
        //basic game loop
        long lastTime= System.nanoTime();
        double amountOfTicks=60.0;
        double ns= 1000000000/amountOfTicks;
        double delta=0;
        while(true){
            long now=System.nanoTime();
            delta+= (now-lastTime)/ns;
            lastTime=now;
            if(delta>=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    //inner class
    //action listener
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
