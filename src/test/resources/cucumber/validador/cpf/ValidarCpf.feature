# language: pt
Funcionalidade: Converter Dados da Venda
  Eu como validador
  Quero validar o cpf informado
  Para que eu possa garantir a integridade da informacao

  Esquema do Cenario: Deve validar o cpf
    Dado que informei o cpf "<cpf>"
    Quando validar
    Entao deve retornar "<resultado>"

    Exemplos: 
      | cpf          | resultado |
      |  00000000000 | false     |
      |  11111111111 | false     |
      |  22222222222 | false     |
      |  33333333333 | false     |
      |  44444444444 | false     |
      |  55555555555 | false     |
      |  66666666666 | false     |
      |  77777777777 | false     |
      |  88888888888 | false     |
      |  99999999999 | false     |
      | 012345678901 | false     |
      |   0123456789 | false     |
      | 01A23456789  | false     |
      | abc          | false     |
      |              | false     |
      |  02128552044 | true      |
      |  01234567890 | true      |
