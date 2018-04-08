package Game;

import Core.IGame;
import Core.IRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneManagerGame implements IGame, ISceneManager{
    private final Map<String, Scene> sceneMap;
    private Scene currentScene;
    private List<Scene> alreadyInitialised;

    public SceneManagerGame(IRenderer renderer, Scene initialScene){
        currentScene=initialScene;
        sceneMap=new HashMap<>();
        alreadyInitialised=new ArrayList<>();
    }

    @Override
    public void init(){
        currentScene.init();
        currentScene.onStart();
        alreadyInitialised.add(currentScene);
    }

    @Override
    public void addScene(String sceneKey, Scene scene){
        if(!sceneMap.containsKey(sceneKey)){
            sceneMap.put(sceneKey, scene);
            scene.parent=this;
        }else{
            System.err.println("Game already contains scene with key: "+sceneKey);
        }

    }

    @Override
    public void startScene(String sceneKey){
        Scene newScene=sceneMap.get(sceneKey);
        if(currentScene!=newScene){
            currentScene.onClose();
            if(!alreadyInitialised.contains(newScene)){
                newScene.init();
                alreadyInitialised.add(newScene);
            }
            newScene.onStart();
            currentScene=newScene;
        }else{
            System.err.println("Game already running scene with key: "+sceneKey);
        }
    }

    @Override
    public void update(){
        currentScene.update();
    }
}
