import Game.Scene;

public class TestScene extends Scene{

    @Override
    protected void init(){
        System.out.println("Initialised TestScene");
    }

    @Override
    protected void onStart(){
        System.out.println("Started TestScene");
    }

    @Override
    protected void update(){

    }

    @Override
    protected void onClose(){
        System.out.println("Closed TestScene");
    }
}
