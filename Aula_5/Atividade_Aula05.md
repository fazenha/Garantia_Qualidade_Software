# Atividade Prática: Code Review de Pull Request

## Garantia da Qualidade de Software (GQS) — Aula 05

**Tema:** Inspeção de código e classificação de defeitos

*Prof. Flávio Copola Azenha*

---

## 1. Contexto

Vocês é parte do time de QA da fintech **PayFast**. A equipe de desenvolvimento acabou de abrir o **Pull Request #104**, contendo a implementação do cálculo de juros e o registro do histórico de transações do **Pix Parcelado**.

Como o sistema lida com dados bancários e dinheiro real de clientes, a empresa exige que nenhum código vá para a branch principal sem passar por um **Code Review baseado no checklist de inspeção de código**.

---

## 2. Código do Pull Request #104 (`CalculadoraJurosPix.java`)

```java
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
```

---

## 3. Critérios para a revisão

Apliquem os três critérios abaixo — todos já vistos na Aula 05 — para analisar o código.

### 3.1 Checklist de revisão

- [ ] O código atende à descrição registrada no Pull Request?
- [ ] Existem testes automatizados cobrindo a alteração?
- [ ] Os casos de borda (nulo, vazio, zero, negativo) foram tratados?
- [ ] Há exposição de dados sensíveis em logs ou commits?
- [ ] Os nomes de variáveis e funções comunicam claramente sua finalidade?
- [ ] O código está em conformidade com os padrões de estilo da equipe?
- [ ] Não há duplicação evidente que poderia ser extraída em uma função?

### 3.2 Tipos de defeito

| Tipo | Descrição |
| :--- | :--- |
| Lógicos | Condições invertidas, laços incorretos, casos de borda não tratados |
| Segurança | Dados sensíveis expostos, falta de validação de entrada |
| Performance | Laços ineficientes, consultas custosas, uso excessivo de memória |
| Manutenibilidade | Código duplicado, funções gigantes, nomes pouco claros |
| Estilo e Padrões | Inconsistência com as convenções do time ou da linguagem |
| Documentação | Comentários ausentes, desatualizados ou enganosos |

### 3.3 Severidade

| Severidade | Critério | Ação |
| :--- | :--- | :--- |
| Crítico | Sistema trava ou perde dados do usuário | Bloqueia o lançamento — corrigir imediatamente |
| Alto | Funcionalidade principal falha em cenário comum | Corrigir antes do lançamento |
| Médio | Funcionalidade secundária com comportamento incorreto | Priorizar no próximo ciclo |
| Baixo | Problema visual ou de texto, sem impacto funcional | Registrar e agendar |

---

## 4. Tarefa

1. Aplique o checklist da seção 3.1 ao código do Pull Request.
2. Para cada defeito encontrado, preencha a tabela abaixo.
3. Na coluna **Quem detecta?**, indiquem se o defeito seria pego por uma ferramenta de análise estática (PMD/Checkstyle), apenas por um revisor humano, ou por ambos.
4. Escreva um comentário construtivo, como se estivessem respondendo ao desenvolvedor diretamente no Pull Request.

### Tabela de defeitos

| Defeito identificado | Tipo | Severidade | Quem detecta? | Comentário para o Pull Request |
| :--- | :--- | :--- | :--- | :--- |
| Defeito 1 |  |  |  |  |
| Defeito 2 |  |  |  |  |
| Defeito 3 |  |  |  |  |
|Defeito n |  |  |  |  |

---


