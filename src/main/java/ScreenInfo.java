import java.awt.*;

public abstract class ScreenInfo
{
    public static Dimension getSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
