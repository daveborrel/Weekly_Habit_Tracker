package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the text fields for when the habit is constructed.
  */
public class HabitFields extends JPanel {
    private JTextField nameField;
    private JComboBox trackedField;
    private JTextField durationField;

    public HabitFields() {
        setLayout(new GridLayout(6,1));
        setSize(200,400);
        JLabel name = newBlueLabel("Type In The Name of the Habit.");
        nameField = new JTextField();
        JLabel tracked = newBlueLabel("Do You Want To Track This Habit?");
        trackedField = createYesNoOptions();
        JLabel duration = newBlueLabel("How Many Minutes Each Day Will You Do This Habit?");
        durationField = new JTextField();

        addComponentsToFrame(name, tracked, duration);
    }

    //MODIFIES: this
    //EFFECTS: adds the buttons and labels
    private void addComponentsToFrame(JLabel name, JLabel tracked, JLabel duration) {
        add(name);
        add(nameField);
        add(tracked);
        add(trackedField);
        add(duration);
        add(durationField);
    }

    //EFFECTS: returns a new blue label with light blue string
    private JLabel newBlueLabel(String name) {
        JLabel label = new JLabel(name);
        label.setOpaque(true);
        label.setBackground(Color.decode("#ADD8E6"));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    //EFFECTS: Creates a drop-down selection options with yes or no
    public JComboBox<String> createYesNoOptions() {
        String[] options = { "Yes", "No" };
        JComboBox<String> dropdown = new JComboBox<>(options);
        return dropdown;
    }

    // getters
    public String getHabitName() {
        return nameField.getText();
    }

    public boolean getTracked() {
        int index = trackedField.getSelectedIndex();
        boolean isTrue = index == 0;

        return isTrue;
    }

    public int getDuration() {
        try {
            int number = Integer.parseInt(durationField.getText());
            return number;
        } catch (NumberFormatException e) {
            durationField.setText("");
            return 0;
        }
    }

    public JTextField getNameField() {
        return this.nameField;
    }

    public JTextField getDurationField() {
        return this.durationField;
    }
}
