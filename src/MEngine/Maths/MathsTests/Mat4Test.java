package MEngine.Maths.MathsTests;

import MEngine.Maths.Mat4;
import MEngine.Maths.Vec4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Mat4Test{
    Mat4 m;

    @Test
    public void testAdd(){
        m=new Mat4();
        assertEquals(1, m.col1.x,0);
        assertEquals(1, m.col4.w,0);
        m.add(new Mat4());
        assertEquals(2, m.col1.x,0);
        assertEquals(2, m.col4.w,0);
    }

    @Test
    public void testSub(){
        m=new Mat4();
        assertEquals(1, m.col1.x,0);
        assertEquals(1, m.col4.w,0);
        m.sub(new Mat4());
        assertEquals(0, m.col1.x,0);
        assertEquals(0, m.col4.w,0);
    }

    @Test
    public void testMul(){
        m=new Mat4(
            new Vec4(2,2,2,2),
            new Vec4(2,2,2,2),
            new Vec4(2,2,2,2),
            new Vec4(2,2,2,2));

        m.mul(new Mat4());
        assertEquals(2, m.col1.x, 0);
        assertEquals(2, m.col1.y, 0);
        assertEquals(2, m.col3.x, 0);

        m.mul(new Mat4(
           new Vec4(2,2,2,2),
           new Vec4(2,2,2,2),
           new Vec4(2,2,2,2),
           new Vec4(2,2,2,2)));

        assertEquals(16, m.col1.x, 0);
        assertEquals(16, m.col1.y, 0);
        assertEquals(16, m.col3.x, 0);

        Mat4 a=new Mat4(
            new Vec4(1,2,3,4),
            new Vec4(5,6,7,8),
            new Vec4(9,10,11,12),
            new Vec4(13,14,15,16));

        a.mul(new Mat4(a));

        assertEquals(90, a.col1.x, 0);
        assertEquals(120, a.col1.w, 0);
        assertEquals(600, a.col4.w, 0);
    }
}
