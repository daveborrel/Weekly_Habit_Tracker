package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

public class HabitManagerTest {

    private HabitManager myHabits;
    private Habit fitnessHabit;
    private Habit readHabit;

    @BeforeEach
    public void setup(){
        myHabits = new HabitManager("Dave's Habit Manager");
        fitnessHabit = new Habit("Running",false,false,0);
        fitnessHabit.addDaysOfWeek(DayOfWeek.MONDAY);
        readHabit = new Habit("Reading",false,true,30);
    }

    @Test
    public void testAddHabit(){
        checkEmptyHabitList();

        myHabits.addHabit(fitnessHabit);
        assertEquals(1, myHabits.getListOfHabits().size());
        assertEquals(fitnessHabit, myHabits.getListOfHabits().get(0));
    }

    @Test
    public void testAddHabitAlreadyThere(){
        checkEmptyHabitList();

        myHabits.addHabit(fitnessHabit);
        assertEquals(1, myHabits.getListOfHabits().size());
        assertEquals(fitnessHabit, myHabits.getListOfHabits().get(0));

        myHabits.addHabit(fitnessHabit);
        assertEquals(1, myHabits.getListOfHabits().size());
        assertEquals(fitnessHabit, myHabits.getListOfHabits().get(0));
    }

    @Test
    public void testAddTwoHabits(){
        checkEmptyHabitList();

        myHabits.addHabit(fitnessHabit);
        assertEquals(1, myHabits.getListOfHabits().size());
        assertEquals(fitnessHabit, myHabits.getListOfHabits().get(0));

        myHabits.addHabit(readHabit);
        assertEquals(2, myHabits.getListOfHabits().size());
        assertEquals(fitnessHabit, myHabits.getListOfHabits().get(0));
        assertEquals(readHabit, myHabits.getListOfHabits().get(1));
    }

    @Test
    public void removeHabitAlreadyThere() {
        checkEmptyHabitList();

        myHabits.addHabit(fitnessHabit);
        assertEquals(1, myHabits.getListOfHabits().size());
        assertEquals(fitnessHabit, myHabits.getListOfHabits().get(0));

        myHabits.removeHabit(fitnessHabit);
        checkEmptyHabitList();
    }

    @Test
    public void removeHabitNotThere() {
        checkEmptyHabitList();

        myHabits.removeHabit(fitnessHabit);
        checkEmptyHabitList();
    }

    @Test
    public void removeHabitDifferentHabit() {
        checkEmptyHabitList();

        myHabits.addHabit(fitnessHabit);
        assertEquals(1, myHabits.getListOfHabits().size());
        assertEquals(fitnessHabit, myHabits.getListOfHabits().get(0));

        myHabits.removeHabit(readHabit);
        assertEquals(1, myHabits.getListOfHabits().size());
        assertEquals(fitnessHabit, myHabits.getListOfHabits().get(0));
    }

    @Test
    public void testHabitForDayCorrectDay() {
        checkEmptyHabitList();
        assertEquals(0, myHabits.habitForDay(DayOfWeek.MONDAY).size());

        myHabits.addHabit(fitnessHabit);
        assertEquals(1, myHabits.getListOfHabits().size());
        assertEquals(1, myHabits.habitForDay(DayOfWeek.MONDAY).size());
    }

    @Test
    public void testHabitForDayDifferentDay() {
        checkEmptyHabitList();
        assertEquals(0, myHabits.habitForDay(DayOfWeek.TUESDAY).size());

        myHabits.addHabit(fitnessHabit);
        assertEquals(1, myHabits.getListOfHabits().size());
        assertEquals(0, myHabits.habitForDay(DayOfWeek.TUESDAY).size());
    }

    //HELPERS

    private void checkEmptyHabitList() {
        assertEquals(0, myHabits.getListOfHabits().size());
    }
}
