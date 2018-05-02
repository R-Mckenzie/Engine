package MEngine.Input;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard{
    private static Keyboard keyboard;
    private final long window;
    private GLFWKeyCallback keyCallback;
    private boolean[] pressedKeys=new boolean[500];
    private boolean[] heldKeys=new boolean[500];
    private boolean[] releasedKeys=new boolean[500];

    private Keyboard(long windowHandle){
        window=windowHandle;
        glfwSetKeyCallback(window, keyCallback=new GLFWKeyCallback(){
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods){
                if(action==GLFW_PRESS){
                    pressedKeys[key]=true;
                    heldKeys[key]=true;
                }else if(action==GLFW_RELEASE){
                    releasedKeys[key]=true;
                    pressedKeys[key]=false;
                    heldKeys[key]=false;
                }else if(action==GLFW_REPEAT){
                    heldKeys[key]=true;
                }
            }
        });
    }

    public static void initialise(long windowHandle){
        keyboard=new Keyboard(windowHandle);
    }

    public static boolean isKeyDown(int keycode){
        if(keyboard.pressedKeys[keycode]){
            keyboard.pressedKeys[keycode]=false;
            return true;
        }
        return false;
    }

    public static boolean isKeyHeld(int keycode){
        if(keyboard.heldKeys[keycode]){
            return true;
        }
        return false;
    }

    public static boolean isKeyUp(int keycode){
       if(keyboard.releasedKeys[keycode]){
           keyboard.releasedKeys[keycode]=false;
           return true;
       }
       return false;
    }
}
