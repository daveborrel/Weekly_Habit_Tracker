package ui;

import model.Habit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.DayOfWeek;

/**
 * Represents the week that you're tracking your habits for. dayCounter represents the enumeration for the DayOfWeek.
 * index represents the value we work with while cycling through the habits for that given day. DurationHabits
 * is the value that we print out once the week finishes.
 */
public class StartWeekFrame extends JFrame implements ActionListener {
    private MainDisplay mainDisplay;
    private JLabel dayLabel;
    private JLabel habitLabel;
    private JPanel inputPanel;
    private JButton yesButton;
    private JButton noButton;
    private int dayCounter = 1;
    private int index = 0;
    private float totalHabits = 0;
    private int completedHabits = 0;
    private int durationAllHabits = 0;
    private int round = 0;

    //EFFECTS: constructs a new start week frame.
    public StartWeekFrame(MainDisplay mainDisplay) {

        this.mainDisplay = mainDisplay;
        setLayout(new GridLayout(3,1));
        dayLabel = createBlueLabel();
        habitLabel = createBlueLabel();
        inputPanel = new JPanel();
        yesButton = new JButton("Yes");
        yesButton.addActionListener(this);
        noButton = new JButton("No");
        noButton.addActionListener(this);
        inputPanel.setLayout(new GridLayout(1,2));
        inputPanel.add(yesButton);
        inputPanel.add(noButton);

        setSize(500,400);
        setTitle("Start Week!");
        setResizable(false);
        setVisible(false);

        addComponentsToFrame();
        setLocationRelativeTo(null);
    }

    private JLabel createBlueLabel() {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.decode("#ADD8E6"));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    //EFFECTS: adds the components for StartWeekFrame
    private void addComponentsToFrame() {
        add(dayLabel);
        add(habitLabel);
        add(inputPanel);
    }

    //EFFECTS: resets the dayCounter back to 1.
    public void resetDayCounter() {
        this.dayCounter = 1;
    }

    //MODIFIES: this
    //EFFECTS: Looks at the given day of week and cycles through the habits. We resent the index back to 0.
    protected void nextDayOfWeek() {

        if (dayCounter == 8) {
            habitLabel.setText("Week Completed!");
        }

        index = 0;

        DayOfWeek c = DayOfWeek.of(dayCounter);
        dayLabel.setText("Today is " + c);

        // If the day happens to be empty go next.
        try {
            cycleThroughHabits(c, index);
        } catch (Exception e) {
            dayCounter++;
            nextDayOfWeek(); //This will run 7 times when it reaches sunday.
        }

    }

    //EFFECTS: Returns the duration of a habit if it is tracked. 0 if not tracked.
    private int trackDuration(DayOfWeek c, int index) {
        Habit habit = mainDisplay.getHabitManager().habitForDay(c).get(index);
        if (habit.getTrackMinutes()) {
            return habit.getDuration();
        } else {
            return 0;
        }
    }

    //MODIFIES: this
    //EFFECTS: Displays the current habit for the given DayOfWeek at the given index on the habitLabel.
    private void cycleThroughHabits(DayOfWeek c, int index) {
        try {
            Habit habit = mainDisplay.getHabitManager().habitForDay(c).get(index); //We need to catch this.
            habitLabel.setText("Did you complete " + habit.getHabitName() + "?");
        } catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }

    //MODIFIES: this
    //EFFECTS: Button presses will modify the dayCounter, index, completedHabits, or totalHabits.
    //         If yesButton is pressed, totalHabits++ and habitsCompleted++. nextHabits(day) with same day is called.
    //         If noButton is pressed, totalHabits++. nextHabits(day) with same day is called.
    @Override
    public void actionPerformed(ActionEvent e) {
        checkValidDayCounter();
        DayOfWeek day = DayOfWeek.of(dayCounter);

        if (e.getSource() == yesButton) {
            completedHabits++;
            totalHabits++;
            durationAllHabits += trackDuration(day, index);
            nextHabit(day);
        } else if (e.getSource() == noButton) {
            totalHabits++;
            nextHabit(day);
        }
    }

    //MODIFIES: this
    //EFFECTS: Sets the dayCounter to 1 and hides the window if dayCounter is == 8.
    private void checkValidDayCounter() {
        if (dayCounter == 8) {
            dayCounter = 1;
            setVisible(false);
        }
    }

    //MODIFIES: this
    //EFFECTS: Tries call cycleThroughHabits with the same day and increased index. If the exception is thrown,
    //         then increase dayCounter by 1. If day enumeration is beyond range, print results.
    //         Otherwise, go to the next day of the week with the updated dayCounter.
    private void nextHabit(DayOfWeek day) {
        try {
            index++;
            cycleThroughHabits(day, index); //This is the next day
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            dayCounter++;

            try {
                nextDayOfWeek();
            } catch (DateTimeException e) {
                habitLabel.setText("Press Either Button to Hide Window.");
                float percent = ((float) completedHabits / totalHabits) * 100;
                int roundedPercent = Math.round(percent);
                updateUI(roundedPercent);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: returns the results screen once updates the UI text once the program completes.
    private void updateUI(int percent) {

        PieChartFrame pieChartFrame = new PieChartFrame(percent, durationString());

        dayLabel.setText("Week is Completed!");
        habitLabel.setText("Press Either Button to Hide Window.");
    }

    //EFFECTS: returns a string with the total duration of completed habits.
    private String durationString() {
        round++;
        return "For tracked habits, you did " + durationAllHabits + " minutes total! # of Weeks: " + round;
    }
}
