package MEngine.Rendering.GLBufferObjects;

import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class VbaLayout{
    private final List<VbaElement> elements;
    private int stride;

    public VbaLayout(){
        elements=new ArrayList<>();
        this.stride=0;
    }

    public void pushFloat(int count){
        elements.add(new VbaElement(count, GL11.GL_FLOAT, false));
        stride+=(Float.BYTES*count);
    }

    public int getStride(){
        return stride;
    }

    public List<VbaElement> getElements(){
        return elements;
    }
}
