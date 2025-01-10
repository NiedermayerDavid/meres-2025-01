
package kalapacsvetes;

import java.io.*;
import java.util.*;

public class Kalapacsvetes {
    private List<sportolo> sportolok = new ArrayList<>();
    
    public void beolvas() {
        try (BufferedReader br = new BufferedReader(new FileReader("kalapacsvetes.txt"))) {
            String sor;
            br.readLine(); // első sort átugorjuk
            while ((sor = br.readLine()) != null) {
                sportolok.add(new sportolo(sor));
            }
        } catch (IOException e) {
            System.out.println("Hiba a fájl olvasásakor: " + e.getMessage());
        }
    }

    public int getDobaszam() {
        return sportolok.size();
    }

    public double getMagyarAtlag() {
        return sportolok.stream()
            .filter(s -> s.getOrszagKod().equals("HUN"))
            .mapToDouble(sportolo::getEredmeny)
            .average()
            .orElse(0.0);
    }

    public void magyarokMentese() {
        try (PrintWriter pw = new PrintWriter("magyarok.txt")) {
            sportolok.stream()
                .filter(s -> s.getOrszagKod().equals("HUN"))
                .forEach(s -> pw.println(s.toString()));
        } catch (IOException e) {
            System.out.println("Hiba a fájl írásakor: " + e.getMessage());
        }
    }
}
