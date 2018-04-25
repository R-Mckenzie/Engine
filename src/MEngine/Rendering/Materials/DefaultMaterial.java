package MEngine.Rendering.Materials;

import MEngine.Maths.Mat4;
import MEngine.Maths.Vec4;
import MEngine.Rendering.Material;

public class DefaultMaterial extends Material{
    private Vec4 colour;
    private static final Shader shader=new Shader(
        "res/Shaders/solidColourV.txt",
        "res/Shaders/solidColourF.txt");

    public DefaultMaterial(){
        super(shader);
        colour=new Vec4(1,0.4f,1,1);
    }

    public void setColour(Vec4 colour){
        this.colour=colour;
    }


    @Override
    protected void updatePerPassUniforms(){

    }

    @Override
    protected void updatePerInstanceUniforms(Mat4 MVP){
        shader.setUniform("u_Colour", colour);
        shader.setUniform("u_MVP", MVP);
    }

    @Override
    protected void updatePerMeshUniforms(){

    }
}
