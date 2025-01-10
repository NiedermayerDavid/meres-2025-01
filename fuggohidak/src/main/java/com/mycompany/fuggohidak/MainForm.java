/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fuggohidak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MainForm extends JFrame {
    private JList<String> hidakList;
    private DefaultListModel<String> listModel;
    private JTextField helyField, orszagField, hosszField, evField;
    private JRadioButton before2000Radio, after2000Radio;
    private ArrayList<Fuggohid> hidak;

    public MainForm() {
        hidak = new ArrayList<>();
        setTitle("Függőhidak");
        initComponents();
        loadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        // List panel
        listModel = new DefaultListModel<>();
        hidakList = new JList<>(listModel);
        add(new JScrollPane(hidakList), BorderLayout.WEST);

        // Details panel
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2));
        detailsPanel.add(new JLabel("Hely:"));
        helyField = new JTextField();
        detailsPanel.add(helyField);
        
        detailsPanel.add(new JLabel("Ország:"));
        orszagField = new JTextField();
        detailsPanel.add(orszagField);
        
        detailsPanel.add(new JLabel("Hossz:"));
        hosszField = new JTextField();
        detailsPanel.add(hosszField);
        
        detailsPanel.add(new JLabel("Év:"));
        evField = new JTextField();
        detailsPanel.add(evField);

        // Radio buttons
        ButtonGroup bg = new ButtonGroup();
        before2000Radio = new JRadioButton("2000 előtt épült");
        after2000Radio = new JRadioButton("2000-ben vagy után épült");
        bg.add(before2000Radio);
        bg.add(after2000Radio);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton keresesBtn = new JButton("Keresés");
        JButton kilepesBtn = new JButton("Kilépés");
        buttonPanel.add(keresesBtn);
        buttonPanel.add(kilepesBtn);

        // Add action listeners
        hidakList.addListSelectionListener(e -> updateFields());
        keresesBtn.addActionListener(e -> searchBridges());
        kilepesBtn.addActionListener(e -> dispose());

        // Layout
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(detailsPanel, BorderLayout.NORTH);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
    }

    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader("fuggohidak.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                hidak.add(new Fuggohid(parts[0], parts[1], parts[2], 
                    Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                listModel.addElement(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFields() {
        int index = hidakList.getSelectedIndex();
        if (index != -1) {
            Fuggohid hid = hidak.get(index);
            helyField.setText(hid.getHely());
            orszagField.setText(hid.getOrszag());
            hosszField.setText(String.valueOf(hid.getHossz()));
            evField.setText(String.valueOf(hid.getEpites()));
        }
    }

    private void searchBridges() {
        listModel.clear();
        for (Fuggohid hid : hidak) {
            if ((before2000Radio.isSelected() && hid.getEpites() < 2000) ||
                (after2000Radio.isSelected() && hid.getEpites() >= 2000)) {
                listModel.addElement(hid.getNev());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainForm().setVisible(true));
    }
}
