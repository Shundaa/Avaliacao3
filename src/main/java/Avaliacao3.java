
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import utfpr.ct.dainf.if62c.avaliacao.Lancamento;
import utfpr.ct.dainf.if62c.avaliacao.ProcessaLancamentos;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */
public class Avaliacao3 {
    static int conta;
    static List<Lancamento> listlanc = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(System.in); 
        ProcessaLancamentos processor;
        
        System.out.println("Caminho");
        String caminho = scanner.next();
        
        processor = new ProcessaLancamentos(caminho);
        listlanc = processor.getLancamentos();
        
        System.out.println("Conta");
        
        while(scanner.hasNextInt()){
            int ver1=0;
            try{
            conta = scanner.nextInt();
            }
            catch( InputMismatchException e){
                System.out.println("Por favor, informe um valor numérico");
                ver1=1;
            }
        if(conta != 0 && ver1==0){
                Avaliacao3.exibeLancamentosConta(listlanc, conta);
            }
        }
        
    }
    
    public static void exibeLancamentosConta(List<Lancamento> lancamentos, Integer conta) {
        Lancamento temp = new Lancamento(conta, null, null, null);
        List<Lancamento> listconta = new ArrayList<>();
        boolean ver = true;
        
        int indice = lancamentos.indexOf(temp);
        
        if(indice >= 0){
            listconta.add(lancamentos.get(indice));
            indice++;
            while(lancamentos.get(indice).getConta() ==  lancamentos.get(indice-1).getConta()){
                listconta.add(lancamentos.get(indice));
                indice++;
            }
            for(Lancamento l: listconta){
                        System.out.println(l);
                    }
       
        }    
        else
            System.out.println("Conta inexistente.");
        
    }
    
 
}