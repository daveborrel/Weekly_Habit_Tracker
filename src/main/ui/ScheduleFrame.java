package ui;

import model.HabitManager;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;

/**
* represents the Schedule that gets printed out for the week.
*/
public class ScheduleFrame extends JFrame {
    private JTextPane weekOutput;
    private HabitManager habitManager;

    public ScheduleFrame(HabitManager habitManager) {
        super("Schedule for the Week");
        this.habitManager = habitManager;
        weekOutput = new JTextPane();
        weekOutput.setFont(new Font("Arial", Font.PLAIN, 24));
        weekOutput.setText("Your Habits for the Week Are:\n");
        JPanel schedulePanel = new JPanel();
        schedulePanel.add(weekOutput);
        schedulePanel.setBackground(Color.decode("#ADD8E6"));
        add(schedulePanel);

        setSize(new Dimension(500,300));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        printWeek();
    }

    //EFFECTS: prints the days of the week and the habits that you need to do each day.
    private void printWeek() {

        for (DayOfWeek c : DayOfWeek.values()) {
            weekOutput.setText(weekOutput.getText() + c + printDayAndHabit(c) + "\n");
        }
    }

    //MODIFIES: this
    //EFFECTS: prints the habits for a given day of the week.
    private String printDayAndHabit(DayOfWeek c) {
        String habits = ": ";

        if (habitManager.habitForDay(c).size() == 0) {
            return ": No Habits";
        }

        for (int i = 0; i < habitManager.habitForDay(c).size(); i++) {
            habits = habits + habitManager.habitForDay(c).get(i).getHabitName() + ", ";
        }

        return habits;
    }
}
