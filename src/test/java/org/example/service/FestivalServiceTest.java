package org.example.service;

import org.example.model.Artist;
import org.example.model.Bilet;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FestivalServiceTest {

    @Test
    public void testAdaugareBilete() {
        FestivalService service = new FestivalService();
        service.adaugaBilete("VIP", 300, 5);
        assertEquals(5, service.getListaBilete().size());
    }

    @Test
    public void testVanzareBileteSucces() {
        FestivalService service = new FestivalService();
        service.adaugaBilete("VIP", 300, 5);
        boolean rezultat = service.vindeBilete("VIP", 3);
        assertTrue(rezultat);
    }

    @Test
    public void testVanzareBileteInsuficiente() {
        FestivalService service = new FestivalService();
        service.adaugaBilete("VIP", 300, 2);
        boolean rezultat = service.vindeBilete("VIP", 5);
        assertFalse(rezultat);
    }

    @Test
    public void testCalculeazaProfit() {
        FestivalService service = new FestivalService();
        service.adaugaArtist(new Artist("Andra", "Pop", 1500));
        service.adaugaBilete("VIP", 300, 5);
        service.vindeBilete("VIP", 5);
        double profit = service.calculeazaProfit();
        assertEquals(0, profit);
    }

    @Test
    public void testCalculeazaCheltuieli() {
        FestivalService service = new FestivalService();
        service.adaugaArtist(new Artist("Andra", "Pop", 1500));
        assertEquals(1500, service.calculeazaBugetTotal());
    }

    @Test
    public void testFiltrareArtistiDupaGen() {
        FestivalService service = new FestivalService();
        service.adaugaArtist(new Artist("Andra", "Pop", 1500));
        service.adaugaArtist(new Artist("Smiley", "Rock", 2000));
        List<Artist> popArtisti = service.filtreazaArtistiDupaGen("Pop");
        assertEquals(1, popArtisti.size());
        assertEquals("Andra", popArtisti.get(0).getNume());
    }

    @Test
    public void testSortareArtistiAlfabetic() {
        FestivalService service = new FestivalService();
        service.adaugaArtist(new Artist("Andra", "Pop", 1500));
        service.adaugaArtist(new Artist("Smiley", "Rock", 2000));
        List<Artist> sortedArtisti = service.sorteazaArtistiAlfabetic();
        assertEquals("Andra", sortedArtisti.get(0).getNume());
    }

    @Test
    public void testSortareArtistiDupaTarif() {
        FestivalService service = new FestivalService();
        service.adaugaArtist(new Artist("Andra", "Pop", 1500));
        service.adaugaArtist(new Artist("Smiley", "Rock", 2000));
        List<Artist> sortedArtisti = service.sorteazaArtistiDupaTarif();
        assertEquals("Andra", sortedArtisti.get(0).getNume());
    }
}
