# language: pt
Funcionalidade: Converter Dados da Venda
  Eu como validador
  Quero validar o cnpj informado
  Para que eu possa garantir a integridade da informacao

  Esquema do Cenario: Deve validar o cnpj
    Dado que informei o cnpj "<cnpj>"
    Quando validar
    Entao deve retornar "<resultado>"

    Exemplos: 
      | cnpj            | resultado |
      |  00000000000000 | false     |
      |  11111111111111 | false     |
      |  22222222222222 | false     |
      |  33333333333333 | false     |
      |  44444444444444 | false     |
      |  55555555555555 | false     |
      |  66666666666666 | false     |
      |  77777777777777 | false     |
      |  88888888888888 | false     |
      |  99999999999999 | false     |
      |   9097886000019 | false     |
      | 909788600001972 | false     |
      | 909788GGG00197  | false     |
      | ckhp            | false     |
      |                 | false     |
      |  90978860000197 | true      |
      |  33550522000116 | true      |
      |  43975986000128 | true      |
      |  17650093000100 | true      |
