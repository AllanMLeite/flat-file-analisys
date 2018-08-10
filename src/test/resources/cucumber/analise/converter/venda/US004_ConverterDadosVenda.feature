# language: pt
Funcionalidade: Converter Dados da Venda
  Eu como conversor
  Quero converter os dados da venda
  Para que eu possa fornecer estatisticas sobre as vendas

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
