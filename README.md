# FinancasEasy
Sistema Java Web feito com Spring que registra e gera relatórios financeiros para o usuário.

Usuários salvos por padrão:
  usuário normal
  login: denis
  senha: 123
  
  usuário administrador
  login: adm
  senha: 123
  
  
Esse sistema foi desenvolvido por mim para o trabalho final do último semestre do meu curso em Análise e Desenvolvimento de Sistemas.
A funcionalidade dele tem como base a inserção das operações financeiras realizadas pelo usuário (sejam elas ganhos ou gastos), 
atribuindo-as à uma categoria (que indica de onde aquele dinheiro vem ou para onde está indo). O usuário pode cadastrar suas próprias categorias.
O usuário comum tem acesso à uma planilha que exibe a ele o total de dinheiro ganho e gasto para cada mês (e suas respectivas porcentagens), 
além de contabilizar o total através dos meses e indicar por quanto o saldo está positivo ou negativo.
O usuário administrador além disso, pode visualizar um relátorio com os dados dos usuários comuns.
Por mais que o projeto fosse focado no backend, nas classes e regras de negócio, coloquei esforço em criar um frontend amigável e agradável ao usuário,
com uma identidade visual definida com elementos que mudam de cor de acordo com o saldo positivo ou negativo.
A parte de autenticação de usuários foi feita com o UserDetails do Spring Security.
Para fins de apresentação do projeto, o sistema utiliza o banco de dados em memória H2.
