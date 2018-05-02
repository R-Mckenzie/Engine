package MEngine.Game;

import MEngine.Rendering.Camera;
import MEngine.Rendering.Renderable;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene{
    ISceneManager parent;
    List<GameObject> objects;
    List<Renderable> renderables;
    Camera camera;

    public Scene(){
        objects=new ArrayList<>();
        renderables=new ArrayList<>();
    }
    //To be called AFTER components have been added to gameobject
    protected void addGameObject(GameObject... gos){
        for(GameObject go:gos){
            if(!objects.contains(go)){
                for(Component c : go.components()){
                    if(c instanceof Renderable){
                        renderables.add((Renderable)c);
                    }else if(c instanceof Camera){
                        camera=(Camera)c;
                    }
                }
                objects.add(go);
            }
        }

    }
    protected void updateGameObjects(){
        for(GameObject go:objects){
            for(Component c:go.components()){
                c.update();
            }
        }
    }
    protected abstract void init();
    protected abstract void onStart();
    protected abstract void update();
    protected abstract void onClose();
}
