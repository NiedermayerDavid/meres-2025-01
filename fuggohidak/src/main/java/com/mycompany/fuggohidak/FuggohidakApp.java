/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fuggohidak;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// A függőhíd osztály definíciója
class Fuggohid {
    private String nev;
    private String hely;
    private String orszag;
    private int hossz;
    private int atadasEve;

    public Fuggohid(String nev, String hely, String orszag, int hossz, int atadasEve) {
        this.nev = nev;
        this.hely = hely;
        this.orszag = orszag;
        this.hossz = hossz;
        this.atadasEve = atadasEve;
    }

    public String getNev() {
        return nev;
    }

    public String getHely() {
        return hely;
    }

    public String getOrszag() {
        return orszag;
    }

    public int getHossz() {
        return hossz;
    }

    public int getAtadasEve() {
        return atadasEve;
    }

    @Override
    public String toString() {
        return nev;
    }
}

public class FuggohidakApp {

    private JFrame frame;
    private DefaultListModel<Fuggohid> listModel;
    private JList<Fuggohid> listBox;
    private JTextField nevField;
    private JTextField helyField;
    private JTextField orszagField;
    private JTextField hosszField;
    private JTextField atadasEveField;
    private JLabel statusLabel;

    private List<Fuggohid> hidak = new ArrayList<>();

    public FuggohidakApp() {
        // Fő ablak beállítása
        frame = new JFrame("Függőhidak");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Menü
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Fájl");
        JMenuItem openItem = new JMenuItem("Megnyitás");
        JMenuItem exitItem = new JMenuItem("Kilépés");

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        menu.add(openItem);
        menu.add(exitItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        // Fő panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        listBox = new JList<>(listModel);

        listBox.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showDetails(listBox.getSelectedValue());
            }
        });

        JScrollPane listScrollPane = new JScrollPane(listBox);
        mainPanel.add(listScrollPane, BorderLayout.CENTER);

        // Részletek panel
        JPanel detailPanel = new JPanel(new GridLayout(5, 2));
        detailPanel.setBorder(BorderFactory.createTitledBorder("Részletek"));

        detailPanel.add(new JLabel("Híd neve:"));
        nevField = new JTextField();
        nevField.setEditable(false);
        detailPanel.add(nevField);

        detailPanel.add(new JLabel("Hely:"));
        helyField = new JTextField();
        helyField.setEditable(false);
        detailPanel.add(helyField);

        detailPanel.add(new JLabel("Ország:"));
        orszagField = new JTextField();
        orszagField.setEditable(false);
        detailPanel.add(orszagField);

        detailPanel.add(new JLabel("Hossz (m):"));
        hosszField = new JTextField();
        hosszField.setEditable(false);
        detailPanel.add(hosszField);

        detailPanel.add(new JLabel("Átadás éve:"));
        atadasEveField = new JTextField();
        atadasEveField.setEditable(false);
        detailPanel.add(atadasEveField);

        mainPanel.add(detailPanel, BorderLayout.SOUTH);

        // Állapotjelző
        statusLabel = new JLabel(" ");
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void openFile() {
        File file = new File("C:\\Users\\nidid\\Desktop\\meres-2025-01\\meres-2025-01\\fuggohidak.csv"); // Itt adhatod meg a fájl elérési útját manuálisan.
        loadData(file);
    }

    private void loadData(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Fejléc átugrása
            hidak.clear();
            listModel.clear();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 5) {
                    String nev = parts[0];
                    String hely = parts[1];
                    String orszag = parts[2];
                    int hossz = Integer.parseInt(parts[3]);
                    int atadasEve = Integer.parseInt(parts[4]);

                    Fuggohid h = new Fuggohid(nev, hely, orszag, hossz, atadasEve);
                    hidak.add(h);
                    listModel.addElement(h);
                }
            }

            statusLabel.setText("Adatok sikeresen betöltve.");
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Hiba a fájl beolvasása közben!", "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDetails(Fuggohid h) {
        if (h != null) {
            nevField.setText(h.getNev());
            helyField.setText(h.getHely());
            orszagField.setText(h.getOrszag());
            hosszField.setText(String.valueOf(h.getHossz()));
            atadasEveField.setText(String.valueOf(h.getAtadasEve()));
        }
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(FuggohidakApp::new);
}
}
