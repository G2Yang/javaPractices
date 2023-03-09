/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cat.proven.guiprod;

import cat.proven.guiprod.view.MainFramee;
import javax.swing.SwingUtilities;

/**
 *
 * @author dax
 */
public class PracticaUF4PT1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFramee main = new MainFramee();
            main.setVisible(true); 
        });
    }
    
}
