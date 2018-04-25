package MEngine.Maths;

public class Vec2{
    public float x;
    public float y;
    
    public Vec2(){
        this.x=0;
        this.y=0;
    }
    public Vec2(float x, float y){
        this.x=x;
        this.y=y;
    }

    public void add(Vec2 b){
        x+=b.x;
        y+=b.y;
    }
    public void sub(Vec2 b){
        x-=b.x;
        y-=b.y;
    }
    public void mul(Vec2 b){
        x*=b.x;
        y*=b.y;
    }
    public void div(Vec2 b){
        x/=b.x;
        y/=b.y;
    }

    public static Vec2 add(Vec2 a, Vec2 b){
        return new Vec2(a.x+b.x, a.y+b.y);
    }
    public static Vec2 sub(Vec2 a, Vec2 b){
        return new Vec2(a.x-b.x, a.y-b.y);
    }
    public static Vec2 mul(Vec2 a, Vec2 b){
        return new Vec2(a.x*b.x, a.y*b.y);
    }
    public static Vec2 div(Vec2 a, Vec2 b){
        return new Vec2(a.x/b.x, a.y/b.y);
    }

}
