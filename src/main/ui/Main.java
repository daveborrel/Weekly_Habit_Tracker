package ui;

import javax.swing.*;

/**
 * This is the main window in which we run the program.
 */
public class Main {
    public static void main(String[] args) {

        //Creates a Splash Screen.
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon(Main.class.getResource("./resources/loading.gif")),
                        SwingConstants.CENTER));
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        new HabitGuiFrame();
    }
}
