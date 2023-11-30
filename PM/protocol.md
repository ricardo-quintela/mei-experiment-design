# Protocolo de Testes

## Preparação

1. Utilizar o [formulário](https://forms.gle/JdWYdTLPbTjiMfMK7) para recolha de dados e consentimento
2. Utilizar os [programas](PM/programs/bugged) com bugs para testar
3. Utilizar uma folha de papel e um lápis para tirar notas

## Protocolo

1. Apresentar o formulário ao revisor
2. Explicar do que se trata a experiência  
"Esta experiência trata-se de uma revisão de código. O objetivo é rever 3 programas em diferentes linguagens de programação para encontrar possíveis *bugs*."
3. Pedir o seu consentimento para participar
4. Pedir que responda às perguntas sobre a sua experiência com *Python*, *Java* e *Javascript*  
Dependendo das respostas são apresentados diferentes programas:
- Se responder "Baixo" será apresentado o programa 1 dessa linguagem
- Se responder "Intermédio" será apresentado o programa 2 dessa linguagem
- Se responder "Avançado" será apresentado o programa 3 dessa linguagem

5. Explicar que tipo de bugs se procuram e o que pode encontrar  
"Neste teste, entende-se como *bug* qualquer pedaço de **código** que pode fazer o programa **não funcionar como descrito**.  
Os bugs não são:
    - Documentação mal feita
    - Comentários mal escritos
    - Assinaturas de funções"

6. Dizer que tem TEMPO minutos para inspecionar cada código
7. Apresentar o código ao revisor
8. Cronometrar o tempo (TEMPO minutos)
9. Tomar notas

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