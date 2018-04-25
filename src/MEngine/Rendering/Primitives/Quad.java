package MEngine.Rendering.Primitives;

import MEngine.Rendering.GLBufferObjects.IndexBuffer;
import MEngine.Rendering.GLBufferObjects.VbaLayout;
import MEngine.Rendering.GLBufferObjects.VertexArray;
import MEngine.Rendering.GLBufferObjects.VertexBuffer;
import MEngine.Rendering.Mesh;

public class Quad implements IPrimitiveMesh{
    private final Mesh m;

    public Quad(int width, int height){
        m=createQuad(width, height);
    }

    public Mesh getMesh(){
        return m;
    }

    private Mesh createQuad(int width, int height){
        float[] vertices={
            //Vertex Positions | Texture Coords
            0,     height, 0.0f, 0.0f, 1.0f,  //Bottom left
            width, height, 0.0f, 1.0f, 1.0f,  //Bottom right
            width, 0,      0.0f, 1.0f, 0.0f,  //Top right
            0,     0,      0.0f, 0.0f, 0.0f   //Top left
        };
        int[] indices={
            0,1,2,
            2,3,0
        };

        VertexArray va=new VertexArray();
        VertexBuffer vb=new VertexBuffer(vertices);
        VbaLayout layout=new VbaLayout();
        layout.pushFloat(3);
        layout.pushFloat(2);
        va.addBuffer(vb, layout);
        IndexBuffer ib=new IndexBuffer(indices);

        return new Mesh(va, ib);
    }
}
