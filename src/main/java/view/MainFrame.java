package view;

import model.Overlay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MainFrame extends JFrame
{
    private Canvas canvas = new Canvas(this);
    private Menu menu = new Menu(this);

    private File directory = new File("");
    private ArrayList<String> fileNames = new ArrayList<>();
    private BufferedImage image = null;
    private int imageIndex = 0;
    private boolean drawMode = false;
    private Overlay overlay = new Overlay();

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
        Collections.addAll(fileNames, directory.list());
        loadImage();
    }

    public void selectImage()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showOpenDialog(this);
        try
        {
            String filePath = fileChooser.getSelectedFile().getPath();
            image = ImageIO.read(new File(filePath));
        }
        catch (IOException e)
        {
            System.out.println("Image not successfully loaded");
            e.printStackTrace();
            image = null;
        }
        overlay = new Overlay();
        canvas.setOverlay(overlay);
        canvas.repaint();
    }

    public void loadImage()
    {
        try
        {
            String filePath = directory.getPath();
            String fileName = fileNames.get(imageIndex);
            image = ImageIO.read(new File(filePath + "/" + fileName));
        }
        catch (IOException e)
        {
            System.out.println("Image not successfully loaded");
            e.printStackTrace();
            image = null;
        }
        overlay = new Overlay();
        canvas.setOverlay(overlay);
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

    public void toggleDrawMode()
    {
        drawMode  = !drawMode;
    }

    public ArrayList<Rectangle> getOverlay()
    {
        return overlay.getRectangles();
    }
}
