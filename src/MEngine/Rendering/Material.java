package MEngine.Rendering;

import MEngine.Maths.Mat4;
import MEngine.Rendering.Materials.Shader;

public abstract class Material{
    private Shader shader;

    public Material(Shader shader){
        this.shader=shader;
    }

    protected void bindShader(){
        shader.bind();
    }
    protected abstract void updatePerPassUniforms();
    protected abstract void updatePerInstanceUniforms(Mat4 model, Mat4 view, Mat4 projection);
    protected abstract void updatePerMeshUniforms();
}
