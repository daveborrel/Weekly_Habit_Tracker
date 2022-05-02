package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.DayOfWeek;
import java.util.ArrayList;

/**
 * Represents the habit, with a name, completed status, tracked status and a list of the days of the week.
 */
public class Habit implements Writable {

    private final String habitName;
    private Boolean isCompleted;
    private final Boolean trackMinutes;
    private int duration;
    private ArrayList<DayOfWeek> daysOfWeek = new ArrayList<>();

    /*
    REQUIRES: habitName is of non 0 length.
    EFFECTS: constructs a habit with a name, completion status,
             duration (if tracked), and a list of DayOfWeek that
             is empty.
     */

    public Habit(String habitName, Boolean isCompleted, Boolean trackMinutes, int duration) {
        this.habitName = habitName;
        this.isCompleted = isCompleted;
        this.trackMinutes = trackMinutes;
        this.duration = duration;
    }

    //MODIFIES: this
    //EFFECTS: changes the status of the habit to true.
    public void completeHabit() {
        this.isCompleted = true;
    }

    //MODIFIES: this
    //EFFECTS: changes the status of the habit to false.
    public void resetHabit() {
        this.isCompleted = false;
    }

    //REQUIRES: given duration must be > 0
    //MODIFIES: this
    //EFFECTS: changes the duration of a specific habit that is tracked to given duration.
    //         however, if trackMinutes is false, do nothing.
    public void setDuration(int duration) {
        if (this.trackMinutes) {
            this.duration = duration;
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a new day of the week to the given habit.
    //         if already there, do nothing.
    public void addDaysOfWeek(DayOfWeek day) {
        if (!this.daysOfWeek.contains(day)) {
            this.daysOfWeek.add(day);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the day of the week to the given habit.
    //         if not there, do nothing.
    public void removeDaysOfWeek(DayOfWeek day) {
        this.daysOfWeek.remove(day);
    }

    //Getters
    public String getHabitName() {
        return this.habitName;
    }

    public Boolean getIsCompleted() {
        return this.isCompleted;
    }

    public Boolean getTrackMinutes() {
        return this.trackMinutes;
    }

    public int getDuration() {
        return this.duration;
    }

    public ArrayList<DayOfWeek> getDaysOfWeek() {
        return this.daysOfWeek;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("habitName", habitName);
        json.put("isCompleted", isCompleted);
        json.put("trackMinutes", trackMinutes);
        json.put("duration", duration);
        json.put("daysOfWeek", daysOfWeek);
        return json;
    }
}
