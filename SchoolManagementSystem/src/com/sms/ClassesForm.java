import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassesForm extends JFrame {
    private JPanel ClassesPanel;
    private JTextField stuID;
    private JTextField yearandsec;
    private JTextField sub;
    private JTextField time;
    private JTable tableClass;
    private JTextField cors;
    private JTextField sems;
    private JTextField room;
    private JButton ADDButton;
    private JButton DELETEButton;
    private JButton backtomenuButton;
    private DefaultTableModel model;

    public ClassesForm() {
        setTitle("Western Institute of Technology");
        setContentPane(ClassesPanel);
        setSize(800,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stuID1 = stuID.getText();
                String ys1 = yearandsec.getText();
                String sub1 = sub.getText();
                String time1 = time.getText();
                String cors1 = cors.getText();
                String sems1 = sems.getText();
                String room1 = room.getText();

                if (stuID1.isEmpty() || ys1.isEmpty() || sub1.isEmpty() || time1.isEmpty() ||
                cors1.isEmpty() || sems1.isEmpty() || room1.isEmpty()) {
                    JOptionPane.showMessageDialog(ClassesForm.this, "Fill in is required!");
                } else {
                    model.addRow(new Object[] {stuID1, ys1, sub1, time1, cors1, sems1, room1});
                    // Optional: clear fields
                    stuID.setText("");
                    yearandsec.setText("");
                    sub.setText("");
                    time.setText("");
                    cors.setText("");
                    sems.setText("");
                    room.setText("");
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectRow = tableClass.getSelectedRow();

                if (selectRow == -1) {
                    JOptionPane.showMessageDialog(null, "Select a row to delete!");
                } else {
                    model.removeRow(selectRow);
                }
            }
        });

        // Click table and show data
        tableClass.getSelectionModel().addListSelectionListener(e -> {
            int row = tableClass.getSelectedRow();
            if (row != -1) {
                stuID.setText(model.getValueAt(row, 0).toString());
                yearandsec.setText(model.getValueAt(row, 1).toString());
                sub.setText(model.getValueAt(row, 2).toString());
                time.setText(model.getValueAt(row, 3).toString());
                cors.setText(model.getValueAt(row, 4).toString());
                sems.setText(model.getValueAt(row, 5).toString());
                room.setText(model.getValueAt(row, 6).toString());
            }
        });
        backtomenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuForm menu2 = new MenuForm();
                menu2.setVisible(true);
                dispose();
            }
        });
    }

    private void createUIComponents() {
        // Column names in Header
        model = new DefaultTableModel(new Object[]{"Student ID", "Year and Section", "Subject", "Time",
                "Course", "Semester", "Room"}, 0);

        tableClass = new JTable(model);
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClassesForm().setVisible(true);
        });
    }
}
