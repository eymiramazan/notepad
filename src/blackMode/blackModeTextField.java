package blackMode;

import javax.swing.*;
import java.awt.*;

// ConcreteProduct blackModeTextField

public class blackModeTextField extends JTextField {

    public blackModeTextField(int column){
        super(column);
        setBackground(Color.BLACK);
        setForeground(Color.white);
    }
}
