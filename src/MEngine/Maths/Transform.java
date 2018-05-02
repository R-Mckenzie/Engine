package MEngine.Maths;

public class Transform{
    public Vec3 position;
    public Vec3 rotation;
    public Vec3 scale;

    public Transform(){
        position=new Vec3();
        rotation=new Vec3();
        scale=new Vec3(1,1,1);
    }

    public Transform(Vec3 position){
        this.position=position;
        this.rotation=new Vec3();
        scale=new Vec3(1,1,1);
    }

    public Transform(Vec3 position, Vec3 rotation, Vec3 scale){
        this.position=position;
        this.rotation=rotation;
        this.scale=scale;
    }

    public void translate(Vec3 translation){
        this.position.add(translation);
    }

    public void rotate(Vec3 rotation){
        this.rotation.add(rotation);
    }

    public void scale(Vec3 scale){
        this.scale.add(scale);
    }

    public Mat4 toMatrix(){
        Mat4 t=new Mat4();
        t.translate(position);
        t.rotateX((float)Math.toRadians(rotation.x));
        t.rotateY((float)Math.toRadians(rotation.y));
        t.rotateZ((float)Math.toRadians(rotation.z));
        t.scale(scale);
        return t;
    }
}
