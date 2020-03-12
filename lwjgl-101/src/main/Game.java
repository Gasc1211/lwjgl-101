package main;
import engine.io.Display;

public class Game implements Runnable {
    
    //Thread declaration
    public Thread game;
    
    //Display declaration & config
    public static Display display;
    public static final int width = 1366, height = 768; 
    
    public void start(){
        game = new Thread(this, "game");
        game.start();
    }

    public void init() {
    	display = new Display(width, height, "Asteroids Battle Royale");
    	display.create();
    }

    public void run(){
        init();
        while(!display.close()){
            update();
            render();
        }
    }

    private void update() {
        display.update();
    }

    private void render() {
        display.swapBuffers();
    }

    public static void main(String[] args) {
        new Game().start();
    }
}