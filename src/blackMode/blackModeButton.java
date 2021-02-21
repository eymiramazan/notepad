package blackMode;

import javax.swing.*;
import java.awt.*;

// ConcreteProduct blackModeButton
public class blackModeButton extends JButton {
    public blackModeButton(String name){
        super(name);
        setBackground(Color.BLACK);
        setForeground(Color.white);
    }
}
