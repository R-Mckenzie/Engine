package Maths;

/*
 * Mat4:
 * A class that represents a 4x4
 * matrix in column-major order
 * for easy use with openGL.
 *
 */

public class Mat4{
    //Each column starts with x at
    //the top, and w at the bottom.
    public Vec4 col1;
    public Vec4 col2;
    public Vec4 col3;
    public Vec4 col4;

    public Mat4(){
        //Matrices are initialised as the identity matrix
        col1=new Vec4(1,0,0,0);
        col2=new Vec4(0,1,0,0);
        col3=new Vec4(0,0,1,0);
        col4=new Vec4(0,0,0,1);
    }

    public Mat4(Mat4 m){
        this.col1=m.col1;
        this.col2=m.col2;
        this.col3=m.col3;
        this.col4=m.col4;
    }

    public Mat4(Vec4 col1, Vec4 col2, Vec4 col3, Vec4 col4){
        this.col1=col1;
        this.col2=col2;
        this.col3=col3;
        this.col4=col4;
    }

    public void add(Mat4 b){
        col1.add(b.col1);
        col2.add(b.col2);
        col3.add(b.col3);
        col4.add(b.col4);
    }
    public void sub(Mat4 b){
        col1.sub(b.col1);
        col2.sub(b.col2);
        col3.sub(b.col3);
        col4.sub(b.col4);
    }
    public void mul(Mat4 b){
        Vec4 aCol1=new Vec4(col1);
        Vec4 aCol2=new Vec4(col2);
        Vec4 aCol3=new Vec4(col3);
        Vec4 aCol4=new Vec4(col4);
        Vec4 bRow1=new Vec4(b.col1.x, b.col2.x, b.col3.x, b.col4.x);
        Vec4 bRow2=new Vec4(b.col1.y, b.col2.y, b.col3.y, b.col4.y);
        Vec4 bRow3=new Vec4(b.col1.z, b.col2.z, b.col3.z, b.col4.z);
        Vec4 bRow4=new Vec4(b.col1.w, b.col2.w, b.col3.w, b.col4.w);

        col1.x=aCol1.dot(bRow1);
        col1.y=aCol1.dot(bRow2);
        col1.z=aCol1.dot(bRow3);
        col1.w=aCol1.dot(bRow4);

        col2.x=aCol2.dot(bRow1);
        col2.y=aCol2.dot(bRow2);
        col2.z=aCol2.dot(bRow3);
        col2.w=aCol2.dot(bRow4);

        col3.x=aCol3.dot(bRow1);
        col3.y=aCol3.dot(bRow2);
        col3.z=aCol3.dot(bRow3);
        col3.w=aCol3.dot(bRow4);

        col4.x=aCol4.dot(bRow1);
        col4.y=aCol4.dot(bRow2);
        col4.z=aCol4.dot(bRow3);
        col4.w=aCol4.dot(bRow4);
    }

    public void mul(float s){
        Vec4 factor=new Vec4(s,s,s,s);
        col1.mul(factor);
        col2.mul(factor);
        col3.mul(factor);
        col4.mul(factor);
    }

    //Transforms
    public void translate(Vec3 t){
        col3.x = t.x;
        col3.y = t.y;
        col3.z = t.z;
        col3.w = 1.0f;
    }
    public void rotateX(float r){
        Mat4 xRot=new Mat4();
        xRot.col2.y=(float)Math.cos(r);
        xRot.col2.z=(float)Math.sin(r);
        xRot.col3.y=(float)(-Math.sin(r));
        xRot.col3.z=(float)Math.cos(r);

        this.mul(xRot);
    }
    public void rotateY(float r){
        Mat4 yRot=new Mat4();
        yRot.col1.x=(float)Math.cos(r);
        yRot.col1.z=(float)(-Math.sin(r));
        yRot.col3.x=(float)Math.sin(r);
        yRot.col3.z=(float)Math.cos(r);

        this.mul(yRot);
    }
    public void rotateZ(float r){
        Mat4 zRot=new Mat4();
        zRot.col1.x=(float)Math.cos(r);
        zRot.col1.y=(float)Math.sin(r);
        zRot.col2.x=(float)(-Math.sin(r));
        zRot.col2.y=(float)Math.cos(r);

        this.mul(zRot);
    }
    public void scale(Vec3 s){
        Mat4 scale=new Mat4();
        scale.col1.x=s.x;
        scale.col2.y=s.y;
        scale.col3.z=s.z;
        scale.col4.w=1;

        this.mul(scale);
    }
}
