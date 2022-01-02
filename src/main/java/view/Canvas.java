package view;

import controller.DragDraw;
import controller.ScreenInfo;
import model.Overlay;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel
{
    private MainFrame mainFrame;
    private Dimension screenSize;

    public Canvas(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        screenSize = ScreenInfo.getSize();
        setBackground(Color.DARK_GRAY);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics)
    {
        Graphics2D g = (Graphics2D) graphics;
        if(mainFrame.getImage() != null)
        {
            g.drawImage(mainFrame.getImage(),0,0, null);

            g.setColor(Color.RED);
            for(Rectangle r: mainFrame.getOverlay())
            {
                g.drawRect(r.x, r.y, r.width, r.height);
            }
        }
        else
        {
            int x = (screenSize.width / 2) - 40;
            int y = screenSize.height / 2;
            g.drawString("No Image Selected", x, y);
        }
    }

    public void setOverlay(Overlay overlay)
    {
        this.addMouseListener(new DragDraw(overlay, this));
    }
}
