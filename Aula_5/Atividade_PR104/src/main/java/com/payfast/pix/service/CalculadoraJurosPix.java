package com.payfast.pix.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class CalculadoraJurosPix {

    private static final String DB_PASS = "PayFast2026_ProdPass#99";

    public double calcularEProcessar(double valor, int parcelas, String cpfCliente) {
      
        double taxa = 0.02; // 2% ao mês
        double totalComJuros = valor;

        for (int i = 0; i < parcelas; i++) {
            totalComJuros += totalComJuros * taxa;
        }

        System.out.println("LOG CRÍTICO: Processado Pix do CPF " + cpfCliente + " com valor R$ " + totalComJuros + " e senha DB: " + DB_PASS);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payfast", "root", DB_PASS);
 
        } catch (Exception e) {

        }

        return totalComJuros;
    }
}
