
package com.mycompany.fuggohidak;


public class Fuggohid implements Comparable<Fuggohid> {
    private int helyezes;
    private String nev;
    private String helyrajziHely;
    private String orszag;
    private int hossz;
    private int atadasEve;

    // Konstruktor
    public Fuggohid(int helyezes, String nev, String helyrajziHely, String orszag, int hossz, int atadasEve) {
        this.helyezes = helyezes;
        this.nev = nev;
        this.helyrajziHely = helyrajziHely;
        this.orszag = orszag;
        this.hossz = hossz;
        this.atadasEve = atadasEve;
    }

    // Getterek és setterek
    public int getHelyezes() {
        return helyezes;
    }

    public void setHelyezes(int helyezes) {
        this.helyezes = helyezes;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getHelyrajziHely() {
        return helyrajziHely;
    }

    public void setHelyrajziHely(String helyrajziHely) {
        this.helyrajziHely = helyrajziHely;
    }

    public String getOrszag() {
        return orszag;
    }

    public void setOrszag(String orszag) {
        this.orszag = orszag;
    }

    public int getHossz() {
        return hossz;
    }

    public void setHossz(int hossz) {
        this.hossz = hossz;
    }

    public int getAtadasEve() {
        return atadasEve;
    }

    public void setAtadasEve(int atadasEve) {
        this.atadasEve = atadasEve;
    }

    // Összehasonlító metódus a helyezés szerinti rendezéshez
    @Override
    public int compareTo(Fuggohid masikHid) {
        return Integer.compare(this.helyezes, masikHid.helyezes);
    }

    // ToString metódus az adatok megjelenítéséhez
    @Override
    public String toString() {
        return String.format("%d. %s (%s, %s) - %d méter, átadva: %d", 
            helyezes, nev, helyrajziHely, orszag, hossz, atadasEve);
    }

    public static void main(String[] args) {
        // Példa egy híd létrehozására a táblázatból
        Fuggohid elsoHid = new Fuggohid(1, "1915 Çanakkale híd", "Dardanellák", "Törökország", 2023, 2022);
        System.out.println(elsoHid);

        // GUI indítása
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                FuggohidGUI gui = new FuggohidGUI();
                gui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}



