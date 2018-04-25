package MEngine.Rendering;

import MEngine.Game.Component;
import MEngine.Maths.Mat4;
import MEngine.Maths.Vec3;

public class Camera extends Component{

    public Camera(){

    }

    public Mat4 getViewMatrix(){
        Mat4 view=new Mat4();
        view.rotateX((float)Math.toRadians(parent.transform.rotation.x));
        view.rotateY((float)Math.toRadians(parent.transform.rotation.y));
        view.rotateZ((float)Math.toRadians(parent.transform.rotation.z));
        Vec3 negPos=new Vec3(-parent.transform.position.x, -parent.transform.position.y, -parent.transform.position.z);
        view.translate(negPos);
        return view;
    }
}
