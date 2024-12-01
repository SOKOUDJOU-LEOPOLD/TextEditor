public class Function_Edit {
    GUI gui;

    public Function_Edit(GUI gui){
        this.gui = gui;
    }

    //Undo method
    public void undo(){

        gui.um.undo();
    }

    // Redo Method
    public void redo(){
        gui.um.redo();
    }
}
