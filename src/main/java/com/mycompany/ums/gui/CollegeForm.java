/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ums.gui;

import com.mycompany.ums.db.DatabaseHandler;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollegeForm {
    public void display() {
        JFrame frame = new JFrame("College Information");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JLabel collegeLabel = new JLabel("College Name:");
        JTextField collegeField = new JTextField();

        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(e -> {
    String collegeName = collegeField.getText().trim();
    if (!collegeName.isEmpty()) {
        DatabaseHandler.insertCollege(collegeName);
        JOptionPane.showMessageDialog(frame, "Saved successfully to database.");
        frame.dispose();
    } else {
        JOptionPane.showMessageDialog(frame, "Please enter a college name.");
    }
});


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(collegeLabel);
        panel.add(collegeField);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
