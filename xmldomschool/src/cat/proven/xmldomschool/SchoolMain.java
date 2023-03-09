package cat.proven.xmldomschool;

import cat.proven.xmldomschool.model.Group;
import cat.proven.xmldomschool.model.School;
import cat.proven.xmldomschool.model.Student;
import cat.proven.xmldomschool.model.persist.XmlDomSchoolPersist;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import static javax.swing.JOptionPane.showInputDialog;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


/**
 * Application to manage data of a school. Data is read from an xml file using
 * DOM.
 *
 * @author ProvenSoft
 */
public class SchoolMain {

    private final SchoolMenu mainMenu;
    private boolean exit;

    public SchoolMain() {
        mainMenu = new SchoolMenu();
    }

    public static void main(String[] args) throws TransformerException {
        SchoolMain app = new SchoolMain();
        app.run();
    }

    private void run() throws TransformerException {
        interact();
    }

    /**
     * VIEW METHODS
     */
    /**
     * Interacts with user. Displays the menu and controls user's actions.
     */
    public void interact() throws TransformerException {
        exit = false;
        do {
            //display menu
            mainMenu.show();
            //read user's choice
            String action = mainMenu.getSelectedOptionActionCommand();
            action = (action == null) ? "wrong_option" : action;
            //control action
            executeAction(action);
        } while (!exit);
        showMessage("Exitting application");
    }

    /**
     * Displays a message.
     *
     * @param message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prompts a message and reads an input from user
     *
     * @param message the message to prompt
     * @return input from user
     */
    public String prompt(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    private void showSchool(School school) {
        showMessage("School name: "+ school.getName());
        showMessage("Group name: "+ school.getGroups());
        //school.getStudents().forEach(System.out::println);
    }

    /**
     * asks for confirmation and exits application.
     */
    private void exitApplication() {
        String answer = prompt("Sure to exit? (y/n): ");
        if (answer.toLowerCase().equals("y")) {
            exit = true;
        }
    }

    /**
     * CONTROL METHODS
     */
    /**
     * Processes requests from the view.
     *
     * @param action to execute.
     */
    public void executeAction(String action) throws TransformerException {
        switch (action) {
            case "exit":
                exitApplication();
                break;
            case "school/all":
                listSchool();
                break;
            case "students/save":
                saveAll();
                break;

            case "wrong_option":
            default:
                showMessage("Wrong option");
                break;
        }
    }

    private void listSchool() {
        String message;
        
        //List all elements(of file) of the school and show it
        String filename = showInputDialog("Input file name: ");
        
        XmlDomSchoolPersist persister = new XmlDomSchoolPersist(filename);
        School company = persister.readSchoolElement();
        
      
        if (company!= null) {
            showMessage("Successfully read");
            showSchool(company);
            
        } else {
             showMessage("Read fail");
        }
    }
    
    public void saveAll() throws TransformerException{
        // calls a function of XmlDomSchoolPersist  for do it
        String filename = "";
        XmlDomSchoolPersist persister = new XmlDomSchoolPersist(filename);
        persister.saveAllStudents();
    }

    
    
    public String showInputDialog(String message){
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
