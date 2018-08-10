# language: pt
Funcionalidade: Converter Dados Vendedor
  Eu como conversor
  Quero converter os dados do vendedor
  Para que eu possa fornecer estatisticas sobre os vendedores

  Cenario: Deve exibir mensagem quando cpf invalido
    Dado que informei o cpf "4564891986"
    Quando converter
    Entao deve exibir a mensagem "CPF invalido."

  Cenario: Deve exibir mensagem quando salario invalido
  	Dado que informei o cpf "01234567890"
    E que informei o salario "abc"
    Quando converter
    Entao deve exibir a mensagem "Salario invalido."
