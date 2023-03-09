package cat.proven.guiprod.views;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jose
 */
public class HomePanel extends JPanel {

    public HomePanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        JLabel lbMessage = new JLabel("Welcome to this application");
        lbMessage.setForeground(Color.magenta);
        add(lbMessage);
    }
    
}
