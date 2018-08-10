# language: pt
Funcionalidade: US001 Analisar Dados
  Eu como rotina agendada
  Quero analisar os dados de entrada disponibilizados no diretorio
  Para que eu possa fornecer estatisticas sobre as vendas

  Contexto: 
    Dado que existam os diretorios "%HOMEPATH%/data/in" e "%HOMEPATH%/data/out"

  Cenario: Deve processar os dados do diretorio
    Dado que exista um arquivo chamado "20180809.dat" com as linhas abaixo
      | 001;01234567890;Diego;50000                 |
      | 001;87253578082;Renato;4000.99              |
      | 001;58543027055;Jose;7000                   |
      | 002;76434483000102;JosedaSilva;Rural        |
      | 002;65355467000108;EduardoPereira;Rural     |
      | 003;10;[1-10-100,2-30-2.50,3-40-3.10];Diego |
      | 003;08;[1-34-10,2-33-1.50,3-40-0.10];Renato |
    E que exista um arquivo chamado "20180808.dat" com as linhas abaixo
      | 001;01234567890;Diego;6000                  |
      | 001;76051139001;Ramon;8000                  |
      | 002;37632458000173;JosedaSilva;Rural        |
      | 003;15;[1-10-100,2-30-2.50,3-40-3.10];Diego |
    Quando processar
    Entao deve criar um arquivo chamado "20180809.done.dat" com a linha "2;3;10;Jose"
    E deve criar um arquivo chamado "20180808.done.dat" com a linha "1;2;15;Ramon"
    E deve remover o arquivo "20180809.dat"
    E deve remover o arquivo "20180808.dat"
