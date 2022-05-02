package persistence;

import model.HabitManager;
import model.Habit;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Represents a reader that reads HabitManager from JSON data stored in file.
 * Template taken from JsonSerializationDemo.
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HabitManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHabitManager(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the HabitManager from JSON object and returns it
    private HabitManager parseHabitManager(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        HabitManager hm = new HabitManager(name);
        addHabits(hm, jsonObject);
        return hm;
    }

    // MODIFIES: hm
    // EFFECTS: parses Habits from the JSON Array and adds them to HabitManager.
    private void addHabits(HabitManager hm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfHabits");
        for (Object json : jsonArray) {
            JSONObject nextHabit = (JSONObject) json;
            addHabit(hm, nextHabit);
        }
    }

    // MODIFIES: hm
    // EFFECTS: parses Habit from JSON object and adds it to HabitManager.
    private void addHabit(HabitManager hm, JSONObject jsonObject) {
        String habitName = jsonObject.getString("habitName");
        Boolean isCompleted = jsonObject.getBoolean("isCompleted");
        Boolean trackMinutes = jsonObject.getBoolean("trackMinutes");
        int duration = jsonObject.getInt("duration");

        // Takes the JSONArray (daysOfWeek) from the JSON Object
        JSONArray jsonDaysOfWeek = jsonObject.getJSONArray("daysOfWeek");

        Habit habit = new Habit(habitName, isCompleted, trackMinutes, duration);

        // Cycle through JSONArray (jsonDaysOfWeek)
        for (Object d : jsonDaysOfWeek) {
            // DayOfWeek.valueOf((String) d)) turns string into DayOfWeek.
            // Can't cast ((DayOfWeek) d) into the value of d.
            habit.addDaysOfWeek(DayOfWeek.valueOf((String) d));
        }

        hm.addHabit(habit);
    }
}
