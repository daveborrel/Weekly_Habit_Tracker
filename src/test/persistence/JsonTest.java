package persistence;

import model.Habit;

import java.time.DayOfWeek;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Template taken from JsonSerializationDemo.
public class JsonTest {
    protected void checkHabit(String name, Boolean isCompleted, Boolean trackMinutes,
                              int duration, ArrayList<DayOfWeek> daysOfWeek, Habit habit) {
        assertEquals(name, habit.getHabitName());
        assertEquals(isCompleted, habit.getIsCompleted());
        assertEquals(trackMinutes, habit.getTrackMinutes());
        assertEquals(duration, habit.getDuration());
        assertEquals(daysOfWeek, habit.getDaysOfWeek());
    }
}
