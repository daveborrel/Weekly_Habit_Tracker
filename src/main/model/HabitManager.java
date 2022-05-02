package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.DayOfWeek;
import java.util.ArrayList;

/**
 * Represents the class that manages the habits that you're keeping track of.
 */
public class HabitManager implements Writable {

    private final String name;
    private ArrayList<Habit> listOfHabits;

    //EFFECTS: constructs an empty list of habits.
    public HabitManager(String name) {
        this.name = name;
        this.listOfHabits = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a new Habit to the list of habits.
    public void addHabit(Habit habit) {
        if (!this.listOfHabits.contains(habit)) {
            this.listOfHabits.add(habit);
            EventLog.getInstance().logEvent(new Event("Added " + habit.getHabitName() + " to HabitManager"));
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the specified Habit from the total list of habits.
    public void removeHabit(Habit habit) {
        this.listOfHabits.remove(habit);
        EventLog.getInstance().logEvent(new Event("Removed " + habit.getHabitName() + " from HabitManager"));
    }

    //EFFECTS: returns the list of habits that are done in a given DayOfWeek
    public ArrayList<Habit> habitForDay(DayOfWeek day) {
        ArrayList<Habit> temp = new ArrayList<>();

        for (int i = 0; i < this.listOfHabits.size(); i++) {
            if (this.listOfHabits.get(i).getDaysOfWeek().contains(day)) {
                temp.add(this.listOfHabits.get(i));
            }
        }
        return temp;
    }

    //getters
    public ArrayList<Habit> getListOfHabits() {
        return this.listOfHabits; //stub
    }

    public String getName() {
        return this.name;
    }

    public int getNumberOfHabits() {
        return this.listOfHabits.size();
    }

    @Override
    // EFFECTS: converts the given Habit into a JSON object.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("listOfHabits", listOfHabitsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array.
    private JSONArray listOfHabitsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Habit h : listOfHabits) {
            jsonArray.put(h.toJson());
        }

        return jsonArray;
    }
}
