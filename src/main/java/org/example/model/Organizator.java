package org.example.model;

public class Organizator extends Persoana {
    public Organizator(String nume) {
        super(nume);
    }

    @Override
    public void afiseazaRol() {
        System.out.println(nume + " este un organizator al festivalului.");
    }
}