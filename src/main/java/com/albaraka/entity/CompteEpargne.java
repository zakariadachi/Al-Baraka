package com.albaraka.entity;

public final class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(int id, String numero, double solde, int idClient, double tauxInteret) {
        super(id, numero, solde, idClient);
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() { return tauxInteret; }
    public void setTauxInteret(double tauxInteret) { this.tauxInteret = tauxInteret; }
}
