package notepad;

import blackMode.blackModeFactory;
import stockTheme.NotepadFactory;
import darkMode.darkModeFactory;

// The NotepadCreator method, which takes as the factory argument of the Notepad class,
// which is the Client element in the Abstract Factory Pattern, is used here.
// A theme is called according to the argument to the main method.

// Swing elements unique to each theme are defined under the package that belongs to the theme.

public class NotepadAbstractFactory {
    public static void main(String[] args){
        Notepad notepad = Notepad.getInstance();
        NotepadFactory factory = null;

        if (args.length > 0) {
            if ("Dark Mode".equals(args[0])) {
                factory = new darkModeFactory();
            } else if ("Black Mode".equals(args[0])) {
                factory = new blackModeFactory();
            } else if ("Stock Theme".equals(args[0])) {
                factory = new NotepadFactory();
            }
        }
        if (factory == null) {
            factory = new NotepadFactory();
        }
        notepad.NotepadCreator(factory);
    }
}
