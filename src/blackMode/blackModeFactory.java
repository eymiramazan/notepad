package blackMode;
import stockTheme.NotepadFactory;

import javax.swing.*;

// Abstract Factory Pattern - ConcreteFactory
// Creating blackMode components

public class blackModeFactory extends NotepadFactory{
    @Override
    public JFrame makeFrame(String name) {
        return new blackModeFrame(name);
    }

    @Override
    public JPanel makePanel() {
        return new blackModePanel();
    }

    @Override
    public JTextArea makeTextArea() {
        return new blackModeTextArea();
    }

    @Override
    public JMenuBar makeMenuBar() {
        return new blackModeMenuBar();
    }

    @Override
    public JMenu makeMenu(String name) {
        return new blackModeMenu(name);
    }

    @Override
    public JLabel makeLabel(String text) {
        return new blackModeLabel(text);
    }

    @Override
    public JTextField makeTextField(int column) {
        return new blackModeTextField(column);
    }

    @Override
    public JMenuItem makeMenuItem(String name) {
        return new blackModeMenuItem(name);
    }

    @Override
    public JButton makeButton(String name) {
        return new blackModeButton(name);
    }
}
