package entidadesbancosfalidos;

import dadosbancosfalidos.DadosBancosFalidos;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InformacoesBanco {

    private final Map<String, Banco> listaBancos;
    private final DadosBancosFalidos bancoDadosPersist;
    public final List<String> datasAno;


    public InformacoesBanco() {
        bancoDadosPersist = new DadosBancosFalidos();
        listaBancos = bancoDadosPersist.lerListaBancosFalidosCSV();
        datasAno = bancoDadosPersist.obterHeader();
        
    }
    
    
    public int obterNumeroBancos(){
        return this.listaBancos.size();
    }
    
 
    public Map<String, Banco> buscarBancos(String nomeBuscado){
        Map<String, Banco> bancosBuscados = new TreeMap();
        for (Map.Entry<String, Banco> entry : this.listaBancos.entrySet()) {
            String nomeBanco =entry.getValue().getNomebanco().toLowerCase();
            if (nomeBanco.contains(nomeBuscado)){
                 bancosBuscados.put(entry.getKey(), entry.getValue());
            }

           
        }

        return bancosBuscados;
    }
    
    public Banco buscarUmBanco(String cert){
        return listaBancos.get(cert);
    }

}
