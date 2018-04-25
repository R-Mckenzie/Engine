package MEngine.Rendering;

import MEngine.Game.Component;
import MEngine.Maths.Transform;

public class Renderable extends Component{
    protected Material material;
    private final Mesh mesh;

    public Renderable(Material material, Mesh mesh){
        this.material=material;
        this.mesh=mesh;
    }

    protected Transform transform(){
        return parent.transform;
    }

    protected Mesh mesh(){
        return mesh;
    }

}
