package MEngine.Rendering;

import MEngine.Maths.Vec4;
import MEngine.Rendering.Materials.DefaultMaterial;
import MEngine.Rendering.Primitives.Quad;
import MEngine.Resources.TexturePNG;

public class Sprite extends Renderable{
    private DefaultMaterial defaultMaterial;
    private TexturePNG texture;
    public Sprite(int width, int height, TexturePNG texture){
        super(new DefaultMaterial(), new Mesh(new Quad(width, height)));
        this.defaultMaterial=(DefaultMaterial)material;
        this.defaultMaterial.setTexture(texture);
        this.texture=texture;
    }

    public void setColour(Vec4 colour){
        defaultMaterial.setColour(colour);
    }
    public void setTexture(TexturePNG texture){
        defaultMaterial.setTexture(texture);
    }
}
