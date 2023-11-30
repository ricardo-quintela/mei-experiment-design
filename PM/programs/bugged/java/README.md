# Programas em Java

Esta pasta contém 3 programas. 

## Descrição dos programas

Por ordem de mais simples para mais complexo.

1. [Desenhar um pattern](/PatternPrinter.java)

Bugs implementados:

- Linha 12
Utilizar-se >= em vez de <=

- Linha  26
Falta = para ficar <=

- linha 32
No print tem "i" em vez de "l"

- linha 35
Linha de passar à proxima linha está comentada


2. [Loja com produtos](/Store.java)

Bugs implementados:

- linha 33
Em vez de aumentar (+=) a quantidade do produto (pode ser negativo ou positivo), diminui (-=) (basicamente faz o contrario da operaçao pretendida)

- linha 55
Em vez de adicionar um produto (.add), remove-se (.remove)

- linha 64
Colocar um "return;" dentro do loop, assim não existe um display completo de todos os items do inventario, apenas no primeiro

- linha 71
Tem ">= 0" para verificar se existe stock em vez de apenas ">" (se o stock for 0 ainda diz que existe stock)

Um erro que pode ser considerado mas não é relevante é que a quantidade do produto, quando recebe um update pode estar a descer infinitamente (pode se estar a tirar do stock infinitamente)
Isto apenas quando se dá "update", quando se compra um produto já não é possivel porque se a quantidade do produto for <= 0 esta operação não ocorre

3. [Comunicação entre um servidor e cliente](\server_client)

Bugs implementados:
SERVIDOR
- linhas 49 e 50
Estudante que se pretende buscar comentado e utilizar a String "studentName" para ir buscar o estudante

- linha 67 
Servidor a enviar um hashmap vazio para o cliente

CLIENTE
- linha 26 - socket a ser fechado antes das operaçoes

- linha 69 - Escrever o estudante novamente

- linha 88 - Escrever String "exisitingStudent" em vez de objecto

# Referências
1. Aulas EA 3ºAno
2. Projeto de POO
3. Projeto de POO com IS com chatgpt na mistura