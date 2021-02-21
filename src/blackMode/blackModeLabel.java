package blackMode;

import javax.swing.*;
import java.awt.*;

// ConcreteProduct blackModeLabel


public class blackModeLabel extends JLabel {
    public blackModeLabel(String name){
        super(name);
        setBackground(Color.BLACK);
        setForeground(Color.white);
    }
}
