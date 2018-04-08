package Core.CoreTests;

import Core.IWindow;
import Core.Window;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WindowTest{
    IWindow window;

    @Test
    public void testResize(){
        window=new Window(100, 100, "Test");
        assertNotEquals(200, window.width());
        assertEquals(100, window.width());
        assertEquals(100, window.height());
        window.setSize(200, 200);
        assertEquals("Width set to 200 successfully", 200, window.width());
        assertEquals("Width set to 200 successfully", 200, window.height());
        window.dispose();
    }


}
