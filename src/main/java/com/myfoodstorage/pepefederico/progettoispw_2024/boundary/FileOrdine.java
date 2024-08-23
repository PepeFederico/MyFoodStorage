package com.myfoodstorage.pepefederico.progettoispw_2024.boundary;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOrdine {

    private File file;
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(FileOrdine.class.getName());

    public void creaFileOrdine(OrdineBean ordineBean){
        try {
            file = new File("D:\\ProgrammiJava\\progettoISPW_2024\\ordini\\ordine.txt");
            FileWriter fileOrdine = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOrdine);

            for (int i = 0; i < ordineBean.getProdotti().size(); i++) {
                bufferedWriter.write(ordineBean.getProdotti().get(i).getNomeProdotto());
                bufferedWriter.write(" ");

                bufferedWriter.write(String.valueOf(ordineBean.getProdotti().get(i).getTipoAnimale()));
                bufferedWriter.write(" ");

                bufferedWriter.write(String.valueOf(ordineBean.getProdotti().get(i).getQtaRichiesta()));
                bufferedWriter.write("KG\n");
            }
            bufferedWriter.close();
            fileOrdine.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void eliminaFile(){
        if(!this.file.delete()){
            logger.log(Level.SEVERE, ACTION, "Errore !!");
        }
    }
}
