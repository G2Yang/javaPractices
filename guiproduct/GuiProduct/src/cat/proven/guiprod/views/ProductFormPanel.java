package cat.proven.guiprod.views;

import cat.proven.guiprod.model.Model;
import cat.proven.guiprod.model.Product;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author jose
 */
public class ProductFormPanel extends JPanel {

    private Product product;
    //
    private JTextField tfId;
    private JTextField tfCode;
    private JTextField tfDescription;
    private JTextField tfPrice;
    private JTextField tfStock;
    //
    private final ActionListener actionListener;

    //
    public ProductFormPanel() {
        actionListener = (ActionEvent e) -> {
            String action = e.getActionCommand();
            processAction(action);
        };
        initComponents();
        clearForm();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private void initComponents() {
        setBackground(Color.LIGHT_GRAY);
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.yellow, Color.white, Color.white));
        //
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //
        JLabel lb;
        JTextField tf;
        JButton bt;
        //row 0: header
        lb = new JLabel();
            //configure component.
            lb.setText("Product form");
            lb.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 18));
            lb.setHorizontalAlignment(JLabel.CENTER);
            lb.setForeground(Color.RED);
            lb.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.lightGray, Color.yellow));
            //configure gridbagconstraints.
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 0, 5, 0);
            gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(lb, gbc);
        //row 1.
        lb = new JLabel();
            lb.setText("Id:");
            //
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0; gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.weightx = 0.0;
            gbc.insets = new Insets(5, 0, 5, 5);
        add(lb, gbc);
            //
        tf = new JTextField();
            tf.setColumns(25);
            tf.setEditable(false);
            //
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 1; gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(0, 0, 0, 0);
        add(tf, gbc);
        tfId = tf;
        //row 2.
        lb = new JLabel();
            lb.setText("Code:");
            //
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0; gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.weightx = 0.0;
            gbc.insets = new Insets(5, 0, 5, 5);
        add(lb, gbc);
        //
        tf = new JTextField();
            tf.setColumns(25);
            //
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 1; gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 2;
            gbc.weightx = 0.0;
            gbc.insets = new Insets(0, 0, 0, 0);
        add(tf, gbc);
        tfCode = tf;
        //
        bt = new JButton();
            bt.setText("Search");
            bt.setActionCommand("product/search");
            bt.addActionListener(actionListener);
            //
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 3; gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(0, 5, 0, 5);
            gbc.weightx = 0.0;
        add(bt, gbc);
        //row 3
        lb = new JLabel();
            //configure component.
            lb.setText("Description:");
            //configure gridbagconstraints.
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0; gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.weightx = 0.0;
            gbc.insets = new Insets(5, 0, 5, 5);
        add(lb, gbc);
        //
        tf = new JTextField();
            //configure component.
            tf.setColumns(25);
            //configure gridbagconstraints.
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 0.0;
            gbc.insets = new Insets(0, 0, 0, 0);
        add(tf, gbc);
        tfDescription = tf;
        //row 4.
        lb = new JLabel();
            lb.setText("Price:");
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.weightx = 0.0;
            gbc.insets = new Insets(5, 0, 5, 5);
        add(lb, gbc);
        //
        tf = new JTextField();
            tf.setColumns(25);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.weightx = 0.0;
            gbc.insets = new Insets(0, 0, 0, 0);
        add(tf, gbc);
        tfPrice = tf;
        //
        lb = new JLabel();
            lb.setText("Stock:");
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 2;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.weightx = 0.0;
            gbc.insets = new Insets(10, 0, 10, 10);
        add(lb, gbc);
        //
        tf = new JTextField();
            tf.setColumns(25);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 3;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 2;
            gbc.weightx = 1.0;
            gbc.insets = new Insets(0, 0, 0, 0);
        add(tf, gbc);
        tfStock = tf;
        //row 5.
        
        bt = new JButton();
            bt.setText("Insert");
            bt.setActionCommand("product/insert");
            bt.addActionListener(actionListener);
            //
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(0, 5, 0, 5);
            gbc.weightx = 0.0;
        add(bt, gbc);
        
        
        bt = new JButton();
            bt.setText("Save");
            bt.setActionCommand("product/save");
            bt.addActionListener(actionListener);
            //
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(0, 5, 0, 5);
            gbc.weightx = 0.0;
        add(bt, gbc);
        //
        bt = new JButton();
            bt.setText("Clear");
            bt.setActionCommand("clear");
            bt.addActionListener(actionListener);
            //
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 2;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(0, 5, 0, 5);
            gbc.weightx = 0.0;
        add(bt, gbc);
        
        bt = new JButton();
            bt.setText("Delete");
            bt.setActionCommand("delete");
            bt.addActionListener(actionListener);
            //
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 3;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(0, 5, 0, 5);
            gbc.weightx = 0.0;
        add(bt, gbc);
        //
    }

    private Product formToProduct() {
        Product product = null;
        try {
            long id = Long.parseLong(tfId.getText());
            String code = tfCode.getText();
            String description = tfDescription.getText();
            double price = Double.parseDouble(tfPrice.getText());
            int stock = Integer.parseInt(tfStock.getText());
            product = new Product(id, code, description, price, stock);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid data", "Validation error", JOptionPane.ERROR_MESSAGE);
        }
        return product;
    }

    private void productToForm(Product product) {
        tfId.setText(String.valueOf(product.getId()));
        tfCode.setText(product.getCode());
        tfDescription.setText(product.getDescription());
        tfPrice.setText(String.valueOf(product.getPrice()));
        tfStock.setText(String.valueOf(product.getStock()));
    }

    private void processAction(String action) {
        if (action != null) {
            switch (action) {
                case "product/search" -> {
                   search();
                }
                case "product/save" -> {
                   save();
                }
                case "clear" -> {
                    clearForm();
                }
                case "delete" -> {
                    delete();
                }
                case "product/insert" -> {
                    product_insert();
                }
            }
            System.out.println("ProductForm> Action: " + action);
        }
    }

    public void clearForm() {
        productToForm(new Product(0, "", "", 0.0, 0));
    }

    public void save() {
        Product p = formToProduct();
        if (p != null) {
            int res = Model.productService.modify(product, p);
            if (res == 1) {
                alert("Successfully saved");
                product = p;
            } else {
                alert("Not modified");
            }
            product = p;
        }
    }
    
    public void delete(){
        Product p = formToProduct();
        if (p != null) {
            int res = Model.productService.remove(p);
            if (res == 1) {
                alert("Successfully deleted");
                product = p;
            } else {
                alert("Not deleted");
            }
            product = p;
        }
    }
    
    public void product_insert(){
        Product p = formToProduct();
        if (p != null) {
            int res = Model.productService.add(p);
            if (res == 1) {
                alert("Successfully inserted");
                product = p;
            } else {
                alert("Not inserted");
            }
            product = p;
        }
    }

    public void search() {
        String code = tfCode.getText();
        Product p = Model.productService.findByCode(new Product(0, code));
        if (p != null) {
            productToForm(p);
            product = p;
        } else {
            alert("Not found");
        }
    }
    
    public void alert(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
