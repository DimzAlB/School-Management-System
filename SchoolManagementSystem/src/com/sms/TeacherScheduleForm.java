package com.sms;
import com.sms.FeeManagementForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherScheduleForm extends JFrame {
    private JPanel TeachSchedPanel;
    private JTextField teach;
    private JTextField dept;
    private JTextField subj;
    private JTextField day;
    private JTextField time;
    private JTextField status;
    private JButton enterDataButton1;
    private JButton deleteButton1;
    private JTable schedTable;
    private JButton backtomenuButton;
    private DefaultTableModel modelTeach;

    public TeacherScheduleForm() {
        setTitle("Western Institute of Technology");
        setContentPane(TeachSchedPanel);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        enterDataButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teach1 = teach.getText();
                String dept1 = dept.getText();
                String subj1 = subj.getText();
                String day1 = day.getText();
                String time1 = time.getText();
                String status1 = status.getText();

                if (teach1.isEmpty() || dept1.isEmpty() || subj1.isEmpty() || day1.isEmpty() ||
                        time1.isEmpty() || status1.isEmpty()) {
                JOptionPane.showMessageDialog(TeacherScheduleForm.this, "Please fill up all forms!");
            } else {
                modelTeach.addRow(new Object[]{teach1, dept1, subj1, day1, time1, status1});
                // Optional: Clear Fields
                teach.setText("");
                dept.setText("");
                subj.setText("");
                day.setText("");
                time.setText("");
                status.setText("");
            }
            }
        });

        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = schedTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Select a row to delete!");
                } else {
                    modelTeach.removeRow(selectedRow);
                }
            }
        });

        // Click table and show date
        schedTable.getSelectionModel().addListSelectionListener(e -> {
            int row1 = schedTable.getSelectedRow();
            if (row1 != -1) {
                teach.setText(modelTeach.getValueAt(row1, 0).toString());
                dept.setText(modelTeach.getValueAt(row1, 1).toString());
                subj.setText(modelTeach.getValueAt(row1, 2).toString());
                day.setText(modelTeach.getValueAt(row1, 3).toString());
                time.setText(modelTeach.getValueAt(row1, 4).toString());
                status.setText(modelTeach.getValueAt(row1, 5).toString());
            }
        });
        backtomenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuForm menuu = new MenuForm();
                menuu.setVisible(true);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // Column names in Header
        modelTeach = new DefaultTableModel(new Object[]{"Teacher", "Department", "Subject", "Day", "Time", "Status"}, 0);

        schedTable = new JTable(modelTeach);
    }

    // Main Method
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new TeacherScheduleForm().setVisible(true);
    });
    }
}