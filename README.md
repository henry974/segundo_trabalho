# segundo_trabalho
#  Sistema de Compras em Java

Este projeto é um sistema de compras orientado a objetos, feito em Java. Ele permite que **usuários se cadastrem, façam login e realizem compras simuladas**, enquanto **administradores** podem gerenciar o estoque.

---

##  Funcionalidades

###  Usuário (Cliente)
- Criar conta
- Fazer login
- Adicionar/remover produtos do carrinho
- Visualizar carrinho e preço total
- Finalizar compra (atualiza estoque)

###  Administrador
- Login com usuário e senha de admin
- Adicionar/remover produtos no estoque
- Visualizar todos os produtos disponíveis

## Compilação
No terminal, estando dentro de src/:

javac model/*.java service/*.java app/Principal.java

Isso criará .class em cada pacote.

## Execução
Ainda dentro do src/, execute com:

java -cp . app.Principal


