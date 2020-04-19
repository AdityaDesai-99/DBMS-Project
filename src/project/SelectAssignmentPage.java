/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.bulenkov.darcula.DarculaLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author desai
 */
public class SelectAssignmentPage extends javax.swing.JFrame {

    /**
     * Creates new form SelectAssignmentPage
     */
    public SelectAssignmentPage() {
        initComponents();
    }

    static String selectedSubject = "";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        assignmentScroll = new javax.swing.JScrollPane();
        assignmentsTable = new javax.swing.JTable();
        assignmentLabel = new javax.swing.JLabel();
        selectAss = new javax.swing.JLabel();
        continueButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        assignmentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Assignment number", "Assignment title", "Last Date"
            }
        ));
        assignmentsTable.setRowSelectionAllowed(true);
        assignmentsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        assignmentScroll.setViewportView(assignmentsTable);
        updateTable();

        assignmentLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        assignmentLabel.setText("Assignments");

        selectAss.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectAss.setText("Select Assignment");

        continueButton.setText("Continue");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(continueButton)
                                .addGap(98, 98, 98)
                                .addComponent(backButton))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(selectAss)))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(assignmentScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(assignmentLabel)
                .addGap(120, 120, 120))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assignmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectAss, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assignmentScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(continueButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        int selectedRow = assignmentsTable.getSelectedRow();
        AddSolution.solutionAss = assignmentsTable.getValueAt(selectedRow, 0).toString();
        new AddSolution().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_continueButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new StudentPage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    void updateTable() {
        DefaultTableModel table = (DefaultTableModel) assignmentsTable.getModel();
        SQLutils sql = new SQLutils(this);
        List<Map<String, Object>> resultSet = sql.selectQueryWhere("assNo, title, lastdate", "assignments", String.format(" subID = \'%s\' and tchID = \'%s\'", selectedSubject, TeacherLoginPage.loggedInTeacher.teacherID), "");
        sql.close();
        if (resultSet.isEmpty()) {
            Utils.showMessage(this, "No Assignments currently available for this Subject!");
            backButton.doClick();
        }
        for (Map row : resultSet) {
            table.addRow(new Vector<>(row.values()));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Darcula look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            System.err.close();
            System.setErr(new PrintStream(new FileOutputStream("err.log")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set laf to darcula
        try {
            UIManager.setLookAndFeel(new DarculaLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { new SelectAssignmentPage().setVisible(true); }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel assignmentLabel;
    private javax.swing.JTable assignmentsTable;
    private javax.swing.JButton backButton;
    private javax.swing.JButton continueButton;
    private javax.swing.JLabel selectAss;
    private javax.swing.JScrollPane assignmentScroll;
    // End of variables declaration//GEN-END:variables
}
