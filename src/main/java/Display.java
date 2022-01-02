import javax.swing.*;
import java.awt.*;

public class Display extends JPanel
{
    private App app;
    private Dimension screenSize;

    public Display(App app)
    {
        this.app = app;
        screenSize = ScreenInfo.getSize();
        setBackground(Color.DARK_GRAY);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics)
    {
        Graphics2D g = (Graphics2D) graphics;
        if(app.getImage() != null)
        {
            g.drawImage(app.getImage(),0,0, null);
        }
        else
        {
            int x = (screenSize.width / 2) - 40;
            int y = screenSize.height / 2;
            g.drawString("No Image Selected", x, y);
        }
    }
}
