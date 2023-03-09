package cat.proven.guiprod.views;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author jose
 */
public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private final ActionListener actionListener;
    private final String aboutMessage;
    //
    private HomePanel homePanel;
    private ProductFormPanel productFormPanel;
    private ProductTablePanel productTablePanel;
    
    public MainFrame() {
        aboutMessage = "<html><h2>My App</h2><p>by ProvenSoft</p></html>";
        actionListener = (ActionEvent e) -> {
            String action = e.getActionCommand();
            processAction(action);
        };
        initComponents();
    }

    private void initComponents() {
        setTitle("My App main window");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
        menuBar = buildMenuBar();
        setJMenuBar(menuBar);
        homePanel = new HomePanel();
        setContentPane(homePanel);
        setLocationRelativeTo(null);
        setSize(400,300);
    }

    private JMenuBar buildMenuBar() {
        JMenuBar mBar = new JMenuBar();
        JMenu mnu;
        JMenuItem mItem;
        //
        mnu = new JMenu("File");
            mItem = createMenuItem("Home", "home", actionListener);
            mnu.add(mItem);
            mItem = createMenuItem("Exit", "exit", actionListener);
            mnu.add(mItem);
        mBar.add(mnu);
        //
        mnu = new JMenu("Edit");
            mItem = createMenuItem("Product form", "product/form", actionListener);
            mnu.add(mItem);   
            mItem = createMenuItem("Product list", "product/filter", actionListener);
            mnu.add(mItem); 
        mBar.add(mnu);
        //
        mnu = new JMenu("Help");
            mItem = createMenuItem("About", "about", actionListener);
            mnu.add(mItem);
        mBar.add(mnu);
        return mBar;
    }

    private JMenuItem createMenuItem(String text, String actionCommand, ActionListener actionListener) {
        JMenuItem mItem = new JMenuItem(text);
        mItem.setActionCommand(actionCommand);
        mItem.addActionListener(actionListener);
        return mItem;
    }
    
    private void processAction(String action) {
        if (action != null) {
            switch (action) {
                case "exit" -> {
                    exitApplication();
                }
                case "home" -> {
                    displayHomePanel();
                }
                case "about" -> {
                    displayAboutDialog();
                }
                case "product/form" -> {
                    displayProductFormPanel();
                }
                case "product/filter" -> {
                    displayProductTablePanel();
                }
                default ->{
                    displayError("Not implemented");
                }  
            }
            System.out.println("Action: "+action);
        }
    }
    
    private void exitApplication() {
        int answer = JOptionPane.showConfirmDialog(this, "Sure?", "Exit application", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    private void displayAboutDialog() {
        JOptionPane.showMessageDialog(this, aboutMessage, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayHomePanel() {
        setContentPane(homePanel);
        validate();
    }

    private void displayProductFormPanel() {
        productFormPanel = new ProductFormPanel();
        setContentPane(productFormPanel);
        validate();
    }

    private void displayProductTablePanel() {
        productTablePanel = new ProductTablePanel();
        setContentPane(productTablePanel);
        validate();
    }
    
}
