package MEngine;

import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Utils{
    public static FloatBuffer arrayToFloatBuffer(float[] data){
        FloatBuffer buffer=BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    public static IntBuffer arrayToIntBuffer(int[] data){
        IntBuffer buffer=BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
    public static String loadTextFile(String filename){
        StringBuilder text = new StringBuilder();
        String line = null ;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            while( (line = reader.readLine()) !=null ){
                text.append(line);
                text.append('\n');
            }
        }catch(Exception e){
            throw new IllegalArgumentException("unable to load from file ["+filename+"]", e);
        }

        return text.toString();
    }
}
