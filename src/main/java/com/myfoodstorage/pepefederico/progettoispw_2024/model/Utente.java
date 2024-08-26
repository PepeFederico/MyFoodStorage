package com.myfoodstorage.pepefederico.progettoispw_2024.model;

public abstract class Utente {
    protected String tipoUtente;
    protected String email;
    protected String password;
    protected String nome;
    protected String cognome;
    protected String partitaIva;
    protected String viaAttivita;
    protected int numeroCivico;
    protected String citta;
    protected String cap;
    protected String nomeAttivita;
    protected String numeroTelefono;

    protected Utente(String email,
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
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.partitaIva = partitaIva;
        this.viaAttivita = viaAttivita;
        this.numeroCivico = numeroCivico;
        this.citta = citta;
        this.cap = cap;
        this.nomeAttivita = nomeAttivita;
        this.numeroTelefono = numeroTelefono;
    }
    public String getTipoUtente() {
        return tipoUtente;
    }

    public void setTipoUtente(String tipoUtente) {
        this.tipoUtente = tipoUtente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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
