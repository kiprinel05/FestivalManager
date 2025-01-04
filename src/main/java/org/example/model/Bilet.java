package org.example.model;

public class Bilet {
    private String tip; // vip, standard, backstage
    private double pret;
    private boolean vandut;

    public Bilet(String tip, double pret) {
        this.tip = tip;
        this.pret = pret;
        this.vandut = false;
    }

    public String getTip() {
        return tip;
    }

    public double getPret() {
        return pret;
    }

    public boolean isVandut() {
        return vandut;
    }

    public void vindeBilet() {
        this.vandut = true;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "tip='" + tip + '\'' +
                ", pret=" + pret +
                ", vandut=" + (vandut ? "Da" : "Nu") +
                '}';
    }
}
