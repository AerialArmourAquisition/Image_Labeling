import javax.swing.*;
import java.io.File;

public class App extends JFrame
{
    public App()
    {
        File f = new File("");
        build(f.getPath());
    }

    public App(String directoryPath)
    {
        build(directoryPath);
    }

    private void build(String directoryPath)
    {
        setTitle("Labeling Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
