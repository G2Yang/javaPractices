package cat.proven.xmldomschool;

import cat.proven.util.menu.Menu;
import cat.proven.util.menu.Option;

public class SchoolMenu extends Menu {

    public SchoolMenu() {
        super("School manager");
        addOption(new Option("Exit", "exit"));
        addOption(new Option("Show school", "school/all"));
        addOption(new Option("Save all students", "students/save"));
       
    }
}
