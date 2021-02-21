package darkMode;

import javax.swing.*;
import java.awt.*;

// ConcreteProduct darkModeTextField

public class darkModeTextField extends JTextField {

    public darkModeTextField(int column){
        super(column);
        setBackground(Color.DARK_GRAY);
        setForeground(Color.white);
    }
}
