# Programas em Javascript

Esta pasta contém 1 programa agora


## Descrição dos programas

Por ordem de mais simples para mais complexo.

1. [Cena de fibonacci](Fibonacci.js)

Programa simples com a regra de fibonacci

Bugs:

- linha 4 - Devia ser i++ e não i--

- linha 5 - em vez de [i-1] e depois [i-2] tem [i-1] e depois [i].


2. [Request de mais conteúdo](request_resources/main.js)

Programa que recebe um clique num botão e pede ao servidor mais conteúdo, neste caso deve pedir o html de um jogo.

### Bugs introduzidos

- linha `34` em `main.js`
```js
document.querySelector(".main");
```
Devia ser
```js
mainEl = document.querySelector("main");
```
Em alternativa `index.html` poderia ser alterado para que o
elemento `<main>` fosse `<main class="main">`. Também se pode definir `mainEl`
nesta linha retirando a sua declaração `let mainEL;` na linha `23`.

---

- linha `40` em `main.js`
```js
if (mainEl != resourceContent)
```
Devia ser
```js
if (mainEl.innerHtml !== resourceContent)
```
Porque `!=` faz uma conversão de tipo e caso `resourceContent`
seja `null` e `mainEl` for `undefined` o código vai entrar no `if`.
É necessário acrescentar `innerHtml` porque na documentação de `resourceContent`
está dito que retorna uma string de um elemento Html.

---

- linha `41` em `main.js`
```js
mainEl.innerHtml = resourceContent.innerHtml;
```
Devia ser
```js
mainEl.innerHtml = resourceContent;
```
Porque `resourceContent` é uma `String` que já representa um elemento
Html, não tendo atributo `innerHtml`.

# Referências

1. 
2. https://github.com/ricardo-quintela/negate/
