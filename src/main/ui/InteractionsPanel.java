package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the buttons that we can use to interact with the Habit Manager.
 */
public class InteractionsPanel extends JPanel {
    private final JButton addButton;
    private final JButton removeButton;
    private final JButton viewWeekButton;
    private final JButton startWeekButton;
    private final JButton saveButton;
    private final JButton loadButton;
    private final JButton clearButton;

    public InteractionsPanel() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        addButton = newButton("New Habit");
        removeButton = newButton("Remove Habit");
        viewWeekButton = newButton("View Weekly Schedule");
        startWeekButton = newButton("Start Week");
        saveButton = newButton("Save Habits");
        loadButton = newButton("Load Habits");
        clearButton = newButton("Clear Habits");

        // Combined the buttons into one panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(7,1));
        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(viewWeekButton);
        buttonsPanel.add(startWeekButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(clearButton);
        add(buttonsPanel);
    }

    //EFFECTS: creates a new button with given name.
    private JButton newButton(String name) {
        JButton button = new JButton(name);
        button.setBackground(new Color(255, 255, 255));
        button.setSize(50, 50);
        return button;
    }

    // getters
    public JButton getAddButton() {
        return this.addButton;
    }

    public JButton getRemoveButton() {
        return this.removeButton;
    }

    public JButton getViewWeekButton() {
        return this.viewWeekButton;
    }

    public JButton getStartWeekButton() {
        return this.startWeekButton;
    }

    public JButton getSaveButton() {
        return this.saveButton;
    }

    public JButton getLoadButton() {
        return this.loadButton;
    }

    public JButton getClearButton() {
        return this.clearButton;
    }
}
