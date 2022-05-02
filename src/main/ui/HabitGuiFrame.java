package ui;

import model.EventLog;
import model.exceptions.LogException;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main GUI object that represents the Habit Manager program.
 */
public class HabitGuiFrame extends JFrame {
    private MainDisplay mainDisplay;

    public HabitGuiFrame() {
        mainDisplay = new MainDisplay(); // the panel

        add(mainDisplay);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            TerminalPrinter lp = new TerminalPrinter();

            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    System.out.println(lp.printLog(EventLog.getInstance()));
                } catch (LogException e) {
                    e.printStackTrace();
                }
                //THEN you can exit the program
                System.exit(0);
            }
        });


        setSize(new Dimension(800,500));
        setTitle("Habit Manager Application");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
