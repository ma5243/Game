package com.muhammad.game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by abdullah on 1/26/2017.
 */
public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 160; //Width
    public static final int HEIGHT = WIDTH / 12*9; // Makes the ratio of the game 9:12 (HEIGHT:WIDTH)
    public static final int SCALE = 3; //Scale used to increase the size of the game
    public static final String NAME = "Game"; //Name of the game

    public boolean running = false;

    private JFrame jframe;

    public Game(){
        setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE)); //Minimum size
        setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE)); //Maximum size
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE)); //Preferred size

        jframe = new JFrame(); //Creates a new JFrame
        jframe.setTitle(NAME); //Sets the title of the Jframe to NAME variable
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Tells the program to exit when game is closed

        jframe.setLayout(new BorderLayout()); //Form of layout
        jframe.add(this,BorderLayout.CENTER); //Adds the canvas to jframe

        jframe.pack(); //Sets the size of everything
        jframe.setResizable(false); //Doesn't allow the user to change the size of the game

        jframe.setLocationRelativeTo(null); //Doesn't set the location relative to anything

        jframe.setVisible(true); //Makes the JFrame visible
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }


    @Override
    public void run() {
        while(running){

        }
    }

    public static void main(String[] arg) {
        new Game().start();
    }

}
