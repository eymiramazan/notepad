package stockTheme;

import notepad.Notepad;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class OpenFile implements FileInterface {
    private static OpenFile instance;
    public static File currentFile; //It is defined as static, because if the file was opened before,
                                    // it was saved directly on that file without asking for a place to save.

    public static Notepad notepad = Notepad.getInstance();

    public static OpenFile getInstance(){
        if (instance == null){
            instance = new OpenFile();
        }
        return instance;
    }

    @Override
    public void dosyaİslemi() {
        JFileChooser fileChooser = new JFileChooser();
        int r = fileChooser.showOpenDialog(null);
        if(r == JFileChooser.APPROVE_OPTION){
            String fileName = fileChooser.getSelectedFile().getName();
            notepad.getFrame().setTitle(fileName);
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            currentFile = file;
            try{
                String fileString = "";
                Scanner textFile = new Scanner(file);
                while(textFile.hasNextLine()){
                    fileString = fileString + textFile.nextLine() + "\n";
                }
                notepad.getTextArea().setText(fileString);
            }catch (Exception evt){
                JOptionPane.showMessageDialog(null,evt.getMessage());
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Dosya açma iptal edildi.");
        }
    }
}
