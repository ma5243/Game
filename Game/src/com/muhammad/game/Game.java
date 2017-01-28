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

    public int tickCount;

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
        long lastTime = System.nanoTime();  // Current time in nanaoseconds
        double nsPerTick = 1000000000.0/60; // Nanoseconds in one tick

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            boolean shouldRender = true;

            while(delta >= 1){
                ticks++;
                tick();
                delta-=1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(shouldRender) {
                frames++;
                render();
            }

            if(System.currentTimeMillis() - lastTimer >= 1000){
                lastTimer+=1000;
                System.out.println("ticks: " + ticks + "," + "frames: " + frames);
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void tick(){
        tickCount++;
    }

    public void render(){

    }

    public static void main(String[] arg) {
        new Game().start();
    }

}
