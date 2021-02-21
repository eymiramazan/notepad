package notepad;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

public class UndoCommand implements UndoableCommand {

    //Undomanager defined for undo in swing textarea
    public UndoManager manager;
    //The event of pressing the undo button
    UndoableEditEvent event;
    //Constructor
    public UndoCommand(UndoManager manager, UndoableEditEvent event){
        this.manager = manager;
        this.event = event;
    }

    //This function works when the undo button is pressed
    //The function starts to retrieve items that can be retrieved with UndoManager at every button click.
    //If not, an error message is printed on the screen.
    @Override
    public void execute() {
        if(manager.canUndo()){
            manager.undo();
        }
        else{
            JOptionPane.showMessageDialog(null, "Geri alınacak öğe yok..");
        }
    }

    //The changes made in textarea are ordered in the manager object created from the UndoManager class.
    @Override
    public void delete(UndoableEditEvent editEvent) {
        manager.addEdit(editEvent.getEdit());
    }
}
