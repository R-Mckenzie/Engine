package MEngine.Rendering.GLBufferObjects;

import org.lwjgl.opengl.GL11;

public class VbaElement{
    final int count;
    final int type;
    final boolean normalised;

    public VbaElement(int count, int type, boolean normalised){
        this.count=count;
        this.type=type;
        this.normalised=normalised;
    }

    public int getSizeOfType(){
        switch(type){
            case GL11.GL_FLOAT:
                return Float.BYTES;
            case GL11.GL_INT:
                return Integer.BYTES;
            default:
                System.err.println("[error] Data type not specified in BufferElement");
                System.exit(-1);
        }
        System.out.println("Type not defined in BufferElement.getSizeOfType()");
        return 0;
    }
}
