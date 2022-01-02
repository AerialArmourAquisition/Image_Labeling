import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Menu extends JPanel
{
    private App app;

    public Menu(App app)
    {
        this.app = app;
        setBackground(Color.GRAY);
        setLayout(new FlowLayout());

        JButton select_dir = new JButton("Select Dir");
        select_dir.addActionListener(e -> app.selectDirectory());
        add(select_dir);

        JButton select_img = new JButton("Select Img");
        select_img.addActionListener(e -> app.selectImage());
        add(select_img);

        JButton prev_img = new JButton("  <-  ");
        prev_img.addActionListener(e -> app.previousImage());
        add(prev_img);

        JButton next_img = new JButton("  ->  ");
        next_img.addActionListener(e -> app.nextImage());
        add(next_img);




        setVisible(true);
    }
}
