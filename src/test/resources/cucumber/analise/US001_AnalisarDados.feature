# language: pt
Funcionalidade: US001 Analisar Dados
  Eu como uma rotina agendada
  Quero analisar os dados de entrada disponibilizados no diretorio
  Para que eu possa fornecer estatisticas sobre as vendas

  Contexto: 
    Dado que existam os diretorios "%HOMEPATH%/data/in" e "%HOMEPATH%/data/out"

  Cenario: Deve processar os dados do diretorio
    Dado que exista um arquivo chamado "20180809.dat" com as linhas abaixo
      | 001;01234567890;Diego;50000                 |
      | 001;01234567890;Renato;40000.99             |
      | 001;58543027055;Renato;40000.99             |
      | 002;2345675434544345;JosedaSilva;Rural      |
      | 002;2345675433444345;EduardoPereira;Rural   |
      | 003;10;[1-10-100,2-30-2.50,3-40-3.10];Diego |
      | 003;08;[1-34-10,2-33-1.50,3-40-0.10];Renato |
    Quando processar
    Entao deve criar um arquivo chamado "20180809.done.dat" com a linha "2;3;10;Renato"
