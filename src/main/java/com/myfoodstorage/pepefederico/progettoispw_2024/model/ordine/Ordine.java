package com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine;

import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ProdottoFornito;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.stati.*;

import java.time.LocalDate;
import java.util.*;

public class Ordine {
    private final Stato creazione;
    private final Stato checkinOut;
    private final Stato accettato;
    private final Stato rifiutato;
    private final Stato attesa;
    private Stato stato;

    private final ArrayList<Prodotto> prodottiSelezionati;
    private String contattoFornitore;
    private String nomeFornitore;
    private Date dataOrdine;
    private LocalDate dataConsegna;
    private String statoCorrente;

    public Ordine() {
        this.creazione = new Creazione(this);
        this.checkinOut = new CheckingOut(this);
        this.attesa = new Attesa(this);
        this.accettato = new Accettato(this);
        this.rifiutato = new Rifiutato(this);
        this.stato = creazione;
        this.prodottiSelezionati = new ArrayList<>();
    }

    public void setStati(Stato nuovoStato) {
        this.stato = nuovoStato;
    }

    public void creaOrdine(ArrayList<Prodotto> prodotti){
        this.stato.creaOrdine(prodotti);
    }

    public ArrayList<ProdottoFornito> elaboraOrdine(String nomeAttivita){
        return this.stato.elaboraOrdine(nomeAttivita);
    }

    public void inoltraOrdine(){
        this.stato.inoltraOrdine();
    }

    public ArrayList<Prodotto> getProdottiSelezionati() {
        return this.prodottiSelezionati;
    }
    public String getContattoFornitore() {
        return contattoFornitore;
    }
    public void setContattoFornitore(String contattoFornitore) {
        this.contattoFornitore = contattoFornitore;
    }

    public Stato getCheckinOut() {
        return checkinOut;
    }
    public Stato getAccettato() {
        return accettato;
    }
    public Stato getRifiutato() {
        return rifiutato;
    }
    public Stato getAttesa() {
        return attesa;
    }
    public Date getDataOrdine() {
        return dataOrdine;
    }
    public void setDataCorrente() {
        this.dataOrdine = new Date(System.currentTimeMillis());
    }
    public void setStatoCorrente(String statoCorrente) {
        this.statoCorrente = statoCorrente;
    }
    public String getStatoCorrente() {
        return statoCorrente;
    }
    public String getNomeFornitore() {
        return nomeFornitore;
    }
    public void setNomeFornitore(String nomeFornitore) {
        this.nomeFornitore = nomeFornitore;
    }
    public LocalDate getDataConsegna() {
        return dataConsegna;
    }
    public void setDataConsegna() {
        LocalDate date = LocalDate.now();
        this.dataConsegna = date.plusDays(7);
    }
}
