package com.mycompany.pmd.checkstyle;

//import java.util.logging.Level;
//import java.util.logging.Logger;


/*
 * Os 5 problemas propositais continuam no código — é isso que o PMD deve
 * encontrar ao rodar "mvn pmd:check". Cada comentário numerado explica o
 * problema e a correção sugerida
 */
public class EmprestimoService {

    /*
        (1) UnusedPrivateField — campo declarado e nunca utilizado.
        CORREÇÃO: remover o campo, já que ele não é lido em nenhum lugar
        da classe. Se fosse necessário, o certo seria de fato usá-lo (ex.:
        registrar essa mensagem em log), não apenas declará-lo.
    */
    //private static final Logger LOGGER = Logger.getLogger(EmprestimoService.class.getName());
    
    private String mensagemDebug = "DEBUG_MODE_ATIVO";

    public boolean registrarEmprestimo(int atraso, boolean disponivel) {

        /*
            (2) UnusedLocalVariable / DataflowAnomalyAnalysis — variável
            declarada e nunca lida (as duas violações vêm da mesma causa).
            CORREÇÃO: usar a variável de fato no cálculo da multa, por
            exemplo "double multaTotal = atraso * multaDiaria;" — ou
            transformá-la em uma constante de classe, se o valor for fixo.
        */
        double multaDiaria = 2.50;

        /*
            (3) Não é um defeito apontado pelo PMD — é a regra de negócio
            do sistema (tolerância de 7 dias de atraso). Vale perguntar se
            o "7" deveria ser uma constante nomeada, em vez de um
            número mágico solto no meio do código?
        */
        
        if (atraso > 0 && atraso <= 7) {
            
            //double multaDiaria = 2.50;
            //double multaTotal = atraso * multaDiaria;
            //LOGGER.log(Level.INFO, "Multa calculada: R$ {0}", multaTotal);
            return disponivel;
        }

        try {
            salvarRegistro();
        } catch (Exception e) {
            /*
                (4) EmptyCatchBlock — exceção capturada e silenciada.
                CORREÇÃO: nunca deixar o catch vazio. Registrar o erro em
                log, por exemplo:
            */
            //LOGGER.log(Level.SEVERE, "Falha ao salvar o registro.", e);
            
        }

        /*
            (5) SystemPrintln — uso de System.out.println em produção.
            CORREÇÃO: substituir por um logger configurado, por exemplo
            java.util.logging (LOGGER.info("..."));
            ou SLF4J
        */
        //LOGGER.info("Empréstimo processado."); 
        
       System.out.println("Empréstimo processado.");
        return false;
    }

    private void salvarRegistro() {
        // simula gravação em banco de dados
    }
}
