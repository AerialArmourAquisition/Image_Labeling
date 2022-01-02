package view;

import model.Overlay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame
{
    private Canvas canvas = new Canvas(this);
    private Menu menu = new Menu(this);

    private File directory = new File("");
    private ArrayList<String> fileNames = new ArrayList<>();
    private BufferedImage image = null;
    private int imageIndex = 0;
    private boolean drawMode = false;
    private Overlay overlay;

    public MainFrame()
    {
        build();
    }

    private void build()
    {
        setTitle("Labelling Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(BorderLayout.NORTH, menu);
        add(BorderLayout.CENTER, canvas);
        setVisible(true);
    }

    public void selectDirectory()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(this);
        directory = fileChooser.getSelectedFile();
        for(String fileName: directory.list())
        {
            if(fileName.endsWith(".png") ||
                    fileName.endsWith(".jpeg") ||
                    fileName.endsWith(".jpg"))
            {
                fileNames.add(fileName);
            }
        }
        loadImage();
    }

    public void selectImage()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showOpenDialog(this);
        Rectangle img_size = null;
        try
        {
            String filePath = fileChooser.getSelectedFile().getPath();
            image = ImageIO.read(new File(filePath));
            img_size = new Rectangle(image.getMinX(), image.getMinY(), image.getWidth(), image.getHeight());
        }
        catch (IOException e)
        {
            System.out.println("Image not successfully loaded");
            e.printStackTrace();
            image = null;
        }
        overlay = new Overlay(img_size);
        canvas.setOverlay(overlay);
        canvas.resetOffset();
        canvas.repaint();
    }

    public void loadImage()
    {
        Rectangle img_size = null;
        try
        {
            String filePath = directory.getPath();
            String fileName = fileNames.get(imageIndex);
            image = ImageIO.read(new File(filePath + "/" + fileName));
            img_size = new Rectangle(0, 0, image.getWidth(), image.getHeight());
        }
        catch (IOException e)
        {
            System.out.println("Image not successfully loaded");
            e.printStackTrace();
            image = null;
        }
        overlay = new Overlay(img_size);
        canvas.setOverlay(overlay);
        canvas.resetOffset();
        canvas.repaint();
    }

    public java.awt.image.BufferedImage getImage()
    {
        return image;
    }

    public void nextImage()
    {
        if(imageIndex+1 < fileNames.size())
            imageIndex++;
        loadImage();
    }

    public void previousImage()
    {
        if(imageIndex-1 >= 0)
            imageIndex--;
        loadImage();
    }

    public void save()
    {
        String fileName = fileNames.get(imageIndex);
        int endIndex = fileName.length();
        for(int i = fileName.length()-1; i >= 0; i--)
        {
            if(fileName.charAt(i) == '.')
            {
                endIndex = i;
                break;
            }
        }
        fileName = fileName.substring(0, endIndex);
        overlay.writeToFile(directory + "/" + fileName + ".lbl");
        nextImage();
    }

    public ArrayList<Rectangle> getOverlay()
    {
        return overlay.getRectangles();
    }
}
