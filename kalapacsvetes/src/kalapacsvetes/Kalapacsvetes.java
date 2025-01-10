
package kalapacsvetes;

import java.io.*;
import java.util.*;

public class Kalapacsvetes {
    private List<Sportolo> sportolok = new ArrayList<>();
    
    public void beolvas() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nidid\\Desktop\\meres-2025-01\\meres-2025-01\\kalapacsvetes\\src\\kalapacsvetes\\kalapacsvetes.txt"))) {
            String sor;
            br.readLine(); // első sort átugorjuk
            while ((sor = br.readLine()) != null) {
                sportolok.add(new Sportolo(sor));
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
            .mapToDouble(Sportolo::getEredmeny)
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

    public static void main(String[] args) {
        Kalapacsvetes k = new Kalapacsvetes();
        k.beolvas();
        System.out.println("Dobások száma: " + k.getDobaszam());
        System.out.printf("Magyar átlag: %.2f\n", k.getMagyarAtlag());
        k.magyarokMentese();
    }
}

