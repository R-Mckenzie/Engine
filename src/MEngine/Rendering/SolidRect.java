package MEngine.Rendering;

import MEngine.Maths.Vec4;
import MEngine.Rendering.Materials.DefaultMaterial;
import MEngine.Rendering.Primitives.Quad;

public class SolidRect extends Renderable{
    private DefaultMaterial defaultMaterial;
    public SolidRect(int width, int height, Vec4 colour){
        super(new DefaultMaterial(), new Mesh(new Quad(width, height)));
        this.defaultMaterial=(DefaultMaterial)material;
        setColour(colour);
    }

    public void setColour(Vec4 colour){
        defaultMaterial.setColour(colour);
    }
}
