package MEngine.Core;

public interface IWindow{
    void refresh();
    void show();
    void hide();
    void close();
    void dispose();
    boolean isCloseRequested();
    void setSize(int width, int height);
    int width();
    int height();
}
