package com.myfoodstorage.pepefederico.progettoispw_2024.dao;

import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ZeroOrderException;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.OrdineFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ProdottoFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.TipoAnimale;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Ordine;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class OrdineDAO {
    private int countOrdini = 0;
    private final File directoryName = new File("D:\\ProgrammiJava\\progettoISPW_2024\\ordineProdotti");
    private ArrayList<Ordine> ordine;
    private ArrayList<Prodotto> listaOrdine;

    public void salvaOrdine(Ordine ordine, String stato, String nomeFornitore){
        try {
            String fileName = nomeOrdine();
            String pathName = "D:\\ProgrammiJava\\progettoISPW_2024\\ordineProdotti\\"+fileName;

            File file = new File(pathName);

            FileWriter fileOrdine = new FileWriter(file);
            BufferedWriter bufferedWriter = getBufferedWriter(ordine, fileOrdine, stato, nomeFornitore);

            bufferedWriter.close();
            fileOrdine.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recuperaInfoOrdini() throws ZeroOrderException {
        countFile();

        if(this.countOrdini == 0)
            throw new ZeroOrderException("Non hai effettuato nessun ordine !!");

        ordine = new ArrayList<>();
        for(int i = 0; i < this.countOrdini; i++){
            ordine.add(OrdineFactory.getInstance().getOrdine());

            switch(letturaStatoOrdine(i)){
                case "Accettato":
                    ordine.get(i).setStati(ordine.get(i).getAccettato());
                    break;

                case "Rifiutato":
                    ordine.get(i).setStati(ordine.get(i).getRifiutato());
                    break;

                default:
                    ordine.get(i).setStati(ordine.get(i).getAttesa());
            }

            ordine.get(i).setNomeFornitore(letturaFornitore(i));
            letturaOrdine(i);

            ordine.get(i).creaOrdine(listaOrdine);
        }

    }

    private String letturaStatoOrdine(int i){
        String nomeOrdineFile = directoryName + "\\" + "ordine_" + i +".txt";
        String stato = "";
        try{
            FileReader file = new FileReader(nomeOrdineFile);
            BufferedReader bufferedReader = new BufferedReader(file);

            String line = bufferedReader.readLine();
            StringTokenizer obj = new StringTokenizer(line, " ");

            while(obj.hasMoreTokens()){
                stato = switch (obj.nextToken()) {
                    case "Rifiutato"    -> obj.nextToken();
                    case "Accettato"    -> obj.nextToken();
                    default             -> obj.nextToken();
                };
            }
            bufferedReader.close();
            file.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stato;
    }

    private String letturaFornitore(int i){
        String nomeOrdineFile = directoryName + "\\" + "ordine_" + i +".txt";
        try{
            FileReader file = new FileReader(nomeOrdineFile);
            BufferedReader bufferedReader = new BufferedReader(file);

            String line = bufferedReader.readLine();
            line = bufferedReader.readLine();
            return line;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void letturaOrdine(int i){
        String nomeOrdineFile = directoryName + "\\" + "ordine_" + i +".txt";
        listaOrdine = new ArrayList<>();
        int count;

        try {
            FileReader file = new FileReader(nomeOrdineFile);
            BufferedReader bufferedReader = new BufferedReader(file);

            String s = bufferedReader.readLine();
            s = bufferedReader.readLine();

            while(true){
                s = bufferedReader.readLine();
                if(s == null) break;

                StringTokenizer obj = new StringTokenizer(s, " ");
                Prodotto prodotto = ProdottoFactory.getInstance().getProdotto();
                count = 0;
                while(obj.hasMoreTokens()){
                    String token = obj.nextToken();
                    switch (count){
                        case 0:
                            prodotto.setNomeProdotto(token);
                            break;

                        case 1:
                            prodotto.setTipoAnimale(TipoAnimale.valueOf(token));
                            break;

                        default:
                            prodotto.setScorte(Integer.parseInt(String.valueOf(token.charAt(0))));
                    }
                    count++;
                }
                listaOrdine.add(prodotto);
            }
            bufferedReader.close();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedWriter getBufferedWriter(Ordine ordine, FileWriter fileOrdine, String stato, String noneFornitore) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(fileOrdine);

        bufferedWriter.write(ordine.getDataOrdine() + " Stato: " + stato);
        bufferedWriter.newLine();
        bufferedWriter.write("Verso: " + noneFornitore);
        bufferedWriter.newLine();

        for (int i = 0; i < ordine.getProdottiSelezionati().size(); i++) {
            bufferedWriter.write(ordine.getProdottiSelezionati().get(i).getNomeProdotto());
            bufferedWriter.write(" ");

            bufferedWriter.write(String.valueOf(ordine.getProdottiSelezionati().get(i).getTipoAnimale()));
            bufferedWriter.write(" ");

            bufferedWriter.write(String.valueOf(ordine.getProdottiSelezionati().get(i).getScorte()));
            bufferedWriter.write("KG\n");
        }
        return bufferedWriter;
    }

    private String nomeOrdine(){
        countFile();
        return "ordine_"+ this.countOrdini+".txt";
    }

    private void countFile(){
        this.countOrdini = Objects.requireNonNull(directoryName.list()).length;
    }

    public ArrayList<Ordine> getOrdine() {
        return ordine;
    }
}
