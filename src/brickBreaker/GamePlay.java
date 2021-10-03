package brickBreaker;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener,ActionListener{

    // KeyListener for arrow
    // ActionListener for Ball

    private boolean play = false;
    private int score = 0;

    private int totalBricks = 21;

    private Timer time;
    private int delay = 10;

    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;

    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;

    public GamePlay(){
        map = new MapGenerator(3,7);


     addKeyListener(this);
     setFocusable(true);
     setFocusTraversalKeysEnabled(false);
     time = new Timer(delay,this);
     time.start();

    }

    public void paint(Graphics g){
        //background

        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);

        // drawing map
        map.draw((Graphics2D)g);

        //borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,687,3);
        g.fillRect(691,0,3,592);

        // the paddle

        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        //the ball

        g.setColor(Color.yellow);
        g.fillOval(ballposX,ballposY,20,20);
        g.dispose();
    }




    @Override
    public void actionPerformed(ActionEvent e) {
    time.start();
    if(play){

        if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
        {
            ballYdir = -ballYdir;

        }
        ballposX+=ballXdir;
        ballposY+=ballYdir;
        if(ballposX < 0){
           ballXdir = -ballXdir;
        }
        if(ballposY < 0){
            ballYdir = -ballYdir;
        }
        if(ballposX > 670){
            ballXdir = -ballXdir;
        }
    }

    repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    // So we just need KeyPressed Event
    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }else{
                moveRight();
            }

        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }else{
                moveLeft();
            }
        }



    }

    public void moveRight(){
        play = true;
        playerX+=20;
    }
    public void moveLeft(){
        play = true;
        playerX-=20;
    }

}