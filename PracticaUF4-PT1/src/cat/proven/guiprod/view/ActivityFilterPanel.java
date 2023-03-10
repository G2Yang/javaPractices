/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cat.proven.guiprod.view;

import cat.proven.guiprod.model.Activity;
import cat.proven.guiprod.model.DbConnect;
import cat.proven.guiprod.model.ModelActivity;
import cat.proven.guiprod.model.User;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author dax
 */
public class ActivityFilterPanel extends javax.swing.JPanel {

    ModelActivity model;

    /**
     * Creates new form ActivityFilterPanel
     */
    public ActivityFilterPanel() {
        model = new ModelActivity();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        activity = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();
        filter = new javax.swing.JButton();

        activity.setText("Activity:");

        comboBox.setModel(rellenarComboBox());

        filter.setText("Filter");
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handlerMenu(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(activity, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(filter)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filter))
                    .addComponent(activity))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void handlerMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handlerMenu
        String action = evt.getActionCommand();

        if (action != null) {
            switch (action) {
                case "Filter":
                    filter();
                    break;
            }
        }
    }//GEN-LAST:event_handlerMenu

    public DefaultComboBoxModel<String> rellenarComboBox() {
        List<Activity> info = new ArrayList<Activity>();
        info = model.getDataFromDataBase();

        DefaultComboBoxModel<String> data = new DefaultComboBoxModel<>();
        for (Activity opcion : info) {
            data.addElement(opcion.getName());
        }
        return data;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activity;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JButton filter;
    // End of variables declaration//GEN-END:variables

    public List<User> filter() {
        int op = comboxActivity();
        List<User> usr = model.getDataFromFilterByActivity(op);
        return usr;
    }

    private int comboxActivity() {
        int op = 0;
        String activityName = this.comboBox.getSelectedItem().toString();
        op = model.getActivityId(activityName);
        System.out.println(op);
        return op;
    }

}
