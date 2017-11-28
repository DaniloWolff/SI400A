package dadosbancosfalidos;

import entidadesbancosfalidos.Banco;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DadosBancosFalidos {
    List<String> datasAnos  = new ArrayList();
    
    public Map<String, Banco> lerListaBancosFalidosCSV() {
            Map<String, Banco> listaBancos = new TreeMap();
            try {
                CSVReader reader = new CSVReader(new FileReader("files_csv/banklist.csv"), ',' , '"' , 0);

                //Lê o CSV linha por linha e usa o string array como quiser
                String[] nextLine;
                boolean headerFoiLido = false;
                
                while ((nextLine = reader.readNext()) != null) {
                    if (nextLine != null) {
                        //Ler o header
                        if (!headerFoiLido){
                            preencherDatasAnos(nextLine);
                            headerFoiLido = true;
                            continue;
                        }
                        
                        //Ler os dados 
                        Banco banco = new Banco();
                        banco.setCert(nextLine[0]);
                        banco.setNomebanco(nextLine[1]);
                        int i=2;
                        for (String nome : datasAnos) {
                            banco.adicionarDatas(nome, new Integer(nextLine[i]));
                            i++;
                        }
                        
                        //Adicionar o banco na lista que tem como KEY o CERT porque é único
                        listaBancos.put(banco.getCert(), banco);
                        
                    }
                }
                //Retornar a lista lida do arquivo CSV
               
            } 
            catch (IOException ex) {
            Logger.getLogger(DadosBancosFalidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listaBancos;
    }
    
    public List<String> obterHeader(){
        return datasAnos;
    }
    
    public void preencherDatasAnos(String[] nextLine) {
            for(String column: nextLine){
                if (!column.equals("CERT")&& !column.equals("NOME DO BANCO")){
                    datasAnos.add(column);
                }
            }
    }
}
