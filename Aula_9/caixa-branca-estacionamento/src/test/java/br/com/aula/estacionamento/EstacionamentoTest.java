package br.com.aula.estacionamento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EstacionamentoTest {
    private final Estacionamento estacionamento = new Estacionamento();

    @ParameterizedTest(name = "{0}")
    @CsvSource({
        "CT01, 10,  0.00,   0.00",
        "CT02, 120, 0.00,   12.00",
        "CT03, 120, 150.00, 0.00"
    })
    void caminhosBasicos(String id, int minutos, double valorNota, double esperado) {
        assertEquals(esperado, estacionamento.calcularTarifa(minutos, valorNota), 0.001);
    }
}
