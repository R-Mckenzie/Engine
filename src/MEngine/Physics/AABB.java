package MEngine.Physics;

import MEngine.Maths.Vec3;

public class AABB{
    public Vec3 position;
    public int width;
    public int height;

    public AABB(Vec3 position, int width, int height){
        this.position=position;
        this.width=width;
        this.height=height;
    }
}
