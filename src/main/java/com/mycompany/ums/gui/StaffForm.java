package com.mycompany.ums.gui;

import javax.swing.*;
import java.awt.*;
import com.mycompany.ums.db.DatabaseHandler;

public class StaffForm {
    public void display() {
        JFrame frame = new JFrame("Staff Registration");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel nameLabel = new JLabel("Username:");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel idLabel = new JLabel("Staff ID:");
        JTextField idField = new JTextField();

        JLabel deptLabel = new JLabel("Department:");
        JTextField deptField = new JTextField();

        JLabel uniLabel = new JLabel("University:");
        JComboBox<String> universityCombo = new JComboBox<>(DatabaseHandler.getUniversityNames());

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
            String username = nameField.getText().trim();
            String email = emailField.getText().trim();
            String staffId = idField.getText().trim();
            String department = deptField.getText().trim();
            
            String university = (String) universityCombo.getSelectedItem();
int universityId = -1;
try {
    universityId = Integer.parseInt(university.split(" - ")[0]);
} catch (Exception ex) {
    JOptionPane.showMessageDialog(frame, "Invalid university selection.");
    return;
}


            if (!username.isEmpty() && !email.isEmpty() && !staffId.isEmpty() && !department.isEmpty() && university != null) {
                
               

                int userId = DatabaseHandler.insertUser(username, email, "staff");
                if (userId != -1) {
                    DatabaseHandler.insertStaff(staffId, userId, department, universityId);
                    JOptionPane.showMessageDialog(frame, "Staff member saved to database!");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to create user. Email or username may be duplicate.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "All fields are required!");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 5, 5)); 
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(deptLabel);
        panel.add(deptField);
        panel.add(uniLabel);
        panel.add(universityCombo);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
