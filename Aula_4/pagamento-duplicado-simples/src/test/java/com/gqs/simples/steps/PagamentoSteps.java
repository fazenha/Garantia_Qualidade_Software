package com.gqs.simples.steps;

import com.gqs.simples.ControleDePagamento;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Uma nova instância desta classe é criada pelo Cucumber a cada cenário,
 * então "controle" começa sempre zerado (pago = false).
 */
public class PagamentoSteps {

    private final ControleDePagamento controle = new ControleDePagamento();
    private String resultado;
    private final List<String> resultadosConcorrentes = new ArrayList<>();

    @Dado("que o pedido ainda não foi pago")
    public void que_o_pedido_ainda_nao_foi_pago() {
        // Este é o estado inicial padrão — nada a fazer aqui.
        // O passo existe só para deixar o cenário legível.
    }

    @Dado("que o pedido já foi pago")
    public void que_o_pedido_ja_foi_pago() {
        controle.pagar("preparação do cenário");
    }

    @Quando("o cliente confirma o pagamento")
    public void o_cliente_confirma_o_pagamento() {
        resultado = controle.pagar("cliente");
    }

    @Quando("o cliente confirma o pagamento novamente")
    public void o_cliente_confirma_o_pagamento_novamente() {
        resultado = controle.pagar("cliente");
    }

    @Entao("o pagamento deve ser aprovado")
    public void o_pagamento_deve_ser_aprovado() {
        assertTrue(resultado.contains("APROVADO"), "Esperava aprovado, veio: " + resultado);
    }

    @Entao("o pagamento deve ser rejeitado")
    public void o_pagamento_deve_ser_rejeitado() {
        assertTrue(resultado.contains("REJEITADO"), "Esperava rejeitado, veio: " + resultado);
    }

    @Quando("duas tentativas de pagamento chegam ao mesmo tempo")
    public void duas_tentativas_de_pagamento_chegam_ao_mesmo_tempo() throws InterruptedException {
        CountDownLatch largada = new CountDownLatch(1);

        Runnable tentativaNoNavegador = () -> {
            aguardar(largada);
            String r = controle.pagar("aba do navegador");
            synchronized (resultadosConcorrentes) {
                resultadosConcorrentes.add(r);
            }
        };
        Runnable tentativaNoCelular = () -> {
            aguardar(largada);
            String r = controle.pagar("app do celular");
            synchronized (resultadosConcorrentes) {
                resultadosConcorrentes.add(r);
            }
        };

        Thread t1 = new Thread(tentativaNoNavegador);
        Thread t2 = new Thread(tentativaNoCelular);
        t1.start();
        t2.start();
        largada.countDown();
        t1.join();
        t2.join();
    }

    @Entao("apenas uma delas deve ser aprovada")
    public void apenas_uma_delas_deve_ser_aprovada() {
        long aprovados = resultadosConcorrentes.stream().filter(r -> r.contains("APROVADO")).count();
        assertEquals(1, aprovados, "Esperava exatamente 1 aprovado entre as duas tentativas simultâneas");
    }

    private void aguardar(CountDownLatch largada) {
        try {
            largada.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
