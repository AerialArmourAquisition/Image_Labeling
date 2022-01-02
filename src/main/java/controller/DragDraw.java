package controller;

import model.Overlay;

import view.Canvas;
import java.awt.Rectangle;
import java.awt.event.*;

public class DragDraw implements MouseListener, MouseMotionListener
{
    private int x;
    private int y;
    private Overlay overlay;
    private Canvas canvas;

    public DragDraw(Overlay overlay, Canvas canvas)
    {
        super();
        this.overlay = overlay;
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        int dx = e.getX() - x;
        int dy = e.getY() - y;
        Rectangle r = new Rectangle(x, y, dx, dy);
        overlay.addRect(canvas.removeOffset(r));
        canvas.updateSelection(null);
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        int dx = e.getX() - x;
        int dy = e.getY() - y;
        Rectangle r = new Rectangle(x, y, dx, dy);
        canvas.updateSelection(r);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        if(e.isControlDown())
        {
            int dx = e.getX() - x;
            int dy = e.getY() - y;
            canvas.setOffset(dx, dy);
        }
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

}
