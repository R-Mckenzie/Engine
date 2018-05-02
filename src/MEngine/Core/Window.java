package MEngine.Core;

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
    private final long id;
    private int width, height;

    public Window(int width, int height, String title){
        this.width=width;
        this.height=height;
        id=create(width, height, title, false, false);
        MEngine.Input.Keyboard.initialise(id);
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
        if(width>0&&height>0)
            glfwSetWindowSize(id, width, height);
        else
            System.err.println("[ERROR] - Can't have a negative window size");
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
    private long create(int width, int height, String title, boolean isResizable, boolean isFullScreen){
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit())throw new IllegalStateException("Unable to initialize GLFW");

        setSettings(isResizable);
        long winId = glfwCreateWindow(width, height, title, isFullScreen?glfwGetPrimaryMonitor():NULL, NULL);
        if(winId==NULL)throw new RuntimeException("Failed to create the GLFW window");

        centerWindow(winId);
        setupResizeCallback(winId);

        glfwMakeContextCurrent(winId);
        glfwSwapInterval(-1);
        return winId;
    }

    private void setupResizeCallback(long winID){
        final Window win=this;
        glfwSetWindowSizeCallback(winID, new GLFWWindowSizeCallback(){
            public void invoke(long window, int width, int height){
                win.width=width;
                win.height=height;
            }
        });
    }
    private void centerWindow(long winId){
        try(MemoryStack stack = stackPush()){
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            glfwGetWindowSize(winId, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(winId, (vidmode.width()-pWidth.get(0))/2, (vidmode.height()-pHeight.get(0))/2);
        }
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
