package MEngine.Rendering.Materials;

import MEngine.Maths.Mat4;
import MEngine.Maths.Vec4;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.util.HashMap;
import java.util.Map;

import static MEngine.Utils.loadTextFile;

public class Shader{
    private final int id;
    private final Map<String, Integer> locationCache;

    public Shader(String vertexPath, String fragmentPath){
        locationCache=new HashMap<>();
        id=loadProgram(vertexPath, fragmentPath);
    }

    private int getLocation(String uniformName){
        if(locationCache.containsKey(uniformName)){
            return locationCache.get(uniformName);
        }else{
            int location=GL20.glGetUniformLocation(id, uniformName);
            if(location!=-1){
                locationCache.put(uniformName, location);
                return location;
            }
        }

        System.out.println("Problem finding uniform: '"+uniformName);
        return -1;
    }

    protected void setUniform(String uniformName, Vec4 vector){
        bind();
        GL20.glUniform4f(getLocation(uniformName), vector.x, vector.y, vector.z, vector.w);
    }
    protected void setUniform(String uniformName, Mat4 matrix){
        bind();
        GL20.glUniformMatrix4fv(getLocation(uniformName), false, matrix.toFloat());
    }

    private int loadProgram(String vertexSourcePath, String fragmentSourcePath){
        int vertexID=loadShader(vertexSourcePath, GL20.GL_VERTEX_SHADER);
        int fragmentID=loadShader(fragmentSourcePath, GL20.GL_FRAGMENT_SHADER);
        int programID=GL20.glCreateProgram();

        GL20.glAttachShader(programID, vertexID);
        GL20.glAttachShader(programID, fragmentID);

        GL20.glLinkProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS)==GL11.GL_FALSE){
            throw new RuntimeException("could not link shader. Reason: " + GL20.glGetProgramInfoLog(programID, 1000));
        }

        GL20.glValidateProgram(programID);

        GL20.glDeleteShader(vertexID);
        GL20.glDeleteShader(fragmentID);

        System.out.println("Loaded shader");
        return programID;
    }

    private int loadShader(String filename, int shaderType){
        String shaderSource=loadTextFile(filename);
        int shaderID = GL20.glCreateShader(shaderType);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);


        if(GL20.glGetShaderi(shaderID,GL20.GL_COMPILE_STATUS)==GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 1000));
            System.out.println("Could not compile shader: "+filename);
            System.exit(-1);
        }else{
            System.out.println((shaderType==GL20.GL_VERTEX_SHADER?"Vertex ":"Fragment ") + "shader compiled successfully");
        }

        return shaderID;
    }

    public void bind(){
        GL20.glUseProgram(id);
    }

    public void dispose(){
        GL20.glDeleteProgram(id);
    }
}
