package com.sms;

import javax.swing.*;

public class FeeManagementForm {
    private JTextField studentNameField;
    private JTextField feeAmountField;
    private JTextField dueDateField;
    private JButton addRecordButton;
    private JTable feeTable;
    private JComboBox statusComboBox;
    private JButton markAsPaidButton;
    private JButton generateReportButton;
    private JPanel mainPanel;
    private JButton clearButton;
    private JButton deleteRecordButton;

    public FeeManagementForm() {
        // Set up the table columns

        String[] columns = {"Student Name", "Fee Amount", "Due Date", "Status"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(columns, 0);
        feeTable.setModel(model);

        // Add Record button
        addRecordButton.addActionListener(e -> {
            String name = studentNameField.getText();
            String amount = feeAmountField.getText();
            String date = dueDateField.getText();
            String status = (String) statusComboBox.getSelectedItem();

            if (name.isEmpty() || amount.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                return;
            }

            model.addRow(new Object[]{name, amount, date, status});
            studentNameField.setText("");
            feeAmountField.setText("");
            dueDateField.setText("");
        });

        // Delete Record button
        deleteRecordButton.addActionListener(e -> {
            int row = feeTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a record to delete!");
                return;
            }
            model.removeRow(row);
        });

        // Clear button
        clearButton.addActionListener(e -> {
            studentNameField.setText("");
            feeAmountField.setText("");
            dueDateField.setText("");
            statusComboBox.setSelectedIndex(0);
        });

        // Mark as Paid button
        markAsPaidButton.addActionListener(e -> {
            int row = feeTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a record first!");
                return;
            }

            model.setValueAt("Paid", row, 3);
        });

        // Generate Report button
        generateReportButton.addActionListener(e -> {
            StringBuilder report = new StringBuilder("Fee Report:\n\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                report.append(model.getValueAt(i, 0)).append(" | ")
                        .append(model.getValueAt(i, 1)).append(" | ")
                        .append(model.getValueAt(i, 2)).append(" | ")
                        .append(model.getValueAt(i, 3)).append("\n");
            }

            JOptionPane.showMessageDialog(null, report.toString());
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fee Management System");
        frame.setContentPane(new FeeManagementForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(700, 500);
    }}


