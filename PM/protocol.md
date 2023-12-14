# Protocolo de Testes

## Preparação

1. Utilizar o [formulário](https://forms.gle/JdWYdTLPbTjiMfMK7) para recolha de dados e consentimento
2. Utilizar os [programas](programs/bugged) com bugs para testar
3. Utilizar uma folha de papel e um lápis para tirar notas

## Protocolo

1. Apresentar o formulário ao revisor
2. Explicar do que se trata a experiência  
"Esta experiência trata-se de uma revisão de código. O objetivo é rever 3 programas na linguagens de programação python para encontrar possíveis *bugs*."
3. Pedir o seu consentimento para participar
4. Pedir que responda a algumas questões pessoais para ser possível obter conclusões.
5. Pedir que responda às perguntas sobre a sua experiência com *Python*
Dependendo das respostas são apresentados diferentes programas:
- Se responder "Baixo" será apresentado o programa 1 dessa linguagem (nível baixo)
- Se responder "Intermédio" serão apresentados 2 programas (níveis baixo e médio)
- Se responder "Avançado" serão apresentados 3 programas (níveis baixo, médio e difícil)

5. Explicar que tipo de bugs se procuram e o que pode encontrar  
"Neste teste, entende-se como *bug* qualquer pedaço de **código** que pode fazer o programa **não funcionar como descrito**.  
Os bugs não são:
    - Documentação mal feita
    - Comentários mal escritos
    - Assinaturas de funções
Os bugs são aqueles em que o código funciona na mesma mas não se obtém o que é pretendido.
Ex: Ter um "+=" em vem de "="

6. Dizer que tem TEMPO minutos para inspecionar cada código. (Dependendo do nível de dificuldade do cógido o tempo fornecido é diferente)
7. Apresentar o código ao revisor
- Se a revisão for presencialmente será fornecido o código ou em papel ou no computador (o revisor não pode correr o código ou utilizar alguma ferramenta)
- Se a revisão for remota, será inicializada uma reunião zoom ou uma chamada por "discord" em que o revisor compartilhará a tela com o código lhe fornecido para ele encontrar os bugs

8. Cronometrar o tempo (TEMPO minutos)
9. Tomar notas

## Regras para a revisão

- O revisor não pode utilizar ferramentas como "chatgpt", pesquisar sobre as funções utilizadas ou outro tupo de ajudas que ajudaram a encontrar os bugs
- Se existir alguma dúvida, o revisor pode perguntar.
- O revisor utiliza uma folha de papel para descrever os bugs. Em caso da revisão ser remota, escreverá em um bloco de notas que posteriormente é enviado
- O revisor pode não utilizar o tempo total fornecido se assim quiser, mas não o pode ultrapassar



## Notas (logging)

### Cabeçalho:
`N_DD/MM/AAAA_HH:MM_lang_P`  
em que:
- `N` é o número de estudante
- `DD/MM/AAAA` é a data da revisão no formato "DD/MM/AAAA"
- `HH:MM` é a hora da revisão no formato "HH:MM"
- `lang` é a linguagem de programação `lang`
- `P` é o programa número `P`

### Corpo

Legenda:
- `B:l` - Bug *encontrado* na linha `l`
- `C:l` - Bug *corrigido* na linha `l`
- `E:l` - *Correção* mal feita na linha `l`
