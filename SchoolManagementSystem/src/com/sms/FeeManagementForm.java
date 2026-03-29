package com.sms;

import javax.swing.*;

public class FeeManagementForm {
    private JTextField studentNameField;
    private JTextField feeAmountField;
    private JTextField dueDateField;
    private JButton addRecordButton;
    private JTable feeTable;
    private JButton generateReportButton;
    private JPanel mainPanel;
    private JButton clearButton;
    private JButton deleteRecordButton;
    private JLabel totalFeesLabel;
    private JTextField amountPaidField;
    private JLabel balanceLabel;
    private JLabel totalCollectedLabel;
    private JLabel totalPendingLabel;
    private JButton updatePaymentButton;

    public FeeManagementForm() {

        // Real-time balance/change calculator
        amountPaidField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { calculate(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { calculate(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { calculate(); }

            private void calculate() {
                try {
                    double due = Double.parseDouble(feeAmountField.getText().trim());
                    double paid = Double.parseDouble(amountPaidField.getText().trim());
                    double diff = paid - due;
                    if (diff < 0) {
                        balanceLabel.setText("Balance: " + String.format("%.2f", Math.abs(diff)));
                    } else if (diff == 0) {
                        balanceLabel.setText("0.00");
                    } else {
                        balanceLabel.setText("Change: " + String.format("%.2f", diff));
                    }
                } catch (NumberFormatException ex) {
                    balanceLabel.setText("0.00");
                }
            }
        });

        // Set up the table columns

        String[] columns = {"Student Name", "Amount Due", "Amount Paid", "Due Date", "Balance/Change", "Status"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        feeTable.setModel(model);

        // Load selected row into fields when clicked
        feeTable.getSelectionModel().addListSelectionListener(e -> {
            int row = feeTable.getSelectedRow();
            if (row != -1) {
                studentNameField.setText(model.getValueAt(row, 0).toString());
                feeAmountField.setText(model.getValueAt(row, 1).toString());
                amountPaidField.setText(model.getValueAt(row, 2).toString());
                dueDateField.setText(model.getValueAt(row, 3).toString());
            }
        });

        // Add Record button
        addRecordButton.addActionListener(e -> {
            String name = studentNameField.getText();
            String amountDueText = feeAmountField.getText();
            String amountPaidText = amountPaidField.getText();
            String date = dueDateField.getText();

            if (name.isEmpty() || amountDueText.isEmpty() || amountPaidText.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                return;
            }

            try {
                double amountDue = Double.parseDouble(amountDueText);
                double amountPaid = Double.parseDouble(amountPaidText);
                double difference = amountPaid - amountDue;
                String status;
                String balanceChange;

                if (amountPaid < amountDue) {
                    status = "Pending";
                    balanceChange = "Balance: " + String.format("%.2f", Math.abs(difference));
                } else if (amountPaid == amountDue) {
                    status = "Paid";
                    balanceChange = "0.00";
                } else {
                    status = "Paid";
                    balanceChange = "Change: " + String.format("%.2f", difference);
                }

                balanceLabel.setText(balanceChange);
                model.addRow(new Object[]{name, String.format("%.2f", amountDue), String.format("%.2f", amountPaid), date, balanceChange, status});
                studentNameField.setText("");
                feeAmountField.setText("");
                amountPaidField.setText("");
                dueDateField.setText("");

                // Receipt popup
                String receipt = "===== PAYMENT RECEIPT =====\n\n" +
                        "Student Name : " + name + "\n" +
                        "Amount Due   : " + String.format("%.2f", amountDue) + "\n" +
                        "Amount Paid  : " + String.format("%.2f", amountPaid) + "\n" +
                        "Status       : " + status + "\n" +
                        "Due Date     : " + date + "\n" +
                        "Balance/Change: " + balanceChange + "\n\n" +
                        "===========================\n" +
                        "Thank you for your payment!";
                JOptionPane.showMessageDialog(null, receipt, "Receipt", JOptionPane.INFORMATION_MESSAGE);
                updateTotalFees(model);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Amount Due and Amount Paid must be numbers!");
            }
        });

        // Delete Record button
        deleteRecordButton.addActionListener(e -> {
            int row = feeTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a record to delete!");
                return;
            }
            model.removeRow(row);
            updateTotalFees(model);
        });

        // Update Payment button
        updatePaymentButton.addActionListener(e -> {
            int row = feeTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a record to update!");
                return;
            }

            try {
                double amountDue = Double.parseDouble(feeAmountField.getText().trim());
                double previouslyPaid = Double.parseDouble(model.getValueAt(row, 2).toString());
                double newPayment = Double.parseDouble(amountPaidField.getText().trim());
                double amountPaid = previouslyPaid + newPayment;
                double diff = amountPaid - amountDue;
                String status;
                String balanceChange;

                if (amountPaid < amountDue) {
                    status = "Pending";
                    balanceChange = "Balance: " + String.format("%.2f", Math.abs(diff));
                } else if (amountPaid == amountDue) {
                    status = "Paid";
                    balanceChange = "0.00";
                } else {
                    status = "Paid";
                    balanceChange = "Change: " + String.format("%.2f", diff);
                }

                model.setValueAt(studentNameField.getText(), row, 0);
                model.setValueAt(String.format("%.2f", amountDue), row, 1);
                model.setValueAt(String.format("%.2f", amountPaid), row, 2);
                model.setValueAt(dueDateField.getText(), row, 3);
                model.setValueAt(balanceChange, row, 4);
                model.setValueAt(status, row, 5);

                balanceLabel.setText(balanceChange);
                updateTotalFees(model);

                JOptionPane.showMessageDialog(null, "Payment updated successfully!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Amount Due and Amount Paid must be numbers!");
            }
        });

        // Clear button
        clearButton.addActionListener(e -> {
            studentNameField.setText("");
            feeAmountField.setText("");
            dueDateField.setText("");
        });


        // Generate Report button
        generateReportButton.addActionListener(e -> {
            StringBuilder report = new StringBuilder("Fee Report:\n\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                report.append("Name: ").append(model.getValueAt(i, 0)).append("\n")
                        .append("Amount Due: ").append(model.getValueAt(i, 1)).append("\n")
                        .append("Amount Paid: ").append(model.getValueAt(i, 2)).append("\n")
                        .append("Due Date: ").append(model.getValueAt(i, 3)).append("\n")
                        .append("Balance/Change: ").append(model.getValueAt(i, 4)).append("\n")
                        .append("Status: ").append(model.getValueAt(i, 5)).append("\n")
                        .append("----------------------------\n");
            }
            JOptionPane.showMessageDialog(null, report.toString());
        });
    }

    private void updateTotalFees(javax.swing.table.DefaultTableModel model) {
        double totalDue = 0.0;
        double totalCollected = 0.0;
        double totalPending = 0.0;

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                double amountDue = Double.parseDouble(model.getValueAt(i, 1).toString());
                double amountPaid = Double.parseDouble(model.getValueAt(i, 2).toString());
                String status = model.getValueAt(i, 5).toString();

                totalDue += amountDue;
                totalCollected += Math.min(amountPaid, amountDue);

                if (status.equals("Pending")) {
                    totalPending += amountDue - amountPaid;
                }
            } catch (NumberFormatException ex) {
                // skip
            }
        }

        totalFeesLabel.setText("Total Fees: " + String.format("%.2f", totalDue));
        totalCollectedLabel.setText("Total Collected: " + String.format("%.2f", totalCollected));
        totalPendingLabel.setText("Total Pending: " + String.format("%.2f", totalPending));
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Fee Management System");
        frame.setContentPane(new FeeManagementForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(900, 400);
    }}


