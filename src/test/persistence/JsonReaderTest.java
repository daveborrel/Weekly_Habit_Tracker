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

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HabitManager hm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHabitManager() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyHabitManager.json");
        try {
            HabitManager hm = reader.read();
            assertEquals("My Habit Manager", hm.getName());
            assertEquals(0, hm.getNumberOfHabits());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHabitManager() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralHabitManager.json");
        try {
            HabitManager hm = reader.read();
            assertEquals("My Habit Manager", hm.getName());
            ArrayList<DayOfWeek> emptyWeek = new ArrayList<>();
            ArrayList<DayOfWeek> filledWeek = new ArrayList<>();
            filledWeek.add(DayOfWeek.MONDAY);
            filledWeek.add(DayOfWeek.WEDNESDAY);
            filledWeek.add(DayOfWeek.FRIDAY);
            List<Habit> listOfHabits = hm.getListOfHabits();
            assertEquals(2, listOfHabits.size());
            checkHabit("H1", false, false, 0, emptyWeek, listOfHabits.get(0));
            checkHabit("H2", false, true, 15, filledWeek, listOfHabits.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
