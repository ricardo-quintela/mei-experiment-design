# mei-experiment-design

Neste projeto pretende-se desevolver uma experiência para determinar se as inspeções de código valem realmente a pena.

# Inspeções de Fagan

Uma inspeção de código formal que segue várias etapas para garantir a eficiência da inspeção.

1. Planeamento
	1. Preparação de materiais
	2. Arranjo de participantes
	3. Arranjo do local de reunião
2. Visão Geral
	1. Explicação ao grupo dos materiais em revisão
	2. Distribuição de papéis
3. Preparação
	1. Os participantes revêm os materiais antes da inspeção para se prepararem
	2. Os participantes preparam os seus papéis (lápis, canetas, sla...)
4. Reunião de inspeção
	1. Deteção dos defeitos
5. Rearranjo
	1. Os defeitos encontrados são arranjados pelo autor até os requisitos serem cumpridos
6. Seguimento
	1. Com os defeitos arranjados o moderador confirma que está tudo bem

# Inspeções de código modernas

As inspeções de código modernas baseiam-se em pull requests.

Um pull request nada mais é do que uma notificação aos contribuidores de um repositório num sistema de controlo de versões de que o código vai sofrer alterações ou integração de novas funcionalidades.

Um pull request requer que o código juntado tenha a versão mais recente e que não possua conflitos em zonas de código.

1. O desenvolvedor requisita um pull request
2. Resolve os conflitos com a versão mais recente do repositório
3. Pede revisão de outro *desenvolvedor* que também contribui para o repositório
4. O *desenvolvedor* revê e aprova o "*merge*"
5. As mudanças são integradas no ramo "*main*"


# Variáveis dependentes

Variáveis que dependem das independentes.

## Recolha de variáveis dependentes

1. Percentagem de bugs que as inspeções de código detetam
2. Percentagem de false positives

No caso de se encontrar um false positive tem que se ver se realmente há um bug naquele local ou se o reviewer se enganou

# Variáveis independentes

São manipuladas pelos investigadores na experiência e não por outras variáveis.

Uma variável independente acontece **sempre** antes da variável dependente.

>Exemplo
>Número de carros vendidos e o preço do carro
>
>O carro tem que ter um preço antes de ser vendido por isso *preço* é a variável independente

## Recolha de variáveis independentes

- Linguagem de programação
- Nível de conhecimento da linguagem
- Tempo disponível

### Níveis

1. Linguagem de programação
	1. Java
	2. Python
	3. JavaScript
2. Nível de conhecimento da linguagem
	1. Baixo - Controlo de fluxo, loops, tipos primitivos e estruturas de dados básicas
	2. Intermédio - Boa gestão de paradigmas de programação (funcional, poo, procedimental)
	3. Avançado - Multiprocessamento, threads, GUI
3. Tempo disponível
	1. 1h
	2. 30min
	3. 10min
