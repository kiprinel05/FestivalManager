package org.example.model;

public abstract class Persoana {
    protected String nume;

    public Persoana(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public abstract void afiseazaRol();
}
