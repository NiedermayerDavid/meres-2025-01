/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kalapacsvetes;

public class Sportolo {
    private int helyezes;
    private double eredmeny;
    private String nev;
    private String orszagKod;
    private String helyszin;
    private String datum;
    
    public Sportolo(String[] adatok) {
        this.helyezes = Integer.parseInt(adatok[0]);
        this.eredmeny = Double.parseDouble(adatok[1].replace(",", "."));
        this.nev = adatok[2];
        this.orszagKod = adatok[3];
        this.helyszin = adatok[4];
        this.datum = adatok[5];
    }
    
    public int getHelyezes() {
        return helyezes;
    }
    
    public double getEredmeny() {
        return eredmeny;
    }
    
    public String getNev() {
        return nev;
    }
    
    public String getOrszagKod() {
        return orszagKod;
    }
    
    public String getHelyszin() {
        return helyszin;
    }
    
    public String getDatum() {
        return datum;
    }
    
    public int getEv() {
        return Integer.parseInt(datum.split("\\.")[0]);
    }
    
    @Override
    public String toString() {
        return String.format("%d;%.2f;%s;%s;%s;%s", 
                helyezes, eredmeny, nev, orszagKod, helyszin, datum);
    }
}

