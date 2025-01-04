package org.example.service;

import org.example.model.Artist;
import org.example.model.Bilet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class FestivalService {
    private List<Artist> listaArtisti = new ArrayList<>();
    private List<Bilet> listaBilete = new ArrayList<>();
    private static final String FILE_ARTISTS = "artisti.json";
    private static final String FILE_BILETE = "bilete.json";
    private final Gson gson = new Gson();

    public void incarcaDateDinFisier() {
        try (FileReader readerArtisti = new FileReader(FILE_ARTISTS)) {
            Type artistListType = new TypeToken<List<Artist>>() {}.getType();
            listaArtisti = gson.fromJson(readerArtisti, artistListType);
        } catch (IOException e) {
            System.out.println("Nu s-au gasit date pentru artisti. Se incepe cu o lista goala.");
        }

        try (FileReader readerBilete = new FileReader(FILE_BILETE)) {
            Type biletListType = new TypeToken<List<Bilet>>() {}.getType();
            listaBilete = gson.fromJson(readerBilete, biletListType);
        } catch (IOException e) {
            System.out.println("Nu s-au gasit date pentru bilete. Se incepe cu o listă goala.");
        }
    }

    public void salveazaDateInFisier() {
        try (FileWriter writerArtisti = new FileWriter(FILE_ARTISTS)) {
            gson.toJson(listaArtisti, writerArtisti);
        } catch (IOException e) {
            System.out.println("Eroare la salvarea artistilor: " + e.getMessage());
        }

        try (FileWriter writerBilete = new FileWriter(FILE_BILETE)) {
            gson.toJson(listaBilete, writerBilete);
        } catch (IOException e) {
            System.out.println("Eroare la salvarea biletelor: " + e.getMessage());
        }
    }

    public void adaugaArtist(Artist artist) {
        listaArtisti.add(artist);
    }

    public void afiseazaArtisti() {
        for (Artist artist : listaArtisti) {
            artist.afiseazaRol();
        }
    }

    public double calculeazaBugetTotal() {
        return listaArtisti.stream().mapToDouble(Artist::getTarif).sum();
    }

    public void adaugaBilete(String tip, double pret, int numarBilete) {
        for (int i = 0; i < numarBilete; i++) {
            listaBilete.add(new Bilet(tip, pret));
        }
    }

    public List<Bilet> getListaBilete() {
        return new ArrayList<>(listaBilete); // Returnare defensivă a listei
    }

    public void afiseazaBilete(boolean doarVandute) {
        Map<String, Map<Double, Long>> groupedBilete = listaBilete.stream()
                .filter(bilet -> bilet.isVandut() == doarVandute)
                .collect(Collectors.groupingBy(
                        Bilet::getTip,
                        Collectors.groupingBy(Bilet::getPret, Collectors.counting())
                ));

        groupedBilete.forEach((tip, preturi) -> {
            preturi.forEach((pret, count) -> {
                System.out.println(tip + " - " + pret + " - " + count);
            });
        });
    }

    public boolean vindeBilete(String tip, int numarBilete) {
        int bileteVandute = 0;
        for (Bilet bilet : listaBilete) {
            if (bilet.getTip().equalsIgnoreCase(tip) && !bilet.isVandut() && bileteVandute < numarBilete) {
                bilet.vindeBilet();
                bileteVandute++;
            }
        }
        if (bileteVandute > 0) {
            System.out.println("Au fost vandute " + bileteVandute + " bilete de tip " + tip);
            return true;
        } else {
            System.out.println("Nu exista suficiente bilete disponibile pentru acest tip!");
            return false;
        }
    }

    public double calculeazaVenituri() {
        return listaBilete.stream().filter(Bilet::isVandut).mapToDouble(Bilet::getPret).sum();
    }

    public double calculeazaProfit() {
        return calculeazaVenituri() - calculeazaBugetTotal();
    }

    public List<Artist> filtreazaArtistiDupaGen(String genMuzical) {
        return listaArtisti.stream()
                .filter(artist -> artist.getGenMuzical().equalsIgnoreCase(genMuzical))
                .collect(Collectors.toList());
    }

    public List<Artist> sorteazaArtistiAlfabetic() {
        return listaArtisti.stream()
                .sorted(Comparator.comparing(Artist::getNume))
                .collect(Collectors.toList());
    }

    public List<Artist> sorteazaArtistiDupaTarif() {
        return listaArtisti.stream()
                .sorted(Comparator.comparingDouble(Artist::getTarif))
                .collect(Collectors.toList());
    }
}
