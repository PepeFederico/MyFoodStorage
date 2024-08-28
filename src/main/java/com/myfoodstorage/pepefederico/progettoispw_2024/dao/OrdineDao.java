package com.myfoodstorage.pepefederico.progettoispw_2024.dao;

import com.myfoodstorage.pepefederico.progettoispw_2024.exceptions.ZeroOrderException;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.OrdineFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.factory.ProdottoFactory;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.Prodotto;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.TipoAnimale;
import com.myfoodstorage.pepefederico.progettoispw_2024.model.ordine.Ordine;
import java.io.*;
import java.util.*;
import java.util.concurrent.RejectedExecutionException;

public class OrdineDao {
    private int countOrdini = 0;
    private File directoryName;
    private List<Ordine> ordine;
    private ArrayList<Prodotto> listaOrdine;

    public void salvaOrdine(Ordine ordine, String stato, String nomeFornitore){
        try {
            setDirectoryName();
            String fileName = nomeOrdine();
            String pathName = "D:\\ProgrammiJava\\progettoISPW_2024\\ordineProdotti\\"+fileName;

            File file = new File(pathName);

            FileWriter fileOrdine = new FileWriter(file);
            BufferedWriter bufferedWriter = getBufferedWriter(ordine, fileOrdine, stato, nomeFornitore);

            bufferedWriter.close();
            fileOrdine.close();
        } catch (IOException e) {
            throw new RejectedExecutionException(e);
        }
    }

    public void recuperaInfoOrdini() throws ZeroOrderException, RejectedExecutionException {
        setDirectoryName();
        countFile();

        try {

            if (this.countOrdini == 0)
                throw new ZeroOrderException("Non hai effettuato nessun ordine !!");

            ordine = new ArrayList<>();
            for (int i = 0; i < this.countOrdini; i++) {
                ordine.add(OrdineFactory.getInstance().getOrdine());
                letturaStatoOrdine(i);

                ordine.get(i).setNomeFornitore(letturaFornitore(i));
                letturaOrdine(i);

                ordine.get(i).creaOrdine(listaOrdine);
            }
        }catch (RejectedExecutionException e){
            throw new ZeroOrderException(e.getMessage());
        }
    }

    public boolean eliminaUltimoOrdine(){
        setDirectoryName();
        countFile();
        int ultimoFile = countOrdini - 1;

        String nomeOrdineFile = directoryName + "\\" + "ordine_" + ultimoFile +".txt";
        File file = new File(nomeOrdineFile);

        return file.delete();
    }

    private void letturaStatoOrdine(int i) throws RejectedExecutionException{
        String nomeOrdineFile = directoryName + "\\" + "ordine_" + i +".txt";
        try{
            FileReader file = new FileReader(nomeOrdineFile);
            BufferedReader bufferedReader = new BufferedReader(file);

            String line = bufferedReader.readLine();
            StringTokenizer obj = new StringTokenizer(line, " ");

            while(obj.hasMoreTokens()){
                String stato = obj.nextToken();

                switch (stato) {
                    case "Attesa"       -> ordine.get(i).setStati(ordine.get(i).getAttesa());
                    case "Rifiutato"    -> ordine.get(i).setStati(ordine.get(i).getRifiutato());
                    case "Accettato"    -> ordine.get(i).setStati(ordine.get(i).getAccettato());
                    default             -> stato = "";
                }
            }
            bufferedReader.close();
            file.close();
        }catch (IOException e) {
            throw new RejectedExecutionException(e);
        }
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
            throw new RejectedExecutionException(e);
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
                            prodotto.setTipoAnimale(TipoAnimale.valueOf(token.toUpperCase()));
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
            throw new RejectedExecutionException(e);
        }
    }

    private BufferedWriter getBufferedWriter(Ordine ordine, FileWriter fileOrdine, String stato, String nomeFornitore) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(fileOrdine);

        bufferedWriter.write(ordine.getDataOrdine() + " Stato: " + stato);
        bufferedWriter.newLine();
        bufferedWriter.write("Verso: " + nomeFornitore);
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

    private void setDirectoryName(){
        try{

            InputStream input = new FileInputStream("risorseDB/pathFileOrdine.properties");
            Properties properties = new Properties();
            properties.load(input);
            directoryName = new File(properties.getProperty("DIRECTORY_NAME"));

        } catch (IOException e) {
            throw new RejectedExecutionException(e);
        }
    }

    public List<Ordine> getOrdine() {
        return ordine;
    }
}
