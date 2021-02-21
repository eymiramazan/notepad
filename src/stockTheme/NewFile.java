package stockTheme;

import notepad.Notepad;

public class NewFile implements FileInterface {

    private static NewFile instance;
    public static Notepad notepad = Notepad.getInstance();

    public static NewFile getInstance(){
        if (instance == null){
            instance = new NewFile();
        }
        return instance;
    }
    @Override
    public void dosyaİslemi() {
        notepad.getFrame().setTitle("Yeni Metin Belgesi");
        notepad.getTextArea().setText(" ");
        OpenFile.currentFile = null;
    }
}
