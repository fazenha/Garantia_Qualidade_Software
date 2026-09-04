package com.gqs.simples;

/**
 * Toda a regra de negócio deste exemplo: um pedido só pode ser pago uma vez.
 *
 * O "synchronized" garante que, se duas tentativas chegarem ao mesmo tempo,
 * apenas uma execute o bloco "ler o estado + decidir + escrever o estado"
 * por vez — sem isso, as duas poderiam ler "ainda não pago" antes de
 * qualquer uma marcar como pago.
 */
public class ControleDePagamento {

    private boolean pago = false;
    private final Object lock = new Object();

    public String pagar(String quem) {
       synchronized (lock) {
            if (pago) {
                return quem + " -> REJEITADO (pedido já foi pago)";
            }
            // Pausa de propósito, simulando um processamento real entre
            // "ler o estado" e "escrever o estado" (ex.: uma chamada a um
            // gateway de pagamento). É essa janela de tempo que o
            // synchronized protege — sem ela, o bug quase nunca aparece,
            // porque as duas linhas rodam rápido demais para as threads
            // se cruzarem.
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            pago = true;
            return quem + " -> APROVADO";
        }
    }
}