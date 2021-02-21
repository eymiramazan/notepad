package stockTheme;

import notepad.Notepad;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {
    // Used this arrayList to search and replace word
    // In the 0th index, the word itself keeps track of the indexes of the word in the text from the 1st index.
    ArrayList<Object> searchAndReplaceWordList = new ArrayList<>();
    // It keeps the number of times the search button has been pressed. Thus, can navigate all the words in the text.
    int searchButtonCount;

    Notepad notepad = Notepad.getInstance();

    private static Search instance;
    public static Search getInstance(){
        if(instance==null){
            instance = new Search();
        }
        return instance;
    }


    public void searchAreaButton(){
        notepad.getTextField2().setEnabled(true);
        notepad.getReplaceButton().setEnabled(true);
        if(!searchAndReplaceWordList.isEmpty()){
            if(!searchAndReplaceWordList.get(0).equals(notepad.getTextField1().getText())){
                searchAndReplaceWordList = new ArrayList<>();
                searchButtonCount = 0;
            }
        }
        searchTextArea();
    }

    private void searchTextArea(){
        removeHighLight(notepad.getTextArea());

        Highlighter highlight = notepad.getTextArea().getHighlighter();

        // If it has never been searched
        if (searchButtonCount == 0){
            isContainWord();
            searchButtonCount++;
        }

        // If in the last word in textarea, return to the beginning by setting the count value to 1.
        if(searchButtonCount == searchAndReplaceWordList.size()){
            searchButtonCount = 1;
        }

        // If there is more than one word and it has not reached the end, continue to mark it.
        if(searchButtonCount != 0 && searchButtonCount < searchAndReplaceWordList.size()){
            try {
                highlight.addHighlight((int) searchAndReplaceWordList.get(searchButtonCount), ((int) searchAndReplaceWordList.get(searchButtonCount) + ((String) searchAndReplaceWordList.get(0)).length()), highLighter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            searchButtonCount++;
        }

    }

    private void isContainWord(){
        // to find out if the word exists.
        String fullString = notepad.getTextArea().getText();
        String partWord = notepad.getTextField1().getText();
        String pattern = "\\b" + partWord + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(fullString);
        searchAndReplaceWordList.add(partWord);

        int position;
        while(m.find()){
            position = m.start();
            //add the positions of the word starting from the 1st index of the list.
            searchAndReplaceWordList.add(position);
        }
    }

    public void replaceWordButton(){
        replaceWord();
        searchAndReplaceWordList = new ArrayList<>();
        isContainWord();
        searchButtonCount--;
        notepad.getTextField2().setText("");
    }

    private void replaceWord() {
        StringBuilder finalText = new StringBuilder("");
        Document document = notepad.getTextArea().getDocument();
        StringBuilder text = null;
        try {
            text = new StringBuilder(document.getText(0, document.getLength()));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        String takeReplaceWord = notepad.getTextField2().getText();
        finalText = text.replace((int) searchAndReplaceWordList.get(searchButtonCount - 1), (int) searchAndReplaceWordList.get(searchButtonCount - 1) + ((String) searchAndReplaceWordList.get(0)).length(), takeReplaceWord);
        notepad.getTextArea().setText(finalText.toString());
    }

    // Highlighter
    static class MyHighLighter extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighLighter(Color color) {
            super(color);
        }
    }

    DefaultHighlighter.DefaultHighlightPainter highLighter = new MyHighLighter(Color.yellow);

    private void removeHighLight(JTextArea textArea) {
        Highlighter removeHighlighter = textArea.getHighlighter();
        Highlighter.Highlight[] remove = removeHighlighter.getHighlights();

        for (Highlighter.Highlight highlight : remove) {
            if (highlight.getPainter() instanceof MyHighLighter) {
                removeHighlighter.removeHighlight(highlight);
            }
        }
    }

}
