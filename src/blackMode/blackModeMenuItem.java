package blackMode;

import javax.swing.*;
import java.awt.*;

// JMenuItem ConcreteProduct

public class blackModeMenuItem extends JMenuItem {
    public blackModeMenuItem(String name){
        super(name);
        setBackground(Color.BLACK);
        setForeground(Color.white);
    }
}
