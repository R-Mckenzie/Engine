package TestGame;
import MEngine.Game.GameObject;
import MEngine.Game.Scene;
import MEngine.Maths.Vec4;
import MEngine.Rendering.Camera;
import MEngine.Rendering.SolidRect;

public class TestScene extends Scene{
    GameObject cameraObj;
    GameObject rectObj;

    @Override
    protected void init(){
        cameraObj=new GameObject(new Camera());
        rectObj=new GameObject(new SolidRect(100,100,new Vec4(1,1,1,1)));
        addGameObject(cameraObj, rectObj);
    }

    @Override
    protected void onStart(){
        System.out.println("Started TestGame.TestScene");
    }

    @Override
    protected void update(){
    }

    @Override
    protected void onClose(){
        System.out.println("Closed TestGame.TestScene");
    }
}
