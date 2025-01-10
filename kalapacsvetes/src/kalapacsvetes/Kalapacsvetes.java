
package kalapacsvetes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Kalapacsvetes {
    private List<Sportolo> sportolok;
    
    public Kalapacsvetes() {
        sportolok = new ArrayList<>();
    }
    
    public void beolvas() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("C:\\Users\\nidid\\Desktop\\meres-2025-01\\meres-2025-01\\kalapacsvetes\\src\\kalapacsvetes\\kalapacsvetes.txt"), 
                        StandardCharsets.UTF_8))) {
            
            String fejlec = br.readLine(); // Fejléc beolvasása
            String sor;
            while ((sor = br.readLine()) != null) {
                String[] adatok = sor.split(";");
                sportolok.add(new Sportolo(adatok));
            }
        } catch (Exception e) {
            System.out.println("Hiba a fájl beolvasásakor: " + e.getMessage());
        }
    }
     public void feladat4() {
        System.out.println("4. feladat: Dobások száma: " + sportolok.size());
    }
    
    public void feladat5() {
        double osszeg = 0;
        int db = 0;
        
        for (Sportolo s : sportolok) {
            if (s.getOrszagKod().equals("HUN")) {
                osszeg += s.getEredmeny();
                db++;
            }
        }
        
        if (db > 0) {
            double atlag = osszeg / db;
            System.out.printf("5. feladat: Magyar sportolók átlaga: %.2f méter%n", atlag);
        } else {
            System.out.println("5. feladat: Nincs magyar sportoló az adatok között");
        }
    }
    
    public void feladat6() {
        Scanner sc = new Scanner(System.in);
        System.out.print("6. feladat: Kérem az évszámot: ");
        int ev = sc.nextInt();
        
        List<String> evbenDobtak = new ArrayList<>();
        for (Sportolo s : sportolok) {
            if (s.getEv() == ev) {
                evbenDobtak.add(s.getNev());
            }
        }
        
        if (evbenDobtak.isEmpty()) {
            System.out.println("\tEbben az évben nem került be dobás az első 25 közé");
        } else {
            System.out.println("\tEbben az évben " + evbenDobtak.size() + " dobás került be a legjobbak közé");
            System.out.println("\tDobók:");
            for (String nev : evbenDobtak) {
                System.out.println("\t\t" + nev);
            }
        }
    }
    
    public void feladat7() {
        Map<String, Integer> statisztika = new HashMap<>();
        
        for (Sportolo s : sportolok) {
            String orszag = s.getOrszagKod();
            statisztika.put(orszag, statisztika.getOrDefault(orszag, 0) + 1);
        }
        
        System.out.println("7. feladat: Statisztika");
        for (Map.Entry<String, Integer> entry : statisztika.entrySet()) {
            System.out.printf("\t%s - %d dobás%n", entry.getKey(), entry.getValue());
        }
    }
    
    public void feladat8() {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("magyarok.txt"), 
                        StandardCharsets.UTF_8))) {
            
            for (Sportolo s : sportolok) {
                if (s.getOrszagKod().equals("HUN")) {
                    bw.write(s.toString());
                    bw.newLine();
                }
            }
            System.out.println("8. feladat: magyarok.txt állomány elkészült");
        } catch (Exception e) {
            System.out.println("Hiba a fájl írásakor: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Kalapacsvetes k = new Kalapacsvetes();
        k.beolvas();
        k.feladat4();
        k.feladat5();
        k.feladat6();
        k.feladat7();
        k.feladat8();
    }
}

