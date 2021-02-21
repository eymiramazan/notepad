package darkMode;
import stockTheme.NotepadFactory;

import javax.swing.*;

// Abstract Factory Pattern - ConcreteFactory
// Creating darkMode components

public class darkModeFactory extends NotepadFactory{
    @Override
    public JFrame makeFrame(String name) {
        return new darkModeFrame(name);
    }

    @Override
    public JPanel makePanel() {
        return new darkModePanel();
    }

    @Override
    public JTextArea makeTextArea() {
        return new darkModeTextArea();
    }

    @Override
    public JMenuBar makeMenuBar() {
        return new darkModeMenuBar();
    }

    @Override
    public JMenu makeMenu(String name) {
        return new darkModeMenu(name);
    }

    @Override
    public JLabel makeLabel(String text) {
        return new darkModeLabel(text);
    }

    @Override
    public JTextField makeTextField(int column) {
        return new darkModeTextField(column);
    }

    @Override
    public JMenuItem makeMenuItem(String name) {
        return new darkModeMenuItem(name);
    }

    @Override
    public JButton makeButton(String name) {
        return new darkModeButton(name);
    }
}
