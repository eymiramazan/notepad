package darkMode;

import javax.swing.*;
import java.awt.*;

// ConcreteProduct darkModeMenuItem

public class darkModeMenuItem extends JMenuItem {
    public darkModeMenuItem(String name){
        super(name);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.white);
    }
}
