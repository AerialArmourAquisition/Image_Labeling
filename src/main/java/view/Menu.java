package view;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel
{
    private MainFrame mainFrame;

    public Menu(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        setBackground(Color.GRAY);
        setLayout(new FlowLayout());

        JButton select_dir = new JButton("Select Dir");
        select_dir.addActionListener(e -> mainFrame.selectDirectory());
        add(select_dir);

        JButton select_img = new JButton("Select Img");
        select_img.addActionListener(e -> mainFrame.selectImage());
        add(select_img);

        JButton prev_img = new JButton("  <-  ");
        prev_img.addActionListener(e -> mainFrame.previousImage());
        add(prev_img);

        JButton next_img = new JButton("  ->  ");
        next_img.addActionListener(e -> mainFrame.nextImage());
        add(next_img);

        JToggleButton draw = new JToggleButton("Draw Mode");
        draw.addActionListener(e -> mainFrame.toggleDrawMode());
        add(draw);

        setVisible(true);
    }
}
