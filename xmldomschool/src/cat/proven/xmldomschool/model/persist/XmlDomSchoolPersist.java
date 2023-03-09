package cat.proven.xmldomschool.model.persist;

import cat.proven.xmldomschool.model.Group;
import cat.proven.xmldomschool.model.School;
import cat.proven.xmldomschool.model.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.nio.file.Files.size;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ProvenSoft
 */
public class XmlDomSchoolPersist {

    private School school;
    private String filename;

    public XmlDomSchoolPersist(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public School readSchoolElement() {
        School escuela = null;
        try {
            // create document from XML using DocumentBuilder.
            File file = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);

            //get root element
            Element escuelaElement = document.getDocumentElement();
            escuelaElement.normalize();

            //read school data from document
            //get school name
            Element escuelaNameElement = (Element) escuelaElement.getElementsByTagName("name").item(0);
            String name = escuelaNameElement.getTextContent();

            //get groups
            Element groupsElement = (Element) escuelaElement.getElementsByTagName("groups").item(0);
            List<Group> groups = readgroups(groupsElement);
            //get students
            //Element studentsElement = (Element) escuelaElement.getElementsByTagName("students").item(0);
            //List<Student> students = readStudents(studentsElement);

            //Instantiate company with data read from XML
            escuela = new School(name, groups);

        } catch (FileNotFoundException ex) {

            escuela = null;
        } catch (ParserConfigurationException | SAXException | IOException ex) {

            escuela = null;
        }

        return escuela;
    }

    private List<Group> readgroups(Element groupsElement) {
        List<Group> groups = new ArrayList<>();
        NodeList groupNodes = groupsElement.getChildNodes();
        for (int i = 0; i < groupNodes.getLength(); i++) {
            Node groupNode = groupNodes.item(i);
            if (groupNode instanceof Element groupElement) {
                Group group = readgroup(groupElement);
                groups.add(group);
            }
        }

        return groups;

    }

    private Group readgroup(Element groupElement) {
        Group grupos = null;
        try {
            String name = groupElement.getElementsByTagName("name").item(0).getTextContent();
            String tutor = groupElement.getElementsByTagName("tutor").item(0).getTextContent();
            String curriculum = groupElement.getElementsByTagName("curriculum").item(0).getTextContent();
            NodeList studentsNodes = groupElement.getElementsByTagName("students");
            List<Student> students = readStudents((Element) studentsNodes.item(0));
            grupos = new Group(name, tutor, curriculum, students);
        } catch (NumberFormatException e) {
            e.getMessage();
        }
        return grupos;

    }

    private List<Student> readStudents(Element studentsElement) {
        List<Student> students = new ArrayList<>();
        NodeList studentNodes = studentsElement.getChildNodes();
        for (int i = 0; i < studentNodes.getLength(); i++) {
            Node studentNode = studentNodes.item(i);
            if (studentNode instanceof Element studentElement) {
                Student student = readStudent(studentElement);
                students.add(student);
            }
        }

        return students;

    }

    private Student readStudent(Element studentElement) {
        Student students = null;
        try {
            String id = studentElement.getAttribute("id");
            String name = studentElement.getElementsByTagName("name").item(0).getTextContent();
            String surname = studentElement.getElementsByTagName("surname").item(0).getTextContent();
            String email = studentElement.getElementsByTagName("email").item(0).getTextContent();
            String aage = studentElement.getElementsByTagName("age").item(0).getTextContent();
            int age = Integer.parseInt(aage);
            students = new Student(id, name, surname, email, age);
        } catch (NumberFormatException e) {
            e.getMessage();
        }

        return students;

    }

    public void saveAllStudents() throws TransformerConfigurationException, TransformerException {
        ArrayList<Node> lis = new ArrayList<Node>();
        try {
            Scanner sc = new Scanner(System.in);
            // Read file names from user.;
            System.out.println("Enter source XML file name: ");
            String xmlInputFilename = sc.nextLine();
            System.out.println("Enter destination XML file name: ");
            String xmlOutputFilename = sc.nextLine();
            //
            File xmlFile = new File(xmlInputFilename);
            if (xmlFile.exists()) {
                // Create a document builder factory..
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                // Use document builder factory.
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                // Parse the document and get a document.
                Document document = documentBuilder.parse(xmlFile);

                NodeList nodes = document.getElementsByTagName("students");

                if (nodes.getLength() > 0) {

                    for (int i = 0; i < nodes.getLength(); i++) {
                        
                    Node node = nodes.item(i);
                    lis.add(node);
                    }
                    
                    // Create a transformer factory.
                    TransformerFactory tranformerFactory = TransformerFactory.newInstance();
                    // Get a transformer.
                    Transformer transformer = tranformerFactory.newTransformer();
                    // Configure transformer -> set transformer properties.
                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                    transformer.setOutputProperty(OutputKeys.INDENT, "no");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                    // Create Source and Result for transformer.
                    Source source = new DOMSource((Node) lis);
                    Result result = new StreamResult(new File(xmlOutputFilename));
                    // Perform transformation.
                    transformer.transform(source, result);
                }

            } else {
                System.out.format("File '%s' not found.\n", xmlInputFilename);
            }
        } catch (ParserConfigurationException ex) {
            ex.getMessage();
        } catch (SAXException ex) {
            ex.getMessage();
        } catch (TransformerConfigurationException ex) {
            ex.getMessage();
        } catch (TransformerException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}
