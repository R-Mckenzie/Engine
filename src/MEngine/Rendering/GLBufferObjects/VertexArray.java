package MEngine.Rendering.GLBufferObjects;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

public class VertexArray{
    private final int id;

    private List<VertexBuffer> vertexBuffers;

    public VertexArray(){
        vertexBuffers=new ArrayList<>();
        id=GL30.glGenVertexArrays();
    }

    public void addBuffer(VertexBuffer buffer, VbaLayout layout){
        vertexBuffers.add(buffer);
        bind();
        buffer.bind();
        List<VbaElement> elements=layout.getElements();
        int offset=0;
        for(int i=0;i<elements.size();i++){
            VbaElement e=elements.get(i);
            GL20.glEnableVertexAttribArray(i);
            GL20.glVertexAttribPointer(i, e.count, e.type, e.normalised, layout.getStride(), offset);
            offset+=e.count*e.getSizeOfType();
        }
    }

    public void bind(){
        GL30.glBindVertexArray(id);
    }

    public void dispose(){
        for(VertexBuffer b:vertexBuffers){
            b.dispose();
        }
        GL30.glDeleteVertexArrays(id);
    }
}
