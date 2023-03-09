package cat.proven.xmldomschool.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {
    private String name;
    private String tutor;
    private String curriculum;
    private List<Student> students;

    public Group() {
       students =new ArrayList<>();
    }
    
    

    public Group(String name, String tutor, String curriculum, List<Student> students) {
        this.name = name;
        this.tutor = tutor;
        this.curriculum = curriculum;
        this.students = students;
    }

    public Group(String name, String tutor, String curriculum) {
        this.name = name;
        this.tutor = tutor;
        this.curriculum = curriculum;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Group other = (Group) obj;
        return Objects.equals(this.name, other.name);
    }
    
    @Override
    public String toString() {
        return "Group{" + "name=" + name + ", tutor=" + tutor + ", curriculum=" + curriculum + ", Students=" + students + '}';
    }
   
    
    

    
}
