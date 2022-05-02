package ui;

import model.Habit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;

/**
* Represents the new popup window when we press the add button in the original file.
 * */
public class HabitConstructorFrame extends JFrame implements ActionListener {
    private MainDisplay mainDisplay; // Needed to pass the main display into a parameter in the constructor frame.
    private HabitFields habitFields;
    private JPanel daysCheckBox;
    private Habit constructedHabit;
    private ArrayList<JCheckBox> allDays;

    public HabitConstructorFrame(MainDisplay mainDisplay) {
        this.mainDisplay = mainDisplay;
        allDays = new ArrayList<>();
        habitFields = new HabitFields();
        daysCheckBox = createDaysCheckBox();
        JPanel fieldsAndDaysCheckBox = new JPanel();
        JButton createHabit = createHabitButton();
        createHabit.setMaximumSize(new Dimension(100,100));

        fieldsAndDaysCheckBox.add(habitFields);
        fieldsAndDaysCheckBox.add(daysCheckBox);
        fieldsAndDaysCheckBox.add(createHabit);
        fieldsAndDaysCheckBox.setLayout(new GridLayout(3,1));

        add(fieldsAndDaysCheckBox);
        setSize(400,400);
        setTitle("Make a New Habit!");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //EFFECTS: Constructs that habit button and adds an actionListener.
    private JButton createHabitButton() {
        JButton button = new JButton("Create Habit");
        button.addActionListener(this);
        return button;
    }

    //EFFECTS: returns a panel with 7 checkboxes each having a DayOfWeek.
    public JPanel createDaysCheckBox() {
        JPanel tempCheckBox = new JPanel();
        tempCheckBox.setLayout(new GridLayout(2,4));

        addCheckBox("Monday");
        addCheckBox("Tuesday");
        addCheckBox("Wednesday");
        addCheckBox("Thursday");
        addCheckBox("Friday");
        addCheckBox("Saturday");
        addCheckBox("Sunday");

        for (JCheckBox day : allDays) {
            tempCheckBox.add(day);
        }

        tempCheckBox.setSize(100,100);
        tempCheckBox.setBackground(Color.decode("#ADD8E6"));
        return tempCheckBox;
    }

    //EFFECTS: constructs a new day button, adds it to ArrayList, and adds it to panel.
    private void addCheckBox(String dayOfWeek) {
        JCheckBox day = new JCheckBox(dayOfWeek);
        allDays.add(day);
    }

    // Getters
    public Habit getConstructedHabit() {
        return this.constructedHabit;
    }

    public JPanel getDaysCheckBox() {
        return this.daysCheckBox;
    }

    //EFFECTS: cycles through the checked boxes, adds DayOfWeek enumeration using counter integer.
    public void addDaysOfWeek(Habit habit) {
        int counter = 1;
        for (JCheckBox day : allDays) {
            if (day.isSelected()) {
                habit.addDaysOfWeek(DayOfWeek.of(counter));
            }

            counter++;
        }


    }

    // Pressing the create habit button will add habit to the display and close the window.
    @Override
    public void actionPerformed(ActionEvent e) {
        //Constructs Habit
        String name = this.habitFields.getHabitName();
        boolean isTracked = this.habitFields.getTracked();
        int duration = this.habitFields.getDuration();
        this.constructedHabit = new Habit(name,false,isTracked,duration);
        addDaysOfWeek(this.constructedHabit);
        this.mainDisplay.getHabitManager().addHabit(this.constructedHabit);
        this.mainDisplay.getHabitManagerPanel().getListModel().addElement(this.constructedHabit.getHabitName());

        //Clear Fields
        habitFields.getNameField().setText("");
        habitFields.getDurationField().setText("");

        //Resets the Checkboxes
        for (JCheckBox day : allDays) {
            day.setSelected(false);
        }

        setVisible(false);
    }

}
