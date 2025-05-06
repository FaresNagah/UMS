/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ums.gui;

import javax.swing.*;

public class CollegeForm {
    public void display() {
        JFrame frame = new JFrame("College Information");
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JLabel collegeLabel = new JLabel("College Name:");
        JTextField collegeField = new JTextField();

        JButton saveButton = new JButton("Save");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(collegeLabel);
        panel.add(collegeField);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}