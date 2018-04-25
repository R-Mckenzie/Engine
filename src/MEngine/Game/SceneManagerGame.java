package MEngine.Game;

import MEngine.Core.IGame;
import MEngine.Core.IRenderer;
import MEngine.Rendering.Renderable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneManagerGame implements IGame, ISceneManager{
    private final Map<String, Scene> sceneMap;
    private IRenderer renderer;
    private Scene currentScene;
    private final List<Scene> alreadyInitialised;

    private final String initialKey;

    public SceneManagerGame(IRenderer renderer, String initialSceneKey, Scene initialScene){
        this.renderer=renderer;
        this.initialKey=initialSceneKey;
        sceneMap=new HashMap<>();
        alreadyInitialised=new ArrayList<>();
        addScene(initialSceneKey, initialScene);
    }

    @Override
    public void init(){
        startScene(initialKey);
    }

    @Override
    public void addScene(String sceneKey, Scene scene){
        if(!sceneMap.containsKey(sceneKey)){
            sceneMap.put(sceneKey, scene);
            scene.parent=this;
        }else{
            System.err.println("MEngine.Game already contains scene with key: "+sceneKey);
        }

    }

    @Override
    public void startScene(String sceneKey){
        Scene newScene=sceneMap.get(sceneKey);
        if(currentScene!=newScene){
            if(currentScene!=null)
                currentScene.onClose();

            renderer.clearRenderables();
            if(!alreadyInitialised.contains(newScene)){
                newScene.init();
                alreadyInitialised.add(newScene);
            }
            newScene.onStart();
            for(Renderable r:newScene.renderables){
                renderer.addRenderable(r);
                System.out.println("added renderable");
            }
            renderer.setCamera(newScene.camera);
            currentScene=newScene;
        }else{
            System.err.println("MEngine.Game already running scene with key: "+sceneKey);
        }
    }

    @Override
    public void update(){
        currentScene.update();
    }
}
