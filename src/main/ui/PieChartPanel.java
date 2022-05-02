package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the drawn graphics that display the results for our project that we have made.
 */
public class PieChartPanel extends JPanel {
    private int percentComplete;
    private String durationComplete;

    public PieChartPanel(int percentComplete, String durationComplete) {
        this.percentComplete = percentComplete;
        this.durationComplete = durationComplete;
    }

    //EFFECTS: renders graphical representation of the start week frame.
    public void paint(Graphics g) {
        Graphics circle1 = (Graphics) g;
        Graphics circle2 = (Graphics) g;
        Graphics resultsText = (Graphics) g;
        Graphics durationText = (Graphics) g;

        //Outline
        ((Graphics) g).setColor(Color.BLACK);
        ((Graphics) g).fillArc(45,95,310,310,0,360);

        //This represents the incomplete tasks.
        circle2.setColor(Color.decode("#FF7F7F"));
        circle2.fillArc(50,100,300,300,0, 360);

        //This represents the completed tasks.
        double newArc = ((360.0 * percentComplete / 100.0));
        circle1.setColor(Color.decode("#90EE90"));
        circle1.fillArc(50,100,300,300,0, (int) newArc);


        resultsText.setColor(Color.BLACK);
        resultsText.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        resultsText.drawString("You completed "
                + String.valueOf(percentComplete) + "% of Your Habits this Week!", 20,50);

        durationText.setColor(Color.BLACK);
        durationText.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        durationText.drawString(durationComplete, 20,70);
    }
}
