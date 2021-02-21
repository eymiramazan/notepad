package darkMode;

import javax.swing.*;
import java.awt.*;

// ConcreteProduct darkModeMenu

public class darkModeMenu extends JMenu {
    public darkModeMenu(String name){
        super(name);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.white);
    }
}
