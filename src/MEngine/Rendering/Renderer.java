package MEngine.Rendering;

import MEngine.Core.IRenderer;
import MEngine.Game.GameObject;
import MEngine.Maths.Mat4;
import MEngine.Maths.Vec3;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer implements IRenderer{
    private Map<Material, List<Renderable>> renderables;
    private GLCapabilities capabilities;
    private Vec3 clearColour;
    private Mat4 projection;
    private Camera camera;

    public Renderer(){
        renderables=new HashMap<>();
        clearColour=new Vec3(0,0,0);
        camera=new Camera();
        GameObject camObj=new GameObject(camera);
    }
    public Renderer(Vec3 backgroundColour){
        renderables=new HashMap<>();
        this.clearColour=backgroundColour;
        camera=new Camera();
        GameObject camObj=new GameObject(camera);
    }

    @Override
    public void init(int windowWidth, int windowHeight){
        System.out.println(windowWidth+","+windowHeight);
        projection=createOrthoProjectionMatrix(windowWidth, windowHeight);
        capabilities=GL.createCapabilities();
        GL11.glClearColor(clearColour.x, clearColour.y, clearColour.z, 1);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void addRenderable(Renderable r){
        if(renderables.containsKey(r.material)){
            renderables.get(r.material).add(r);
        }else{
            List<Renderable> newList=new ArrayList<>();
            newList.add(r);
            renderables.put(r.material, newList);
        }
    }

    @Override
    public void clearRenderables(){
        for(List<Renderable> list:renderables.values())
            list.clear();
    }

    @Override
    public void setCamera(Camera c){
        if(c!=null){
            camera=c;
            System.out.println("set camera");
        }else{
            camera=new Camera();
        }
    }

    @Override
    public void draw(){
        prepare();
        for(Material m:renderables.keySet()){
            m.bindShader();
            for(Renderable e:renderables.get(m)){
                m.updatePerInstanceUniforms(e.transform().toMatrix(), camera.getViewMatrix(), projection);
                e.mesh().bind();
                GL11.glDrawElements(GL11.GL_TRIANGLES, e.mesh().getIBO().count, GL11.GL_UNSIGNED_INT, 0);
            }
        }
    }
    @Override
    public void dispose(){
    }

    private void prepare(){
        GL.setCapabilities(capabilities);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    private static Mat4 createOrthoProjectionMatrix(float right, float bottom){
        float left=0;
        float top=0;
        Mat4 m=new Mat4();
        m.col1.x=2/(right-left);
        m.col2.y=2/(top-bottom);
        m.col3.z=-(2/(1.0f-(-1.0f)));
        m.col4.x=-((right+left)/(right-left));
        m.col4.y=-((top+bottom)/(top-bottom));
        m.col4.z=-((1.0f+(-1.0f))/(1.0f-(-1.0f)));
        m.col4.w=1;
        return m;
    }
}
