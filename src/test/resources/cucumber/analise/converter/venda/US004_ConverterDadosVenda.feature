# language: pt
Funcionalidade: Converter Dados da Venda
  Eu como conversor
  Quero converter os dados da venda
  Para que eu possa fornecer estatisticas sobre as vendas

  Contexto: 
    Dado que foram realizadas vendas

  Cenario: Deve exibir mensagem quando quantidade do item for invalido
    Dado que informei um item com id "1", quantidade "abc" e preco "123"
    E que informei o id "1"
    Quando converter os dados da venda
    Entao deve exibir a mensagem  "Quantidade invalida."

  Cenario: Deve exibir mensagem quando preco do item for invalido
    Dado que informei um item com id "1", quantidade "1" e preco "abc"
    E que informei o id "1"
    Quando converter os dados da venda
    Entao deve exibir a mensagem  "Preco invalido."

  Cenario: Deve converter a venda
    Dado que informei um item com id "1", quantidade "1" e preco "10"
    E que informei um item com id "2", quantidade "45" e preco "20"
    E que informei o id "1"
    E que informei o vendedor "Santos"
    Quando converter os dados da venda
    Entao deve gerar uma venda id "1", vendedor "Santos" e com os itens abaixo
      | idItem | quantidade | preco |
      |      1 |          1 |    10 |
      |      2 |         45 |    20 |
