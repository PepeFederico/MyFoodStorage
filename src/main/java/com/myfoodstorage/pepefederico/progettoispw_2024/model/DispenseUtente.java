package com.myfoodstorage.pepefederico.progettoispw_2024.model;

import java.util.ArrayList;

public class DispenseUtente {
    private ArrayList<Dispensa> dispense;
    private static DispenseUtente dispenseUtente = null;

    private DispenseUtente(){
        this.dispense = null;
    }

    public static synchronized DispenseUtente getInstance(){
        if(dispenseUtente == null){
            dispenseUtente = new DispenseUtente();
        }
        return dispenseUtente;
    }

    public void setLogoutDispenseUtente() {
        this.dispense = null;
        DispenseUtente.dispenseUtente = null;
    }
    public ArrayList<Dispensa> getDispense() {
        return dispense;
    }
    public void setDispense(ArrayList<Dispensa> dispense) {
        this.dispense = dispense;
    }
}
