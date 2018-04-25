package MEngine.Rendering;

import MEngine.Rendering.GLBufferObjects.IndexBuffer;
import MEngine.Rendering.GLBufferObjects.VertexArray;
import MEngine.Rendering.Primitives.IPrimitiveMesh;

public class Mesh{
    private VertexArray vertexArray;
    private IndexBuffer indexBuffer;

    public Mesh(VertexArray va, IndexBuffer ib){
        this.vertexArray=va;
        this.indexBuffer=ib;
    }
    public Mesh(IPrimitiveMesh primitive){
        this.vertexArray=primitive.getMesh().vertexArray;
        this.indexBuffer=primitive.getMesh().indexBuffer;
    }

    public VertexArray getVAO(){
        return vertexArray;
    }
    public IndexBuffer getIBO(){
        return indexBuffer;
    }

    public void bind(){
        vertexArray.bind();
        indexBuffer.bind();
    }

    public void dispose(){
        indexBuffer.dispose();
        vertexArray.dispose();
    }
}
