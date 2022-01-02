package model;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Overlay
{
    private ArrayList<Rectangle> rectangles;

    public Overlay()
    {
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
                writer.write(r.x + "," + r.y + "," + r.width +"," + r.height);
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
