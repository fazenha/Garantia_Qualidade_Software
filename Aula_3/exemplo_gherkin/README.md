# Exemplo Cucumber: Login

Exemplo didático de BDD com Cucumber em Java. A especificação está em Gherkin e usa:

- `Contexto` para os passos comuns;
- `Esquema do Cenário` para executar o mesmo fluxo com dados diferentes;
- `Exemplos` para separar um login válido de um login negado.

## Executar

Na pasta deste projeto:

```bash
mvn test
```

O relatório também fica disponível em `target/cucumber.html` quando gerado pelo Cucumber.

## Roteiro rápido para a aula

1. Abra `src/test/resources/features/login.feature` e leia a regra de negócio.
2. Mostre que cada linha do cenário é ligada a um método em `LoginSteps`.
3. Execute `mvn test` e observe que as duas linhas da tabela viram dois casos.
4. Altere uma expectativa na tabela para demonstrar um teste falhando.
5. Volte ao valor correto e execute novamente.
