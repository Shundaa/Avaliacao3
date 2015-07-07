package utfpr.ct.dainf.if62c.avaliacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */
public class ProcessaLancamentos {
    private BufferedReader reader;
    private List<Lancamento> lancamentos;


    public ProcessaLancamentos(File arquivo) throws FileNotFoundException {
         reader = new BufferedReader(new FileReader(arquivo));
    }

    public ProcessaLancamentos(String path) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(new File(path)));
    }
    
    private String getNextLine() throws IOException {
        return reader.readLine();
    }
    
    private Lancamento processaLinha(String linha) {
        String descricao;
        double valor;
        Integer ano, mes, dia, conta;
        
        conta = Integer.parseInt(linha.substring(0, 6));
        ano = Integer.parseInt(linha.substring(6, 10));
        mes = Integer.parseInt(linha.substring(10, 12));
        dia = Integer.parseInt(linha.substring(12, 14));
        descricao = linha.substring(14, 74).trim();
        valor = Long.valueOf(linha.substring(74, 86))/100;
        
        GregorianCalendar novadata = new GregorianCalendar(ano, mes-1, dia);
        Date data = novadata.getTime();
        
        return new Lancamento(conta, data, descricao, valor);
    }
    
    private Lancamento getNextLancamento() throws IOException {
        String linha = this.getNextLine();
        
        if(linha != null) return this.processaLinha(linha);
        else return null;
    }
    
    public List<Lancamento> getLancamentos() throws IOException {
        Lancamento atual;
        lancamentos = new ArrayList<>();
        
        while((atual = getNextLancamento()) != null){
            lancamentos.add(atual);
        }
        
        reader.close();
        
        Collections.sort(lancamentos, new LancamentoComparator());
        
        return lancamentos;
    }
    
}
