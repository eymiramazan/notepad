package darkMode;

import javax.swing.*;
import java.awt.*;

// ConcreteProduct darkModeLabel

public class darkModeLabel extends JLabel {
    public darkModeLabel(String name){
        super(name);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.white);
    }
}
