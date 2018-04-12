package Maths.MathsTests;

import Maths.Vec3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Vec3Test{
    Vec3 testVec;

    //Test basic operators
    @Test
    public void testAdd(){
        testVec=new Vec3(0,0,0);
        assertEquals(0.0f, testVec.x, 0);
        assertEquals(0.0f, testVec.y, 0);
        assertEquals(0.0f, testVec.z, 0);
        testVec.add(new Vec3(10,10,10));
        assertEquals(10f, testVec.z, 0);
        assertEquals(10f, testVec.z, 0);
        assertEquals(10f, testVec.z, 0);
    }
    @Test
    public void testSub(){
        testVec=new Vec3(10,10,10);
        assertEquals(10f, testVec.z, 0);
        assertEquals(10f, testVec.z, 0);
        assertEquals(10f, testVec.z, 0);
        testVec.sub(new Vec3(10,10,10));
        assertEquals(0.0f, testVec.x, 0);
        assertEquals(0.0f, testVec.y, 0);
        assertEquals(0.0f, testVec.z, 0);
    }
    @Test
    public void testMul(){
        testVec=new Vec3(10,10,10);
        assertEquals(10f, testVec.z, 0);
        assertEquals(10f, testVec.z, 0);
        assertEquals(10f, testVec.z, 0);
        testVec.mul(new Vec3(2,2,2));
        assertEquals(20f, testVec.x, 0);
        assertEquals(20f, testVec.y, 0);
        assertEquals(20f, testVec.z, 0);
    }
    @Test
    public void testDiv(){
        testVec=new Vec3(10,10,10);
        assertEquals(10f, testVec.z, 0);
        assertEquals(10f, testVec.z, 0);
        assertEquals(10f, testVec.z, 0);
        testVec.div(new Vec3(10,10,10));
        assertEquals(1f, testVec.x, 0);
        assertEquals(1f, testVec.y, 0);
        assertEquals(1f, testVec.z, 0);
    }

    //Test other functions
    @Test
    public void testMag(){
        testVec=new Vec3(10,10,10);
        float answer=(float)(10*Math.sqrt(3)); //Answer calculated by hand
        assertEquals(answer, testVec.mag(), 0);

        testVec=new Vec3(5,2,8);
        answer=(float)Math.sqrt(93); //Answer calculated by hand
        assertEquals(answer, testVec.mag(), 0);
    }
    @Test
    public void testDot(){
        testVec=new Vec3(9,2,7);
        float dot=testVec.dot(new Vec3(4,8,10));
        assertEquals(122, dot, 0);
    }
    @Test
    public void testCross(){
        testVec=new Vec3(2,3,4);
        Vec3 cross=testVec.cross(new Vec3(5,6,7));
        assertEquals(-3, cross.x, 0);
        assertEquals(6, cross.y, 0);
        assertEquals(-3, cross.z, 0);
    }
}
