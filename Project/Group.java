import java.util.*;

public class Group extends TreeSet<Student>{
    public String ID;
    Assistant assistant;
    public Group(String ID, Assistant assistant, Comparator<Student> comp) {
        super(comp);
        this.ID=ID;
        this.assistant=assistant;
    }
    public Group(String ID, Assistant assistant) {
        this.ID=ID;
        this.assistant=assistant;

    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }
    public Assistant getAssistant(){
        return assistant;
    }
    public String getID(){
        return ID;
    }
    public void setID(String ID){
        this.ID=ID;
    }
    public ArrayList<Student>getStudents(){
        ArrayList<Student> lista = new ArrayList<Student>(this);
        return lista;
    }

}
