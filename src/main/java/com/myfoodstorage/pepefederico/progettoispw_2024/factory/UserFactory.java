package com.myfoodstorage.pepefederico.progettoispw_2024.factory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Utente;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Fornitore;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Ristoratore;

public class UserFactory {
    private static UserFactory userFactory = null;
    private UserFactory(){}
    public static synchronized UserFactory getInstance(){
        if(userFactory == null){
            userFactory = new UserFactory();
        }
        return userFactory;
    }
    public Utente getUtente(String tipoUtente,
                            String email,
                            String password,
                            String nome,
                            String cognome,
                            String partitaIva,
                            String viaAttivita,
                            int numeroCivico,
                            String citta,
                            String cap,
                            String nomeAttivita,
                            String numeroTelefono){
        return switch (tipoUtente) {
            case "Ristoratore" ->
                    new Ristoratore(email, password, nome, cognome, partitaIva, viaAttivita, numeroCivico, citta, cap, nomeAttivita, numeroTelefono);
            case "Fornitore" ->
                    new Fornitore(email, password, nome, cognome, partitaIva, viaAttivita, numeroCivico, citta, cap, nomeAttivita, numeroTelefono);
            default -> null;
        };
    }
}
