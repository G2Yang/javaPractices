package cat.proven.xmldomschool.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class School {
    
   private String name;
   private List<Group> groups;
   private List<Student> students;

    public School(String name, List<Group> groups) {
        this.name = name;
        this.groups = groups;
    }

    public School() {
        groups = new ArrayList<>();
    }

    public School(String name, List<Group> groups, List<Student> students) {
        this.name = name;
        this.groups = groups;
        this.students= students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

      public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final School other = (School) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "School{" + "name=" + name + ", groups=" + groups + '}';
    }

  
    
    
    
}
