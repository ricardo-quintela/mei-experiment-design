# Programas em Python

Esta pasta contém 3 programas. Dois deles estão completos enquanto que o terceiro tem
duas funções que se podem considerar abstratas.

## Descrição dos programas

Por ordem de mais simples para mais complexo.

### 1. [K Means numa imagem](k_means_image/k_means_image.py)

Este programa aplica o algoritmo de clustering K Means a uma imagem guardada num array.

Para o efeito usam-se duas funções que não estão definidas:
- `rgb_ro_lab()` converte uma cor no espaço de cor RGB para o
espaço de cor [CIELAB](https://en.wikipedia.org/wiki/CIELAB_color_space).
- `ciede2000_distance()` calcula a distância entre duas cores no espaço CIELAB
utilizando a distância [CIEDE2000](https://en.wikipedia.org/wiki/Color_difference#CIEDE2000).

#### Bugs introduzidos

1. linha `20` em `k_means_image.py`:
```python
centroids.length
```  
Devia ser
```python
len(centroids)
```

---

2. linha `39` em `k_means_image.py`:
```python
distance > min_distance
```  
Devia ser
```python
distance < min_distance
```

---

3. linha `44` em `k_means_image.py`:
```python
clusters[c]
```
Devia ser
```python
clusters[centroid]
```

### 2. [Lexer](lexer/lexer.py)

Este é um analisador lexical ao qual podem ser atribuidos tokens com as suas respetivas expressões regulares.

O analisador permite separar uma string por tokens.

#### Bugs introduzidos

1. linha `14` em `token.py`:
```python
__new__
```
Devia ser
```python
__init__
```

---

2. linha `21` em `token.py`:

Deve exitir o método `__hash__` pois assim a linha `39` em `lexer.py`
não consegue encontrar os tokens no `Set`
```python
def __hash__(self) -> int:
    return hash(self.name)
```
---

3. linha `53` em `lexer.py`:

```python
self.tokens.append({name: regex})
```
Devia ser
```python
self.tokens.append((name, regex))
```
porque deste modo os outros métodos que acedem aos índices da lista
de tokens não conseguem indexar nem o nome do token nem a expressão regular e
a expressão regular do analisador lexical não é construida.


### 3. [Servidor REST](rest_server/rest_server.py)

Este programa é um servidor REST. Tem 3 endpoints implementados:

- `/` - devolve a string "Home page" quando acedido
- `create-room?username=STRING` - cria uma sala em memória com um ID aleatório à qual se pode juntar outro cliente.
- `/game/<room_id>?username=STRING` - permite que um cliente se junte a uma sala criada previamente.


#### Bugs inteoduzidos

1. linha `69` em `rest_server.py`:

```python
redirect(f"game/{room_id}?username={username}", code=303)
```
Devia ser
```python
redirect(f"/game/{room_id}?username={username}", code=303)
```
Ao não colocar a `/` o url seria acrescentado ao fim do atual.

---

2. linha `55` em `rest_server.py`:

```python
if request.method != "POST":
```
Devia ser
```python
if request.method != "GET":
```
porque o método está definido como *GET*.

---

3. linha `98` em `rest_server.py`:

```python
# roomId not in query params
if "roomId" not in args:
    abort(400)

room_id = args["roomId"]
```
Devia ser removido porque `room_id` já está definido como um *path parameter*.

Em alternativa, o path parameter podia ser removido nas linhas `73` e `74`.


# Referências

1. https://github.com/ricardo-quintela/led_controller/
2. https://github.com/ricardo-quintela/negate/
3. https://github.com/ricardo-quintela/compyler/
