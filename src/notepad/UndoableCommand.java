package notepad;

import javax.swing.event.UndoableEditEvent;

//Queues the last transactions made in text area
//It enables undo in this order when executed in undo command.
public interface UndoableCommand extends Command {
    void delete(UndoableEditEvent editEvent);
}
