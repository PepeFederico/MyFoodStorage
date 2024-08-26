package com.myfoodstorage.pepefederico.progettoispw_2024.boundary;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOrdine {

    private File file;
    private static final String ACTION = "Context error";
    private final Logger logger = Logger.getLogger(FileOrdine.class.getName());

    public void creaFileOrdine(OrdineBean ordineBean){
        try {
            InputStream input = new FileInputStream("risorseDB/pathFileOrdine.properties");
            Properties properties = new Properties();
            properties.load(input);

            file = new File(properties.getProperty("FILE_ORDINE"));
            FileWriter fileOrdine = new FileWriter(file);
            BufferedWriter bufferedWriter = getBufferedWriter(ordineBean, fileOrdine);
            bufferedWriter.close();
            fileOrdine.close();
        } catch (IOException e) {
            throw new RejectedExecutionException(e);
        }

    }

    private BufferedWriter getBufferedWriter(OrdineBean ordineBean, FileWriter fileOrdine) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(fileOrdine);

        for (int i = 0; i < ordineBean.getProdotti().size(); i++) {
            bufferedWriter.write(ordineBean.getProdotti().get(i).getNomeProdotto());
            bufferedWriter.write(" ");

            bufferedWriter.write(String.valueOf(ordineBean.getProdotti().get(i).getTipoAnimale()));
            bufferedWriter.write(" ");

            bufferedWriter.write(String.valueOf(ordineBean.getProdotti().get(i).getQtaRichiesta()));
            bufferedWriter.write("KG\n");
        }
        return bufferedWriter;
    }

    public void eliminaFile(){
        if(!this.file.delete()){
            logger.log(Level.SEVERE, ACTION, "Errore !!");
        }
    }
}
