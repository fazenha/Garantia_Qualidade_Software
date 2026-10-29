# language: pt
Funcionalidade: Login

  Contexto:
    Dado que o sistema está no ar
    E existe um usuário cadastrado

  Esquema do Cenário: Tentativas de login
    Quando informo "<email>" e "<senha>"
    Então o resultado é "<resultado>"

    Exemplos:
      | email      | senha  | resultado |
      | ana@x.com  | 123456 | liberado  |
      | ana@x.com  | errada | negado    |
