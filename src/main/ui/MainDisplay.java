package ui;

import model.Habit;
import model.HabitManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * represents the content of the window with menu on the left and BookList on the right
 * removing "implements ActionListener from MainDisplay" helps keep classes separated.
 */
public class MainDisplay extends JPanel {
    private static final String JSON_STORE = "./data/habitmanager.json";
    private HabitManagerPanel habitManagerPanel;
    private InteractionsPanel interactionsPanel;
    private HabitManager habitManager;
    private HabitConstructorFrame habitConstructorFrame;
    private StartWeekFrame startWeekFrame;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    public MainDisplay() {
        setBackground(new Color(85, 220, 201));
        setLayout(new GridLayout(1,2));
        habitManagerPanel = new HabitManagerPanel();
        interactionsPanel = new InteractionsPanel();
        habitConstructorFrame = new HabitConstructorFrame(this);
        habitConstructorFrame.setVisible(false);
        startWeekFrame = new StartWeekFrame(this);
        this.habitManager = new HabitManager("Dave's Habit Manager");
        add(interactionsPanel);
        add(habitManagerPanel);

        //Added functionality to all the buttons on the menu.
        interactionsPanel.getAddButton().addActionListener(new AddHabitListener());
        interactionsPanel.getRemoveButton().addActionListener(new RemoveListener());
        interactionsPanel.getViewWeekButton().addActionListener(new ViewWeekListener());
        interactionsPanel.getStartWeekButton().addActionListener(new StartWeekListener());
        interactionsPanel.getSaveButton().addActionListener(new AddSaveListener());
        interactionsPanel.getLoadButton().addActionListener(new AddLoadListener());
        interactionsPanel.getClearButton().addActionListener(new ClearHabitsListener());
    }

    // getters
    public HabitManager getHabitManager() {
        return this.habitManager;
    }

    public HabitManagerPanel getHabitManagerPanel() {
        return this.habitManagerPanel;
    }

    //This listener class is used by the add habit button.
    class AddHabitListener implements ActionListener {

        //EFFECTS: makes the habitConstructorFrame appear on screen.
        @Override
        public void actionPerformed(ActionEvent e) {
            habitConstructorFrame.setVisible(true);
        }
    }

    // Removes the selected habit from the list. *Template taken from Oracle*
    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Habit> temp = new ArrayList<>();

            int size = habitManagerPanel.getListModel().getSize();

            //Nobody's left, disable removeButton
            if (size == 0) {
                interactionsPanel.getRemoveButton().setEnabled(false);
                return;
            }

            //Takes index from the Pane and removes it
            int index = habitManagerPanel.getList().getSelectedIndex();
            habitManagerPanel.getListModel().remove(index);

            //Removes the habit, assuming the same position
            Habit h = habitManager.getListOfHabits().get(index);
            habitManager.removeHabit(h);

            //Select an index.
            if (index == habitManagerPanel.getListModel().getSize()) {
                //removed item in last position
                index--;
            }
            habitManagerPanel.getList().setSelectedIndex(index);
            habitManagerPanel.getList().ensureIndexIsVisible(index);
        }
    }

    //This listener class is used by the clear habits button.
    class ViewWeekListener implements ActionListener {

        //EFFECTS: clears the list of habits in pane in addition to habitmanager.
        @Override
        public void actionPerformed(ActionEvent e) {
            ScheduleFrame schedule = new ScheduleFrame(habitManager);
            schedule.setVisible(true);
        }
    }

    //This listener class is used by the save habits button.
    class StartWeekListener implements ActionListener {

        //EFFECTS: makes the habitConstructorFrame appear on screen.
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();

            if (habitManager.getListOfHabits().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Add Some Habits First!");
            } else {
                startWeekFrame.setVisible(true);
                startWeekFrame.resetDayCounter();
                startWeekFrame.nextDayOfWeek();
            }

            frame.dispose();
        }
    }

    //This listener class is used by the save habits button.
    class AddSaveListener implements ActionListener {

        //EFFECTS: Should save the current state of the habits in the program.
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(habitManager);
                jsonWriter.close();
                System.out.println("Saved " + habitManager.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }

            //It seems like removing the habit does not take it away?
        }
    }

    //This listener class is used by the save habits button.
    class AddLoadListener implements ActionListener {

        //EFFECTS: makes the habitConstructorFrame appear on screen.
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                habitManager = jsonReader.read();
                System.out.println("Loaded " + habitManager.getName() + " from " + JSON_STORE);
            } catch (IOException ioException) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }

            //Need to update the interface.
            for (Habit habit : habitManager.getListOfHabits()) {
                habitManagerPanel.getListModel().addElement(habit.getHabitName());
            }
        }
    }

    //This listener class is used by the clear habits button.
    class ClearHabitsListener implements ActionListener {

        //EFFECTS: clears the list of habits in pane in addition to habitmanager.
        @Override
        public void actionPerformed(ActionEvent e) {
            habitManager.getListOfHabits().clear();
            habitManagerPanel.getListModel().clear();
        }
    }
}
