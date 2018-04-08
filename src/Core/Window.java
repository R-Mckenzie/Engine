package Core;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window implements IWindow{
    private long id;
    private int width, height;

    public Window(int width, int height, String title){
        this.width=width;
        this.height=height;
        create(width, height, title, false, false);
    }

    public void refresh(){
        glfwSwapBuffers(id);
        glfwPollEvents();
    }

    public void show(){
        glfwShowWindow(id);
    }
    public void hide(){
        glfwHideWindow(id);
    }

    public void setSize(int width, int height){
        glfwSetWindowSize(id, width, height);
    }
    public int width(){
        return width;
    }
    public int height(){
        return height;
    }

    public void close(){
        glfwSetWindowShouldClose(id, true);
    }
    public boolean isCloseRequested(){
        return glfwWindowShouldClose(id);
    }

    public void dispose(){
        hide();
        glfwFreeCallbacks(id);
        glfwDestroyWindow(id);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    //Creation methods
    private void create(int width, int height, String title, boolean isResizable, boolean isFullScreen){
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit())throw new IllegalStateException("Unable to initialize GLFW");

        setSettings(isResizable);
        id = glfwCreateWindow(width, height, title, isFullScreen?glfwGetPrimaryMonitor():NULL, NULL);
        if(id==NULL)throw new RuntimeException("Failed to create the GLFW window");

        setupKeyCallback();
        centerWindow();
        setupResizeCallback();

        glfwMakeContextCurrent(id);
        glfwSwapInterval(-1);
    }

    private void setupResizeCallback(){
        final Window win=this;
        glfwSetWindowSizeCallback(id, new GLFWWindowSizeCallback(){
            public void invoke(long window, int width, int height){
                win.width=width;
                win.height=height;
            }
        });
    }
    private void centerWindow(){
        try(MemoryStack stack = stackPush()){
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            glfwGetWindowSize(id, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(id, (vidmode.width()-pWidth.get(0))/2, (vidmode.height()-pHeight.get(0))/2);
        }
    }
    private void setupKeyCallback(){
        glfwSetKeyCallback(id, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                close();
        });
    }
    private void setSettings(boolean isResizable){
        glfwWindowHint(GLFW_RESIZABLE, isResizable?GLFW_TRUE:GLFW_FALSE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    }
}
