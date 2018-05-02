package MEngine.Physics;

import MEngine.Game.Component;
import MEngine.Maths.Transform;
import MEngine.Maths.Vec3;

public class AABBCollider extends Component{
    private Transform transform;
    public Vec3 position;
    public int width;
    public int height;

    public AABBCollider(int width, int height){
        this.transform=parent.transform;
        this.position=transform.position;
        this.width=(int)(width*transform.scale.x);
        this.height=(int)(height*transform.scale.y);
    }
}
