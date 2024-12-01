import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;


public class GUI implements ActionListener{

    JFrame window;
    // text area
    JTextArea textArea;
    JScrollPane scrollPane;
    // Top menu bar
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    // File Menu
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    //format Menu
    JMenuItem iWrap;
    JMenu menuFont, menuFontSize;

    //Edit Menu
    JMenuItem  iUndo, iRedo;

    Function_File file = new Function_File(this);
    Function_Format format = new Function_Format(this);
    Function_Edit edit = new Function_Edit(this);

    UndoManager um = new UndoManager();
    public static void main(String[] args){
        // launch GUI
        new GUI();

    }

    // constructor
    public GUI(){
        // create window
        createWindow();
        //create text area
        createTextArea();
        //create menu bar
        createMenuBar();
        //create file menu
        createFileMenu();
        //create format menu
        createFormatMenu();
        //create edit menu
        createEditMenu();


        // set window visible on screen
        window.setVisible(true);

    }

    // create a window
    public void createWindow(){
        window = new JFrame("MyPad");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // create text area and insert it in scroll pane and add scroll pane in window   
    public void createTextArea(){
        // create text area
        textArea = new JTextArea();

        // handle the undo function   -->  check how it works

        textArea.getDocument().addUndoableEditListener(
            new UndoableEditListener() {
                // public void UndoableEditHappened(UndoableEditEvent e){
                //     um.addEdit(e.getEdit());
                // }

                @Override
                public void undoableEditHappened(UndoableEditEvent e) {
                    um.addEdit(e.getEdit());
                }
            });

            
        // create scroll pane and it to the textarea in the scroll pane
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // add the scroll pane to the window
        window.add(scrollPane);
    }

    // create menu bar
    public void createMenuBar(){
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);
        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);
        menuColor = new JMenu("Color");
        menuBar.add(menuColor);
        
    }

    // create menu file items
    public void createFileMenu(){
        iNew = new JMenuItem("New");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this);
        iSave.setActionCommand("Save");    
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("SaveAs");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("SaveAs");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("Exit");        
        menuFile.add(iExit);

    }

    public void createFormatMenu(){
        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

    }

    public void createEditMenu(){

        iUndo  = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo  = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String command = e.getActionCommand();

        switch(command){
            case "New": file.newFile();break;
            case "Open": file.open();break;
            case "SaveAs": file.saveAs();break;
            case "Save": file.save();break;
            case "Exit": file.exit();break;
            case "Undo": edit.undo();break;
            case "Redo": edit.redo();break;
        }
    }

}
