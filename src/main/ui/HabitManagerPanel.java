package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the panel with the Habits that we are keeping track of along with a graphic.
 */
public class HabitManagerPanel extends JPanel
                            implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String TITLE_TXT = "Your Habits to Manage for 2022!";
    private JScrollPane listScrollPane;
    private ImageIcon image;

    public HabitManagerPanel() {
        setOpaque(true);
        setBackground(Color.decode("#ADD8E6"));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JLabel heading = new JLabel(TITLE_TXT);
        JLabel bottom = new JLabel();
        JLabel trademark = new JLabel("\"Discipline is the bridge between goals and accomplishment.\"");
        listModel = new DefaultListModel();

        addImage(bottom);

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        listScrollPane = new JScrollPane(list);

        add(heading);
        add(listScrollPane);
        add(trademark);
        add(bottom);
    }

    //MODIFIES: this
    //EFFECTS: Adds image to HabitManagerPanel
    private void addImage(JLabel bottom) {
        try {
            image = new ImageIcon(getClass().getResource("./resources/mountain.jpeg"));
            bottom.setIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //getters
    public DefaultListModel getListModel() {
        return this.listModel;
    }

    public JList getList() {
        return this.list;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }
}
