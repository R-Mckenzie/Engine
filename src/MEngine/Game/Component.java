package MEngine.Game;

public abstract class Component{
    protected GameObject parent;

    public void init(){}
    public void update(){}

    void setParent(GameObject go){
        if(parent==null)
            parent=go;
    }
}
