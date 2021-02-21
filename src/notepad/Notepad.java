package notepad;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import stockTheme.*;

// Abstract Factory Pattern / Client
// NotepadCreator method creates the factory

public class Notepad  implements  ActionListener{
    private NotepadFactory factory;
    private JFrame frame;
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JMenuBar mb;
    private JMenu m1;
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;
    private JLabel infoTextField1;
    private JLabel infoTextField2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton searchButton;
    private JButton delButton;
    private JButton replaceButton;
    private JButton controlButton;
    private JTextArea textArea;
    private JScrollPane jScrollPane;


    private JMenu themeMenu;
    private JMenuItem stockMenuItem;
    private JMenuItem darkModeMenuItem;
    private JMenuItem blackModeMenuItem;

    private JPanel panel3;

    UndoManager manager = new UndoManager();
    UndoableEditEvent event;
    UndoableCommand undoablecommand = new UndoCommand(manager,event);
    private static Notepad instance;

    public static Notepad getInstance() {
        if (instance == null) {
            instance = new Notepad();
        }
        return instance;
    }

    private Notepad(){}

    public NotepadFactory getFactory() {
        return factory;
    }

    public void setFactory(NotepadFactory factory) {
        this.factory = factory;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JButton getDelButton() {
        return delButton;
    }

    public void setDelButton(JButton delButton) {
        this.delButton = delButton;
    }

    public JButton getReplaceButton() {
        return replaceButton;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public JMenu getThemeMenu() {
        return themeMenu;
    }

    public void setThemeMenu(JMenu themeMenu) {
        this.themeMenu = themeMenu;
    }


    public static void setInstance(Notepad instance) {
        Notepad.instance = instance;
    }

    public void NotepadCreator(NotepadFactory x) {
        getInstance();
        factory = x;
        frame = factory.makeFrame("Notepad");
        panel = factory.makePanel();
        panel1 = factory.makePanel();
        panel2 = factory.makePanel();
        mb = factory.makeMenuBar();
        m1 = factory.makeMenu("File");
        newMenuItem = factory.makeMenuItem("New");
        openMenuItem = factory.makeMenuItem("Open");
        saveMenuItem = factory.makeMenuItem("Save");
        exitMenuItem = factory.makeMenuItem("Exit");

        infoTextField1 = factory.makeLabel("Aranacak Kelime:");
        infoTextField2 = factory.makeLabel("Degisecek Kelime:");
        textField1 = factory.makeTextField(25);
        textField2 = factory.makeTextField(25);
        searchButton = factory.makeButton("Search");
        delButton = factory.makeButton("Undo");
        replaceButton = factory.makeButton("Replace");
        controlButton = factory.makeButton("Control");

        textArea = factory.makeTextArea();
        textArea.setLineWrap (true);
        textArea.setWrapStyleWord (false);
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent event) {
                undoablecommand.delete(event);
            }
        });
        jScrollPane = factory.makeScrollPane(textArea);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        textField1.addActionListener(this);
        textField2.addActionListener(this);

        m1.add(newMenuItem);
        m1.add(openMenuItem);
        m1.add(saveMenuItem);
        m1.add(exitMenuItem);
        mb.add(m1);

        searchButton.addActionListener(this);
        delButton.addActionListener(this);
        replaceButton.addActionListener(this);
        controlButton.addActionListener(this);

        panel.setLayout(new FlowLayout(FlowLayout.CENTER,25,5));
        panel.add(infoTextField1);
        panel.add(textField1);
        panel.add(searchButton);
        panel.add(delButton);

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
        panel1.add(infoTextField2);
        panel1.add(textField2);
        panel1.add(replaceButton);
        panel1.add(controlButton);

        panel3 = factory.makePanel();
        panel3.setLayout(new CardLayout(10,20));
        panel3.add(jScrollPane);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(panel);
        panel2.add(panel1);

        themeMenu = factory.makeMenu("Themes");
        stockMenuItem = factory.makeMenuItem("Stock Theme");
        darkModeMenuItem = factory.makeMenuItem("Dark Mode");
        blackModeMenuItem = factory.makeMenuItem("Black Mode");

        stockMenuItem.addActionListener(this);
        darkModeMenuItem.addActionListener(this);
        blackModeMenuItem.addActionListener(this);

        themeMenu.add(stockMenuItem);
        themeMenu.add(darkModeMenuItem);
        themeMenu.add(blackModeMenuItem);

        mb.add(themeMenu);
        frame.setJMenuBar(mb);
        frame.setLayout(new BorderLayout());
        frame.add(panel2,BorderLayout.NORTH);
        frame.add(panel3,BorderLayout.CENTER);

        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        textField2.setEnabled(false);
        replaceButton.setEnabled(false);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        Search search = Search.getInstance();  // Search and replace operations
        Words words = Words.getInstance();      //Words control
        DoOperation islem = new DoOperation();        //Strategy Pattern

        if (s.equals("New")) {
            islem.islemYap(new NewFile());
        } else if (s.equals("Open")) {
            islem.islemYap(new OpenFile());
        } else if (s.equals("Exit")) {
            islem.islemYap(new CloseFile());
        } else if (s.equals("Save")) {
            islem.islemYap(new SaveFile());
        } else if (s.equals("Search")) {
            search.searchAreaButton();
        } else if (s.equals("Replace")) {
            search.replaceWordButton();
            replaceButton.setEnabled(false);
            textField2.setEnabled(false);
        } else if (s.equals("Undo")) {
            undoablecommand.execute();
        } else if (s.equals("Control")) {
            words.controlButton();
        } else if (s.equals("Stock Theme")) {
            frame.setVisible(false);
            frame.dispose();
            NotepadAbstractFactory.main(new String[] {"Stock Theme"});
        } else if (s.equals("Dark Mode")) {
            frame.setVisible(false);
            frame.dispose();
            NotepadAbstractFactory.main(new String[] {"Dark Mode"});
        } else if (s.equals("Black Mode")) {
            frame.setVisible(false);
            frame.dispose();
            NotepadAbstractFactory.main(new String[] {"Black Mode"});
        }

    }
}
