package org.example.model;

public class Artist extends Persoana implements Performant {
    private String genMuzical;
    private double tarif;

    public Artist(String nume, String genMuzical, double tarif) {
        super(nume);
        this.genMuzical = genMuzical;
        this.tarif = tarif;
    }

    public String getGenMuzical() {
        return genMuzical;
    }

    public double getTarif() {
        return tarif;
    }

    @Override
    public void afiseazaRol() {
        System.out.println(nume + " este un artist de genul " + genMuzical + "cu tariful " + tarif);
    }

    @Override
    public void perform() {
        System.out.println(nume + " performeaza live pe scena!");
    }
}
