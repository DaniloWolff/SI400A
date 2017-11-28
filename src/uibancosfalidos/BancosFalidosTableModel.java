package uibancosfalidos;

import entidadesbancosfalidos.Banco;
import entidadesbancosfalidos.InformacoesBanco;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.table.AbstractTableModel;

public class BancosFalidosTableModel extends AbstractTableModel {
    private final String[] colunas;
    private final String[] listaCERTs;
    Map<String, Banco> listaBancos;
    InformacoesBanco tblAnosBancos;
    private int anoParametro=2017;
    
    public BancosFalidosTableModel(InformacoesBanco anosBancos, Banco banco) {
        this.tblAnosBancos = anosBancos;
        
        int numeroColunas = tblAnosBancos.datasAno.size() + 2;
        colunas = new String[numeroColunas];
       
        //Preencher Colunas
        colunas[0] = "CERT";
        colunas[1] = "NOME DO BANCO";
        int i=2;
        for (String anoBanco : this.tblAnosBancos.datasAno){
            colunas[i]= anoBanco;
            i++;
        }
        
        //Obter filas
        this.listaBancos = new TreeMap<>();
        listaBancos.put(banco.getCert(), banco);
        listaCERTs = listaBancos.keySet().toArray(new String[listaBancos.size()]);
    }

    
    public BancosFalidosTableModel(InformacoesBanco anoBancos, Map<String, Banco> listaBancos, String anofalido, int ano) {
        this.tblAnosBancos = anoBancos;
        int numeroColunas;
        this.anoParametro = ano;
        //Todas as datas
        if (anofalido.equals("TODAS")){
            numeroColunas = tblAnosBancos.datasAno.size() + 2;
            colunas = new String[numeroColunas];
            //Preencher Colunas CERT, NOME e lista de todas as datas
            colunas[0] = "CERT";
            colunas[1] = "NOME DO BANCO";
            int i=2;
            for (String anoBanco : this.tblAnosBancos.datasAno){
                colunas[i]= anoBanco;
                i++;
            }
        }
        //Uma data só
        else{
            numeroColunas = 3;
            colunas = new String[numeroColunas];
            colunas[0] = "CERT";
            colunas[1] = "NOME DO BANCO";
            colunas[2] = anofalido;
        }
        
        
        //Obter filas
        this.listaBancos = listaBancos;
        listaCERTs = listaBancos.keySet().toArray(new String[listaBancos.size()]);
    }

    @Override
    public int getRowCount() {
        return this.listaCERTs.length;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int num){
        return this.colunas[num];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String keyCert = this.listaCERTs[rowIndex]; 
        switch(columnIndex){
            case 0: return this.listaBancos.get(keyCert).getCert();
            case 1: return this.listaBancos.get(keyCert).getNomebanco();
            
        }   
        String dataAnoColuna = this.colunas[columnIndex];
        int ano = this.listaBancos.get(keyCert).retornarNota(dataAnoColuna);            
        if (this.anoParametro >=ano){
            return ano;
        }
        else return null;
    }
}
