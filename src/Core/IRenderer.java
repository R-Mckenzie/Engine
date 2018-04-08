package Core;

import Rendering.Renderable;

public interface IRenderer{
    void init();
    void addRenderable(Renderable r);
    void clearRenderables();
    void draw();
    void dispose();
}
