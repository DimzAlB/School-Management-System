package com.sms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JFrame {
    private JPanel UserLoginPanel;
    private JTextField usernym;
    private JButton ENTERButton;
    private JTextField emeil;

    public UserLogin() {
        setTitle("Western Institute of Technology");
        setContentPane(UserLoginPanel);
        setSize(450,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ENTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernym1 = usernym.getText();
                String email1 = emeil.getText();

                if (usernym1.isEmpty() || email1.isEmpty()) {
                    JOptionPane.showMessageDialog(UserLogin.this, "Please fill all fields.");
                } else {
                    MenuForm menu = new MenuForm();
                    menu.setVisible(true);
                    dispose();
                }
            }
        });
    }
    public static void main(String[] args) {
        new UserLogin().setVisible(true);
    }
}