package darkMode;

import javax.swing.*;
import java.awt.*;

// ConcreteProduct darkModeButton


public class darkModeButton extends JButton {
    public darkModeButton(String name){
        super(name);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.white);
    }
}
