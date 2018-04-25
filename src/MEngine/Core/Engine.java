package MEngine.Core;

public class Engine{
    private IGame game;
    private IRenderer renderer;
    private IWindow window;
    private boolean isRunning;

    public Engine(IGame game, IRenderer renderer, IWindow window){
        this.game=game;
        this.renderer=renderer;
        this.window=window;
        isRunning=false;
    }

    public void start(){
        if(!isRunning){
            renderer.init(window.width(), window.height());
            game.init();
            window.show();
            isRunning=true;
            run();
        }else{
            System.err.println("[ERROR] Engine.start() - MEngine.Game already running");
        }
    }

    public void stop(){
        isRunning=false;
        window.hide();
        window.close();
    }

    private void run(){
        final double idealDelta=Time.SECOND/60;
        long previousTime=Time.getTime();
        double timeAccumulator=0;
        int frames=0;
        int counter=0;

        while(isRunning){
            isRunning=!window.isCloseRequested();

            long currentTime=Time.getTime();
            double delta=currentTime-previousTime;
            Time.setDelta(delta/Time.SECOND);
            previousTime=currentTime;
            timeAccumulator+=delta;
            counter+=delta;

            if(timeAccumulator>=idealDelta){
                while(timeAccumulator>=idealDelta){
                    update();
                    draw();
                    timeAccumulator-=idealDelta;
                    frames++;
                }
            }else{
                try{
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            if(counter>Time.SECOND){
                System.out.println("frames: "+frames+", delta: "+Time.delta());
                frames=0;
                counter=0;
            }
        }
        dispose();
    }

    private void update(){
        game.update();
    }

    private void draw(){
        renderer.draw();
        window.refresh();
    }

    private void dispose(){
        window.dispose();
        renderer.dispose();
    }
}
