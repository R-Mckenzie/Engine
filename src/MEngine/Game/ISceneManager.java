package MEngine.Game;


public interface ISceneManager{
    void init();
    void addScene(String sceneKey, Scene scene);
    void startScene(String sceneKey);
}
