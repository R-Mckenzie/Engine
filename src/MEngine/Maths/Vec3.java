package MEngine.Maths;

public class Vec3{
    public float x;
    public float y;
    public float z;

    public Vec3(float x, float y, float z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Vec3(){
        this.x=0;
        this.y=0;
        this.z=0;
    }

    //For when we want to alter a specific instance's state
    public void add(Vec3 b){
        x+=b.x;
        y+=b.y;
        z+=b.z;
    }
    public void sub(Vec3 b){
        x-=b.x;
        y-=b.y;
        z-=b.z;
    }
    public void mul(Vec3 b){
        x*=b.x;
        y*=b.y;
        z*=b.z;
    }
    public void div(Vec3 b){
        x/=b.x;
        y/=b.y;
        z/=b.z;
    }

    //For when we need to return a new vector
    public static Vec3 add(Vec3 a, Vec3 b){
        return new Vec3(a.x+b.x, a.y+b.y, a.z+b.z);
    }
    public static Vec3 sub(Vec3 a, Vec3 b){
        return new Vec3(a.x-b.x, a.y-b.y, a.z-b.z);
    }
    public static Vec3 mul(Vec3 a, Vec3 b){
        return new Vec3(a.x*b.x, a.y*b.y, a.z*b.z);
    }
    public static Vec3 div(Vec3 a, Vec3 b){
        return new Vec3(a.x/b.x, a.y/b.y, a.z/b.z);
    }

    public float mag(){
        return (float)Math.sqrt(x*x + y*y + z*z);
    }

    public float dot(Vec3 b){
        return ((x*b.x)+(y*b.y)+(z*b.z));
    }

    public Vec3 cross(Vec3 b){
        return new Vec3(
            (y*b.z)-(z*b.y),
            (z*b.x)-(x*b.z),
            (x*b.y)-(y*b.x)
        );
    }
}
