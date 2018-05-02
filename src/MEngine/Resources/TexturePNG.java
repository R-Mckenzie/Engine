package MEngine.Resources;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class TexturePNG{
    ByteBuffer pixelData;
    private final int id;

    public TexturePNG(String filepath){
        id=GL11.glGenTextures();
        bind();
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

        TexData data=loadTexture(filepath);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, data.width, data.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data.buffer);
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, 4);GL11.
        glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
    }

     public class TexData{
        public ByteBuffer buffer;
        public int width;
        public int height;
    }

    private TexData loadTexture(String filepath){
        try{
            TexData data=new TexData();
            InputStream in=new FileInputStream(filepath);
            PNGDecoder decoder=new PNGDecoder(in);
            data.width=decoder.getWidth();
            data.height=decoder.getHeight();
            data.buffer=ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());
            decoder.decode(data.buffer, decoder.getWidth()*4, PNGDecoder.Format.RGBA);
            data.buffer.flip();
            in.close();
            return data;
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
            return null;
        }
    }
     public void bind(){
         GL13.glActiveTexture(0);
         GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
     }
}
