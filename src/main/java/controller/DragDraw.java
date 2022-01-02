package controller;

import model.Overlay;

import view.Canvas;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DragDraw implements MouseListener
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Overlay overlay;
    private Canvas canvas;

    public DragDraw(Overlay overlay, Canvas canvas)
    {
        super();
        this.overlay = overlay;
        this.canvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        width = e.getX() - x;
        height = e.getY() - y;
        Rectangle r = new Rectangle(x, y, width, height);
        overlay.addRect(r);
        canvas.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
