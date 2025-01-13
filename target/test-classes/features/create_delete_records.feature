# language: pt
# encoding: UTF-8

@web_tables
Funcionalidade: Criar e deletar registros em Web Tables

  Cenário: Criar e deletar 12 registros
    Dado que eu acesso a página de Web Tables
    Quando eu crio 12 novos registros dinamicamente
    Então eu devo deletar todos os registros criados