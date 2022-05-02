package persistence;

import model.Habit;
import model.HabitManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Template taken from JsonSerializationDemo.
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            HabitManager hm = new HabitManager("My Habit Manager");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHabitManager() {
        try {
            HabitManager hm = new HabitManager("My Habit Manager");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHabitManager.json");
            writer.open();
            writer.write(hm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHabitManager.json");
            hm = reader.read();
            assertEquals("My Habit Manager", hm.getName());
            assertEquals(0, hm.getNumberOfHabits());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHabitManager() {
        try {
            HabitManager hm = new HabitManager("My Habit Manager");
            hm.addHabit(new Habit("H1", false, false, 0));
            hm.addHabit(new Habit("H2", false, true, 15));
            ArrayList<DayOfWeek> emptyWeek = new ArrayList<>();
            hm.getListOfHabits().get(1).addDaysOfWeek(DayOfWeek.MONDAY);
            hm.getListOfHabits().get(1).addDaysOfWeek(DayOfWeek.WEDNESDAY);
            hm.getListOfHabits().get(1).addDaysOfWeek(DayOfWeek.FRIDAY);
            ArrayList<DayOfWeek> filledWeek = new ArrayList<>();
            filledWeek.add(DayOfWeek.MONDAY);
            filledWeek.add(DayOfWeek.WEDNESDAY);
            filledWeek.add(DayOfWeek.FRIDAY);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHabitManager.json");
            writer.open();
            writer.write(hm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHabitManager.json");
            hm = reader.read();
            assertEquals("My Habit Manager", hm.getName());
            List<Habit> listOfHabits = hm.getListOfHabits();
            assertEquals(2, listOfHabits.size());
            checkHabit("H1", false, false, 0, emptyWeek, listOfHabits.get(0));
            checkHabit("H2", false, true, 15, filledWeek, listOfHabits.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
