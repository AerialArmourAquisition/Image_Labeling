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
    private int x_offset = 0;
    private int y_offset = 0;
    private Rectangle selection;

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
            g.setColor(Color.darkGray);
            g.fillRect(0,0,screenSize.width, screenSize.height);
            g.drawImage(mainFrame.getImage(),x_offset,y_offset, null);

            g.setColor(Color.RED);
            for(Rectangle r: mainFrame.getOverlay())
            {
                Rectangle r_o = addOffset(r);
                g.drawRect(r_o.x, r_o.y, r_o.width, r_o.height);
            }

            if(selection != null)
            {
                g.drawRect(selection.x,
                           selection.y,
                           selection.width,
                           selection.height);
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
        DragDraw dragDraw = new DragDraw(overlay, this);
        addMouseListener(dragDraw);
        addMouseMotionListener(dragDraw);
    }

    public void resetOffset()
    {
        x_offset = 0;
        y_offset = 0;
        repaint();
    }

    public void setOffset(int dx, int dy)
    {
        x_offset = x_offset + dx;
        y_offset = y_offset + dy;
        repaint();
    }

    public void updateSelection(Rectangle rectangle)
    {
        selection = rectangle;
        repaint();
    }

    public Rectangle addOffset(Rectangle rectangle)
    {
        return new Rectangle(rectangle.x + x_offset,
                rectangle.y + y_offset,
                rectangle.width,
                rectangle.height);
    }

    public Rectangle removeOffset(Rectangle rectangle)
    {
        return new Rectangle(rectangle.x - x_offset,
                rectangle.y - y_offset,
                rectangle.width,
                rectangle.height);
    }
}
