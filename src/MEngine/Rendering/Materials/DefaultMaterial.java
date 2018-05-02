package MEngine.Rendering.Materials;

import MEngine.Maths.Mat4;
import MEngine.Maths.Vec4;
import MEngine.Rendering.Material;
import MEngine.Resources.TexturePNG;

public class DefaultMaterial extends Material{
    private Vec4 colour;
    private TexturePNG texture;
    private static final Shader shader=new Shader(
        "res/Shaders/solidColourV.txt",
        "res/Shaders/solidColourF.txt");

    public DefaultMaterial(){
        super(shader);
        texture=new TexturePNG("res/solidRect.png");
        colour=new Vec4(1,1,1,1);
    }

    public void setColour(Vec4 colour){
        this.colour=colour;
    }

    public void setTexture(TexturePNG texture){
        this.texture=texture;
    }

    @Override
    protected void updatePerPassUniforms(){

    }

    @Override
    protected void updatePerInstanceUniforms(Mat4 model, Mat4 view, Mat4 projection){
        shader.setUniform("u_Colour", colour);
        shader.setUniform("u_model", model);
        shader.setUniform("u_view", view);
        shader.setUniform("u_projection", projection);

        texture.bind();
    }

    @Override
    protected void updatePerMeshUniforms(){

    }
}
