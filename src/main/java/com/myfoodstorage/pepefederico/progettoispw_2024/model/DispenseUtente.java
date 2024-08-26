package com.myfoodstorage.pepefederico.progettoispw_2024.model;

import java.util.List;

public class DispenseUtente {
    private List<Dispensa> dispense;
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
        setDispenseUtente();
    }
    public List<Dispensa> getDispense() {
        return dispense;
    }
    public void setDispense(List<Dispensa> dispense) {
        this.dispense = dispense;
    }
    private static synchronized void setDispenseUtente(){
        dispenseUtente = null;
    }
}
