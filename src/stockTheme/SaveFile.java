package stockTheme;

import notepad.Notepad;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile implements FileInterface {
    private static SaveFile instance;
    File currentFile = OpenFile.currentFile;
    public static Notepad notepad = Notepad.getInstance();

    public static SaveFile getInstance(){
        if (instance == null){
            instance = new SaveFile();
        }
        return instance;
    }

    @Override
    public void dosyaİslemi() {
        if(OpenFile.currentFile != null){
            try {
                File file = new File(currentFile.getAbsolutePath());
                OpenFile.currentFile = file;
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter w = new BufferedWriter(fileWriter);
                w.write(notepad.getTextArea().getText());
                w.flush();
                w.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "File Not Found!");
            }
        }
        else {
            JFileChooser j = new JFileChooser();
            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File file = new File(j.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter w = new BufferedWriter(fileWriter);
                    w.write(notepad.getTextArea().getText());
                    w.flush();
                    w.close();
                    OpenFile.currentFile = file;
                    notepad.getFrame().setTitle(j.getSelectedFile().getName());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "File Not Found!");
                }
            }
        }
    }
}
