package org.example.ui;

import org.example.model.Artist;
import org.example.service.FestivalService;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FestivalService service = new FestivalService();
        service.incarcaDateDinFisier();

        while (true) {
            System.out.println("\n---- Meniu Principal ----");
            System.out.println("1. Adauga Artist");
            System.out.println("2. Afiseaza Artisti");
            System.out.println("3. Calculeaza Buget Total");
            System.out.println("4. Adauga Bilete");
            System.out.println("5. Afiseaza Bilete");
            System.out.println("6. Vinde Bilete");
            System.out.println("7. Calculeaza Venituri");
            System.out.println("8. Calculeaza Profit");
            System.out.println("9. Filtrare Artisti dupa Gen Muzical");
            System.out.println("10. Sortare Artisti Alfabetic");
            System.out.println("11. Sortare Artisti dupa Tarif");
            System.out.println("12. Iesire");
            System.out.print("Alege o optiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    System.out.print("Nume Artist: ");
                    String nume = scanner.nextLine();
                    System.out.print("Gen Muzical: ");
                    String gen = scanner.nextLine();
                    System.out.print("Tarif: ");
                    double tarif = scanner.nextDouble();
                    service.adaugaArtist(new Artist(nume, gen, tarif));
                    service.salveazaDateInFisier();
                    break;
                case 2:
                    service.afiseazaArtisti();
                    break;
                case 3:
                    System.out.println("Buget Total: " + service.calculeazaBugetTotal());
                    break;
                case 4:
                    System.out.print("Tip bilet (VIP/Standard/Backstage): ");
                    String tipBilet = scanner.nextLine();
                    System.out.print("Pret: ");
                    double pret = scanner.nextDouble();
                    System.out.print("Numar bilete: ");
                    int numarBilete = scanner.nextInt();
                    service.adaugaBilete(tipBilet, pret, numarBilete);
                    service.salveazaDateInFisier();
                    break;
                case 5:
                    System.out.println("--- Bilete ---");
                    System.out.println("Vandute:");
                    service.afiseazaBilete(true);
                    System.out.println("\nIn Stoc:");
                    service.afiseazaBilete(false);
                    break;
                case 6:
                    System.out.print("Tip bilet de vandut (VIP/Standard/Backstage): ");
                    String tipBiletDeVandut = scanner.nextLine();
                    System.out.print("Numar bilete de vandut: ");
                    int bileteDeVandut = scanner.nextInt();
                    service.vindeBilete(tipBiletDeVandut, bileteDeVandut);
                    service.salveazaDateInFisier();
                    break;
                case 7:
                    System.out.println("Venituri totale: " + service.calculeazaVenituri());
                    break;
                case 8:
                    System.out.println("Profit total: " + service.calculeazaProfit());
                    break;
                case 9:
                    System.out.print("Introdu genul muzical pentru filtrare: ");
                    String genFiltru = scanner.nextLine();
                    List<Artist> artistiFiltrati = service.filtreazaArtistiDupaGen(genFiltru);
                    artistiFiltrati.forEach(artist -> System.out.println(artist.getNume()));
                    break;
                case 10:
                    System.out.println("Artisti sortati alfabetic:");
                    service.sorteazaArtistiAlfabetic().forEach(artist -> System.out.println(artist.getNume()));
                    break;
                case 11:
                    System.out.println("Artisti sortati dupa tarif:");
                    service.sorteazaArtistiDupaTarif().forEach(artist ->
                            System.out.println(artist.getNume() + " - " + artist.getTarif()));
                    break;
                case 12:
                    service.salveazaDateInFisier();
                    System.out.println("Bafta!");
                    return;
                default:
                    System.out.println("Optiune invalida!");
                    break;
            }
        }
    }
}
