/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalapacsvetes;

/**
 *
 * @author nidid
 */
public class Sportolo {
    private double eredmeny;
    private String nev;
    private String orszagKod;
    private String helyszin;
    private String datum;

    public Sportolo(String sor) {
        String[] adatok = sor.split(";");
        this.eredmeny = Double.parseDouble(adatok[1]);
        this.nev = adatok[2];
        this.orszagKod = adatok[3];
        this.helyszin = adatok[4];
        this.datum = adatok[5];
    }

    public double getEredmeny() { return eredmeny; }
    public String getOrszagKod() { return orszagKod; }
    
    @Override
    public String toString() {
        return String.format("%f;%s;%s;%s;%s", eredmeny, nev, orszagKod, helyszin, datum);
    }
}

