import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class App extends JFrame
{
    private Display display = new Display(this);
    private Menu menu = new Menu(this);

    private File directory = new File("");
    private ArrayList<String> fileNames = new ArrayList<>();
    private int imageIndex = 0;
    private BufferedImage image = null;

    public App()
    {
        build();
    }

    private void build()
    {
        setTitle("Labelling Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(BorderLayout.NORTH, menu);
        add(BorderLayout.CENTER, display);
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
        display.repaint();
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
        display.repaint();
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
}
