import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Function_File {

    GUI gui;
    String fileName;
    String fileAddress;

    public Function_File(GUI gui){
        this.gui = gui;
    }

    // when you click on New in File menu, it sets a new empty text area 
    public void newFile(){
        // erase current text
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
        

    }

    // click on Open in File
    public void open(){
        // open file
        FileDialog fd = new FileDialog(gui.window, "Open", FileDialog.LOAD);
        fd.setVisible(true);

        // if fd take a file not folder
        if(fd.getFile()!=null){
            // get the file name
            fileName = fd.getFile();
            // get the file directory
            fileAddress = fd.getDirectory();
            // the notepad takes the name of the file opened.
            gui.window.setTitle(fileName);

            System.out.println("File address and file name: "+fileAddress+fileName);
            // put the content of the file selected into the notepad
            try{
                BufferedReader br = new BufferedReader(new FileReader(fileAddress+fileName));
                gui.textArea.setText("");
                String line = null;
                while((line=br.readLine())!=null){
                    gui.textArea.append(line + "\n");
                }
                br.close();
            }catch(Exception e){
                System.out.println("File Not Opened!!");
            }


        }
    }

    public void save(){
        if(fileName==null){
            saveAs();
        }
        else{
            try{
                FileWriter fw = new FileWriter(fileAddress+fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            }catch(Exception e){
                System.out.println("Something Wrong!");
            }
        }
    }

    public void saveAs(){
        FileDialog fd = new FileDialog(gui.window, "Save", FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();

            gui.window.setTitle(fileName);

        }

        try{
            FileWriter fw = new FileWriter(fileAddress+fileName);
            fw.write(gui.textArea.getText());
            fw.close();
        }catch(Exception e){
            System.out.println("Something Wrong!");
        }

    }

    public void exit(){
        System.exit(0);
    }
    
}
