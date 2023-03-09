package cat.proven.guiprod;


import cat.proven.guiprod.views.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author jose
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

}
