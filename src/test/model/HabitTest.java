package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

class HabitTest {

    private Habit fitnessHabit;
    private Habit meditationHabit;

    @BeforeEach
    public void setup() {
        fitnessHabit = new Habit("Running", false, false, 0);
        meditationHabit = new Habit("Meditation", false, true, 15);
    }

    @Test
    public void testConstructor() {
        assertEquals("Running", fitnessHabit.getHabitName());
        assertFalse(fitnessHabit.getIsCompleted());
        assertFalse(fitnessHabit.getTrackMinutes());
        assertEquals(0, fitnessHabit.getDuration());
        assertEquals(0, fitnessHabit.getDaysOfWeek().size());
    }

    @Test
    public void testCompleteHabit() {
        assertFalse(fitnessHabit.getIsCompleted());
        fitnessHabit.completeHabit();
        assertTrue(fitnessHabit.getIsCompleted());
    }

    @Test
    public void testResetHabitFalse() {
        assertFalse(fitnessHabit.getIsCompleted());
        fitnessHabit.resetHabit();
        assertFalse(fitnessHabit.getIsCompleted());
    }

    @Test
    public void testResetHabitTrue() {
        fitnessHabit.completeHabit();
        assertTrue(fitnessHabit.getIsCompleted());
        fitnessHabit.resetHabit();
        assertFalse(fitnessHabit.getIsCompleted());
    }

    @Test
    public void testSetDurationTracked() {
        assertEquals(15, meditationHabit.getDuration());
        meditationHabit.setDuration(30);
        assertEquals(30, meditationHabit.getDuration());
    }

    @Test
    public void testSetDurationNotTracked() {
        assertEquals(0, fitnessHabit.getDuration());
        fitnessHabit.setDuration(30); //this might be the case from last time.
        assertEquals(0, fitnessHabit.getDuration());
    }

    @Test
    public void testAddDaysOfWeekNotThere() {
        assertEquals(0, fitnessHabit.getDaysOfWeek().size());

        // I think can refactor this?
        fitnessHabit.addDaysOfWeek(DayOfWeek.MONDAY);
        assertEquals(1, fitnessHabit.getDaysOfWeek().size());
        assertEquals(DayOfWeek.MONDAY, fitnessHabit.getDaysOfWeek().get(0));
    }

    @Test
    public void testAddDaysOfWeekAlreadyThere() {
        assertEquals(0, fitnessHabit.getDaysOfWeek().size());

        fitnessHabit.addDaysOfWeek(DayOfWeek.MONDAY);
        assertEquals(1, fitnessHabit.getDaysOfWeek().size());
        assertEquals(DayOfWeek.MONDAY, fitnessHabit.getDaysOfWeek().get(0));

        fitnessHabit.addDaysOfWeek(DayOfWeek.MONDAY);
        assertEquals(1, fitnessHabit.getDaysOfWeek().size());
        assertEquals(DayOfWeek.MONDAY, fitnessHabit.getDaysOfWeek().get(0));
    }

    @Test
    public void testRemoveDaysOfWeekNotThere(){
        assertEquals(0, fitnessHabit.getDaysOfWeek().size());

        fitnessHabit.removeDaysOfWeek(DayOfWeek.MONDAY);
        assertEquals(0, fitnessHabit.getDaysOfWeek().size());
    }

    @Test
    public void testRemoveDaysOfWeekThere(){
        assertEquals(0, fitnessHabit.getDaysOfWeek().size());

        fitnessHabit.addDaysOfWeek(DayOfWeek.MONDAY);
        assertEquals(1, fitnessHabit.getDaysOfWeek().size());
        assertEquals(DayOfWeek.MONDAY, fitnessHabit.getDaysOfWeek().get(0));
    }

}