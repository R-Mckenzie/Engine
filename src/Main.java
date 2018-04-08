import Core.*;
import Game.Scene;
import Game.SceneManagerGame;
import Rendering.Renderer;

public class Main{
    static IWindow window=new Window(100, 100, "test");
    static IRenderer renderer=new Renderer();
    static Scene testScene=new TestScene();
    static IGame game=new SceneManagerGame(renderer, testScene);
    static Engine engine=new Engine(game, renderer, window);

    public static void main(String[] args){
        engine.start();
    }
}
