package MEngine.Rendering.GLBufferObjects;

import org.lwjgl.opengl.GL15;

import static MEngine.Utils.arrayToIntBuffer;

public class IndexBuffer{
    public final int count;
    private final int id;

    public IndexBuffer(int[] data){
        count=data.length;
        id=GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, arrayToIntBuffer(data), GL15.GL_STATIC_DRAW);
    }

    public void bind(){
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, id);
    }

    public void dispose(){
        GL15.glDeleteBuffers(id);
    }
}
