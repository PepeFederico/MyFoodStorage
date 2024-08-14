package com.myfoodstorage.pepefederico.progettoispw_2024.model;

public class Ristoratore extends Utente{
    public Ristoratore(String email,
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
        super(email, password, nome, cognome, partitaIva, viaAttivita, numeroCivico, citta, cap, nomeAttivita, numeroTelefono);
        setTipoUtente("Ristoratore");
    }
}
