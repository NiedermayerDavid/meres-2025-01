
package kalapacsvetes;

import java.io.*;
import java.util.*;

public class Kalapacsvetes {
    private List<Sportolo> sportolok;
    
    public Kalapacsvetes() {
        sportolok = new ArrayList<>();
    }
    
    public void beolvas() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("kalapacsvetes.txt"), 
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

