package MEngine.Core;

import MEngine.Rendering.Camera;
import MEngine.Rendering.Renderable;

public interface IRenderer{
    void init(int width, int height);
    void addRenderable(Renderable r);
    void clearRenderables();
    void setCamera(Camera camera);
    void draw();
    void dispose();
}
