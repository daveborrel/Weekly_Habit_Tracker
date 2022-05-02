package ui;

import model.Event;
import model.EventLog;
import model.exceptions.LogException;

public class TerminalPrinter {

    public boolean printLog(EventLog el) throws LogException {
        for (Event next : el) {
            System.out.println(next.toString());
        }
        return false;
    }
}
