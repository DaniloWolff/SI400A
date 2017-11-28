package entidadesbancosfalidos;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Banco {
    private String cert; 
    private String nomebanco;
    private Map<String, Integer> dataseanos;
    
    public Banco(){
        dataseanos = new TreeMap();
    }
    
    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public String getNomebanco() {
        return nomebanco;
    }

    public void setNomebanco(String nomebanco) {
        this.nomebanco = nomebanco;
    }
    
    public void adicionarDatas(String dataeano, Integer data){
        this.dataseanos.put(dataeano, data);
    }
    
    public Integer retornarNota(String dataeano){
        return this.dataseanos.get(dataeano);
    }
    
    public Map<String, Integer> getDataseanos() {
        return dataseanos;
    }
    
    @Override
    public String toString(){        
        StringBuilder sb = new StringBuilder();
        sb.append("CERT:" );
        sb.append(this.cert);
        sb.append(";");
        sb.append("NOME DO BANCO:" );
        sb.append(this.nomebanco);
        sb.append(";");

              
	for (Map.Entry<String, Integer> entry : this.dataseanos.entrySet()) {
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue());
            sb.append(";");
	}
        
        return sb.toString();

    }
    
    
}


