package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the frame in which we display the results of the week for the habits that we did.
 */
public class PieChartFrame extends JFrame {
    private PieChartPanel report;
    private int percentComplete;
    private String durationCompleted;

    public PieChartFrame(int percentComplete, String durationCompleted) {
        this.percentComplete = percentComplete;
        this.durationCompleted = durationCompleted;
        report = new PieChartPanel(this.percentComplete, this.durationCompleted);
        setBackground(Color.decode("#ADD8E6"));

        add(report);
        setSize(500,450);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
