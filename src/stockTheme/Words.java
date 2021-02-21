package stockTheme;

import notepad.Notepad;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Words {
    // Words inside of words.txt
    private static HashSet<String> wordsTxtHashSet;
    // TextArea word
    private static HashSet<String> textAreaWordHashSet;
    // temp hashset for control
    private static HashSet<String> tempHash;
    // True words
    private static HashSet<String> trueWordsHashSet;

    Notepad notepad = Notepad.getInstance();

    private Words(){
        wordsTxtHashSet = new HashSet<>();
        readWords();
    }

    private static Words instance;
    public static Words getInstance(){
        if(instance==null){
            instance = new Words();
        }
        return instance;
    }

    private void readWords(){
        try {
            Scanner textFile = new Scanner(new File("words.txt"));
            while (textFile.hasNext()) {
                wordsTxtHashSet.add(textFile.next().trim());
            }
                textFile.close();
        } catch (FileNotFoundException e) {
                System.out.println("Words File Not Found!");
            }

    }

    public void controlButton(){
        //Words in text area are parsed here
        String regex = "[!._,;-':@?\n ]";
        StringTokenizer str = new StringTokenizer(notepad.getTextArea().getText(), regex);
        textAreaWordHashSet = new HashSet<String>();
        tempHash = new HashSet<>();
        while(str.hasMoreTokens()){
            textAreaWordHashSet.add(str.nextToken().toLowerCase());
        }

        trueWordsHashSet = new HashSet<>(textAreaWordHashSet);
        trueWordsHashSet.retainAll(wordsTxtHashSet);
        textAreaWordHashSet.removeAll(trueWordsHashSet);

        // Iterator Pattern
        HashSetIterator iterator = new HashSetIterator(textAreaWordHashSet);
            // Numerical strings are correct too
        while(iterator.hasNext()){
            String current = (String) iterator.next();
            if(isNumeric(current)){
                trueWordsHashSet.add(current);
                textAreaWordHashSet.remove(current);
            }
        }

        tempHash.addAll(textAreaWordHashSet);

        // Single Transposition words
        ArrayList singleTranspositionTexts = new ArrayList();
        ArrayList singleTransTrueVersion = new ArrayList();

        for(String falseText : textAreaWordHashSet){
            StringBuilder temp = new StringBuilder(falseText);
            for(int i=0; i < falseText.length()-1; i++){
                char tempChar = temp.charAt(i);
                temp.setCharAt(i,temp.charAt(i+1));
                temp.setCharAt(i+1,tempChar);

                if(wordsTxtHashSet.contains(temp.toString())) {
                    trueWordsHashSet.add(temp.toString());
                    singleTransTrueVersion.add(temp.toString());

                    // Single transposition word correction
                    int index = notepad.getTextArea().getText().toLowerCase().indexOf(falseText);
                    notepad.getTextArea().replaceRange(temp.toString(), index, index + falseText.length());
                    singleTranspositionTexts.add(falseText);
                    tempHash.remove(falseText);
                }
                temp = new StringBuilder(falseText);
            }
        }
        JOptionPane.showMessageDialog(null,"Single Transposition Olan Kelimeler : \n" + singleTranspositionTexts + "\nDoğru Versiyonları : \n" + singleTransTrueVersion);
        textAreaWordHashSet.removeAll(singleTranspositionTexts);
        JOptionPane.showMessageDialog(null, "True def.Words : \n" + trueWordsHashSet+"\nFalse words: \n" + textAreaWordHashSet);
    }

    // check whether the word is numeric or not with this method.
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
