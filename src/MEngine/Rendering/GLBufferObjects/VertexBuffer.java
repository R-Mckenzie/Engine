package MEngine.Rendering.GLBufferObjects;

import MEngine.Utils;
import org.lwjgl.opengl.GL15;

public class VertexBuffer{
    private final int id;

    public VertexBuffer(float[] data){
        id=GL15.glGenBuffers();
        bind();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, Utils.arrayToFloatBuffer(data), GL15.GL_STATIC_DRAW);
    }

    public void bind(){
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
    }

    public void dispose(){
        GL15.glDeleteBuffers(id);
    }
}
