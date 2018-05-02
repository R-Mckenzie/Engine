package TestGame;

import MEngine.Components.TopDownController;
import MEngine.Game.GameObject;
import MEngine.Game.Scene;
import MEngine.Input.Keys;
import MEngine.Maths.Transform;
import MEngine.Maths.Vec3;
import MEngine.Maths.Vec4;
import MEngine.Rendering.Camera;
import MEngine.Rendering.Sprite;
import MEngine.Resources.TexturePNG;

public class TestScene extends Scene{
    GameObject cameraObj;
    GameObject rectObj;
    GameObject rectObj2;

    @Override
    protected void init(){
        cameraObj=new GameObject(new Camera());
        rectObj=new GameObject(new Sprite(100,100, new TexturePNG("res/Alien.png")), new TopDownController(30));
        rectObj2=new GameObject(
            new Transform(new Vec3(300, 300, 0)),
            new Sprite(100,100, new TexturePNG("res/Alien.png")),
            new TopDownController(30, Keys.KEY_UP, Keys.KEY_DOWN, Keys.KEY_LEFT, Keys.KEY_RIGHT));
        addGameObject(cameraObj, rectObj, rectObj2);


        rectObj.getComponent(Sprite.class).setColour(new Vec4(1,0,0,1));
        rectObj2.getComponent(Sprite.class).setColour(new Vec4(0,0,1,1));
    }

    @Override
    protected void onStart(){
        System.out.println("Started TestGame.TestScene");
    }

    @Override
    protected void update(){
        if(MEngine.Input.Keyboard.isKeyUp(Keys.KEY_Q)){
        }
    }

    @Override
    protected void onClose(){
        System.out.println("Closed TestGame.TestScene");
    }
}
