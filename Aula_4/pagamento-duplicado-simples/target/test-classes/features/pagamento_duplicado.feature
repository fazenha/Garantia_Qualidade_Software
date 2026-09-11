# language: pt
Funcionalidade: Bloqueio de pagamento duplicado
  Para evitar cobrar o cliente duas vezes pelo mesmo pedido
  Quero que o sistema aceite apenas o primeiro pagamento

  Cenário: Aprovar o primeiro pagamento
    Dado que o pedido ainda não foi pago
    Quando o cliente confirma o pagamento
    Então o pagamento deve ser aprovado

  Cenário: Bloquear uma segunda tentativa de pagamento
    Dado que o pedido já foi pago
    Quando o cliente confirma o pagamento novamente
    Então o pagamento deve ser rejeitado

  Cenário: Bloquear pagamentos que chegam ao mesmo tempo
    Dado que o pedido ainda não foi pago
    Quando duas tentativas de pagamento chegam ao mesmo tempo
    Então apenas uma delas deve ser aprovada
