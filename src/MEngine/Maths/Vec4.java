package MEngine.Maths;

public class Vec4{
    public float x;
    public float y;
    public float z;
    public float w;

    public Vec4(float x, float y, float z, float w){
        this.x=x;
        this.y=y;
        this.z=z;
        this.w=w;
    }
    public Vec4(Vec4 v){
        this.x=v.x;
        this.y=v.y;
        this.z=v.z;
        this.w=v.w;
    }
    public Vec4(){
        this.x=1;
        this.y=1;
        this.z=1;
        this.w=1;
    }

    //For when we want to alter a specific instance's state
    public void add(Vec4 b){
        x+=b.x;
        y+=b.y;
        z+=b.z;
        w+=b.w;
    }
    public void sub(Vec4 b){
        x-=b.x;
        y-=b.y;
        z-=b.z;
        w-=b.w;
    }
    public void mul(Vec4 b){
        x*=b.x;
        y*=b.y;
        z*=b.z;
        w*=b.w;
    }
    public void div(Vec4 b){
        x/=b.x;
        y/=b.y;
        z/=b.z;
        w/=b.w;
    }

    //For when we need to return a new vector
    public static Vec4 add(Vec4 a, Vec4 b){
        return new Vec4(a.x+b.x, a.y+b.y, a.z+b.z, a.w+b.w);
    }
    public static Vec4 sub(Vec4 a, Vec4 b){
        return new Vec4(a.x-b.x, a.y-b.y, a.z-b.z, a.w-b.w);
    }
    public static Vec4 mul(Vec4 a, Vec4 b){
        return new Vec4(a.x*b.x, a.y*b.y, a.z*b.z, a.w*b.w);
    }
    public static Vec4 div(Vec4 a, Vec4 b){
        return new Vec4(a.x/b.x, a.y/b.y, a.z/b.z, a.w/b.w);
    }

    public float mag(){
        return (float)Math.sqrt(x*x + y*y + z*z + w*w);
    }

    public float dot(Vec4 b){
        return ((x*b.x)+(y*b.y)+(z*b.z)+(w*b.w));
    }
}
