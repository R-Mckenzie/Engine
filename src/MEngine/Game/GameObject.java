package MEngine.Game;

import MEngine.Maths.Transform;

import java.util.ArrayList;
import java.util.List;

public class GameObject{
    public Transform transform;
    private final List<Component> components;

    public GameObject(){
        components=new ArrayList<>();
    }

    public GameObject(Component... components){
        this.transform=new Transform();
        this.components=new ArrayList<>();
        for(Component c:components)
            addComponent(c);
    }
    public GameObject(Transform transform, Component... components){
        this.transform=transform;
        this.components=new ArrayList<>();
        for(Component c:components)
            addComponent(c);
    }
    public void addComponent(Component c){
        components.add(c);
        c.setParent(this);
        c.init();
    }

    public <T extends Component> T getComponent(Class<T> type){
        for(Component c:components){
            if(type.isInstance(c)) return type.cast(c);
        }

        System.err.println("No component of type: "+type.getTypeName()+" found in gameObject");
        return null;
        //TODO: Add graceful exit procedures
    }

    public List<Component> components(){
        return components;
    }
}
