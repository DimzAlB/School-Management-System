package com.sms;
import com.sms.FeeManagementForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentInformation extends JFrame {
    private JPanel Dashboard;
    private JTextField id;
    private JTextField fname;
    private JTextField lname;
    private JTextField ccode;
    private JTextField sem;
    private JTextField contnum;
    private JTextField gender;
    private JTextField age;
    private JTextField bdate;
    private JButton addDataButton;
    private JTextField yearsec;
    private JTable table1;
    private JButton deleteButton;
    private JButton backtomenuButton;
    private DefaultTableModel model;

    public StudentInformation() {
        setTitle("Western Institute of Technology");
        setSize(600, 450);
        setContentPane(Dashboard);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id1 = id.getText();
                String age1 = age.getText();
                String fname1 = fname.getText();
                String gender1 = gender.getText();
                String lname1 = lname.getText();
                String bdate1 = bdate.getText();
                String ccode1 = ccode.getText();
                String contnum1 = contnum.getText();
                String sem1 = sem.getText();
                String yearsec1 = yearsec.getText();

                if (id1.isEmpty() || age1.isEmpty() || fname1.isEmpty() || gender1.isEmpty() ||
                        lname1.isEmpty() || bdate1.isEmpty() || ccode1.isEmpty() || contnum1.isEmpty() || sem1.isEmpty()
                        || yearsec1.isEmpty()) {
                    JOptionPane.showMessageDialog(StudentInformation.this, "Please fill up all forms!");
                } else {
                    model.addRow(new Object[]{id1, fname1, lname1, age1, gender1, bdate1, contnum1, ccode1, sem1, yearsec1});
                    // Optional: clear fields
                    id.setText("");
                    age.setText("");
                    fname.setText("");
                    gender.setText("");
                    lname.setText("");
                    bdate.setText("");
                    ccode.setText("");
                    contnum.setText("");
                    sem.setText("");
                    yearsec.setText("");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Select a row to delete!");
                } else {
                    model.removeRow(selectedRow);
                }
            }
        });

        // Click table and show data
        table1.getSelectionModel().addListSelectionListener(e -> {
            int row = table1.getSelectedRow();
            if (row != -1) {
                id.setText(model.getValueAt(row, 0).toString());
                age.setText(model.getValueAt(row, 1).toString());
                fname.setText(model.getValueAt(row, 2).toString());
                gender.setText(model.getValueAt(row, 3).toString());
                lname.setText(model.getValueAt(row, 4).toString());
                bdate.setText(model.getValueAt(row, 5).toString());
                ccode.setText(model.getValueAt(row, 6).toString());
                contnum.setText(model.getValueAt(row, 7).toString());
                sem.setText(model.getValueAt(row, 8).toString());
                yearsec.setText(model.getValueAt(row, 9).toString());
            }
        });
        backtomenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuForm menu1 = new MenuForm();
                menu1.setVisible(true);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // Column names in Header
        model = new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name", "Age",
                "Gender", "Birth Date", "Contact No.", "Course", "Semester", "Year and Section"}, 0);

    table1 = new JTable(model);
}

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentInformation().setVisible(true);
        });
    }
}