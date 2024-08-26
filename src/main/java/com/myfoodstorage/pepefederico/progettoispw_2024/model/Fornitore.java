package com.myfoodstorage.pepefederico.progettoispw_2024.model;

public class Fornitore extends Utente{
    private String partitaIva;
    private String viaAttivita;
    private int numeroCivico;
    private String citta;
    private String cap;
    private String nomeAttivita;
    private String numeroTelefono;

    public Fornitore(String email,
                     String password,
                     String nome,
                     String cognome,
                     String partitaIva,
                     String viaAttivita,
                     int numeroCivico,
                     String citta,
                     String cap,
                     String nomeAttivita,
                     String numeroTelefono) {
        super(email, password, nome, cognome);
        this.partitaIva = partitaIva;
        this.viaAttivita = viaAttivita;
        this.numeroCivico = numeroCivico;
        this.citta = citta;
        this.cap = cap;
        this.nomeAttivita = nomeAttivita;
        this.numeroTelefono = numeroTelefono;
        setTipoUtente("Fornitore");
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getViaAttivita() {
        return viaAttivita;
    }

    public void setViaAttivita(String viaAttivita) {
        this.viaAttivita = viaAttivita;
    }

    public int getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getNomeAttivita() {
        return nomeAttivita;
    }

    public void setNomeAttivita(String nomeAttivita) {
        this.nomeAttivita = nomeAttivita;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
