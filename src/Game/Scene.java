package Game;

public abstract class Scene{
    ISceneManager parent;

    protected abstract void init();
    protected abstract void onStart();
    protected abstract void update();
    protected abstract void onClose();
}
