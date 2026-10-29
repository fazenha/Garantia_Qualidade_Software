package br.com.aula.estacionamento;

public class Estacionamento {

    public double calcularTarifa(int minutos, double valorNota) {
        double tarifa = 12.00;
        if (minutos <= 15 || valorNota > 1000.00) {
            tarifa = 0.00;
        }
        return tarifa;
    }
}
