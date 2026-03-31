package com.sms;
import com.sms.FeeManagementForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JFrame {
    private JPanel MenuPanel;
    private JButton studentinfoButton;
    private JButton classButton;
    private JButton feesreportsButton;
    private JButton teacherschedButton;

    public MenuForm() {
        setTitle("Western Institute of Technology");
        setContentPane(MenuPanel);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        studentinfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentInformation studinfo = new StudentInformation();
                studinfo.setVisible(true);
                dispose();
            }
        });
        classButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassesForm classform = new ClassesForm();
                classform.setVisible(true);
                dispose();
            }
        });
        teacherschedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeacherScheduleForm teachschedform = new TeacherScheduleForm();
                teachschedform.setVisible(true);
                dispose();
            }
        });
        feesreportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame feeFrame = new JFrame("Fee Management");
                feeFrame.setContentPane(new FeeManagementForm().mainPanel);
                feeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                feeFrame.pack();
                feeFrame.setSize(900, 400);
                feeFrame.setLocationRelativeTo(null);
                feeFrame.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new MenuForm().setVisible(true);
    }
}