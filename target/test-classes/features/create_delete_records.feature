Feature: Criar e deletar 12 registros dinâmicos

  Scenario: Criar e deletar 12 registros
    Given que eu acesso a página de Web Tables
    When eu crio 12 novos registros dinamicamente
    Then eu devo deletar todos os registros criados
