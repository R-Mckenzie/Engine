package MEngine.Components;

import MEngine.Core.Time;
import MEngine.Game.Component;
import MEngine.Input.Keys;
import MEngine.Maths.Vec3;

public class TopDownController extends Component{
    private int speed;
    private Controls controls;
    private class Controls{
        int up;
        int down;
        int left;
        int right;
        protected Controls(int up, int down, int left, int right){
            this.up=up;
            this.down=down;
            this.left=left;
            this.right=right;
        }
    }

    public TopDownController(int speed){
        this.speed=speed;
        this.controls=new Controls(Keys.KEY_W, Keys.KEY_S, Keys.KEY_A, Keys.KEY_D);
    }

    public TopDownController(int speed, int up, int down, int left, int right){
        this.speed=speed;
        this.controls=new Controls(up, down, left, right);
    }

    @Override
    public void update(){
        Vec3 translation=new Vec3();
        if(MEngine.Input.Keyboard.isKeyHeld(controls.right)){
            translation.add(new Vec3(1,0,0));
        }if(MEngine.Input.Keyboard.isKeyHeld(controls.left)){
            translation.add(new Vec3(-1,0,0));
        }if(MEngine.Input.Keyboard.isKeyHeld(controls.up)){
            translation.add(new Vec3(0,-1,0));
        }if(MEngine.Input.Keyboard.isKeyHeld(controls.down)){
            translation.add(new Vec3(0,1,0));
        }

        translation.normalise();
        float frameSpeed=(float)(speed*Time.delta());
        translation.mul(new Vec3(frameSpeed,frameSpeed,0));
        parent.transform.translate(translation);
    }
}
