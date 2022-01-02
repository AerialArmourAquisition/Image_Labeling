package model;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Overlay
{
    private ArrayList<Rectangle> rectangles;
    private Rectangle size;

    public Overlay(Rectangle size)
    {
        this.size = size;
        rectangles = new ArrayList<>();
    }

    public ArrayList<Rectangle> getRectangles()
    {
        return rectangles;
    }

    public void addRect(Rectangle rectangle)
    {
        rectangles.add(rectangle);
    }

    public boolean writeToFile(String filePath)
    {
        try{
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);
            for(Rectangle r: rectangles)
            {
                double x = (double) r.x/ (double) size.width;
                double y = (double)r.y/ (double) size.height;
                double w = (double)r.width/ (double) size.width;
                double h = (double)r.height/ (double) size.height;

                writer.write( x + ","
                        + y + ","
                        + w +","
                        + h + "\n");
            }
            writer.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
