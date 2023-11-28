# Programas em Python

Esta pasta contém 3 programas. Dois deles estão completos enquanto que o terceiro tem
duas funções que se podem considerar abstratas.

## Descrição dos programas

Por ordem de mais simples para mais complexo.

1. [K Means numa imagem](k_means_image.py)

Este programa aplica o algoritmo de clustering K Means a uma imagem guardada num array.

Para o efeito usam-se duas funções que não estão definidas:
- `rgb_ro_lab()` converte uma cor no espaço de cor RGB para o
espaço de cor [CIELAB](https://en.wikipedia.org/wiki/CIELAB_color_space).
- `ciede2000_distance()` calcula a distância entre duas cores no espaço CIELAB
utilizando a distância [CIEDE2000](https://en.wikipedia.org/wiki/Color_difference#CIEDE2000).

2. [Lexer](lexer.py)

Este é um analisador lexical ao qual podem ser atribuidos tokens com as suas respetivas expressões regulares.

O analisador permite separar uma string por tokens.

3. [Servidor REST](rest_server/rest_server.py)

Este programa é um servidor REST. Tem 3 endpoints implementados:

- `/` - devolve a string "Home page" quando acedido
- `create-room?username=STRING` - cria uma sala em memória com um ID aleatório à qual se pode juntar outro cliente.
- `/game/<room_id>?username=STRING` - permite que um cliente se junte a uma sala criada previamente.


# Referências

1. https://github.com/ricardo-quintela/led_controller/
2. https://github.com/ricardo-quintela/negate/
3. https://github.com/ricardo-quintela/compyler/
