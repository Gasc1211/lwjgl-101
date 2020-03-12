package engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class Display{
	
    //Window dimensions and title
    private int width, height;
    private String title;
    private long display;
    public int fps;
    public long time;
    
    public Display(int width, int height, String title){
    	
        this.height = height;
        this.width = width;
        this.title = title;
    }

    public void create(){
        if(!GLFW.glfwInit()){
            System.err.println("ERROR: GLFW wasn't initialized.");
            return;
        }
        
        display = GLFW.glfwCreateWindow(width, height, title, 0, 0);
        
        if (display == 0) {
        	System.err.println("ERROR: Window wasn't created.");
            return;
        }
        //Screen resolution adjustment
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(display, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
        GLFW.glfwMakeContextCurrent(display);
        
        //Show Window
        GLFW.glfwShowWindow(display);
        
        //Set Framerate
        GLFW.glfwSwapInterval(1);
        time = System.currentTimeMillis();
    }
    
    public void update() {
    	GLFW.glfwPollEvents();
    	
        //Show FPS
    	fps++;
        if(System.currentTimeMillis() > time + 1000) {
        	GLFW.glfwSetWindowTitle(display, title + " | FPS: " + fps);
        	time = System.currentTimeMillis();
        	fps = 0;
        }
    }
    
    public void swapBuffers() {
    	GLFW.glfwSwapBuffers(display); 
    }
    
    public boolean close() {
    	return GLFW.glfwWindowShouldClose(display);
    }
}