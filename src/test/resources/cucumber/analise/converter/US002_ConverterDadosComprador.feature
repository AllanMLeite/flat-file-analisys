# language: pt
Funcionalidade: Converter Dados Comprador
  Eu como conversor
  Quero converter os dados do comprador
  Para que eu possa fornecer estatisticas sobre os compradores

Cenario: Deve converter o comprador
    Dado que informei o cnpj "76434483000102"
    E que informei o nome "Alvaro"
    E que informei a area de negocio "Rural"
    Quando converter
    Entao deve gerar um comprador com nome "Alvaro", cnpj "76434483000102" e area "Rural"

  Cenario: Deve exibir mensagem quando cnpj invalido
    Dado que informei o cnpj "41265498498456"
    Quando converter
    Entao deve exibir a mensagem "CNPJ invalido."