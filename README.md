# Pac-Man em Java

Projeto Integrador do curso de Análise e Desenvolvimento de Sistemas do Centro Universitário Senac.

## Descrição

Desenvolvimento de um jogo digital baseado no clássico arcade Pac-Man, implementado em Java para execução em terminal/console. O projeto demonstra a aplicação de conceitos de programação orientada a objetos, estruturas de dados, manipulação de arquivos e lógica de jogo.

# Autores

- Ana Beatriz Santana
- Aparecida da Silva
- Beatriz Alcantara
- Giovanna Batista
- Jusley Carvalho
- Patrik Santos Silva
- Vinicius Melo

# Tecnologias

- Java 11+
- Scanner (entrada de dados do usuário)
- Arrays bidimensionais (labirinto)
- Manipulação de arquivos (salvamento de recordes)
- Random (movimento dos fantasmas)

# Características do Jogo

- **Interface:** Terminal/Console (modo texto)
- **Labirinto:** Grade 15x20 com paredes e corredores
- **Personagens:**
  - Pac-Man (C): Controlado pelo jogador
  - 4 Fantasmas (F): Movimento aleatório
- **Itens:**
  - Bolinhas (.) : 10 pontos cada
  - Power Pellets (O): 50 pontos + torna fantasmas vulneráveis
- **Sistema de Vidas:** 3 vidas iniciais
- **Sistema de Níveis:** Progressão com aumento de dificuldade
- **Recordes:** Top 3 melhores pontuações salvas em arquivo

# Como Jogar

# Controles
- **W** - Mover para cima
- **S** - Mover para baixo
- **A** - Mover para esquerda
- **D** - Mover para direita
- **P** - Pausar o jogo

# Objetivo
Coletar todas as bolinhas do labirinto sem ser capturado pelos fantasmas. Colete Power Pellets para tornar os fantasmas vulneráveis e comê-los por pontos extras!

# Pontuação
- Bolinha comum: 10 pontos
- Power Pellet: 50 pontos
- Fantasma capturado: 200 pontos
- Bônus por completar nível: 1000 pontos

# Estrutura do Projeto
```
pacman-java/
├── src/
│   ├── GameManager.java    # Classe principal e menu
│   ├── Jogo.java           # Lógica do jogo
│   └── Recordes.java       # Sistema de recordes
├── bin/                    # Arquivos compilados
├── recordes.txt           # Arquivo de recordes (gerado automaticamente)
└── README.md
```

# Como Executar

# Opção 1: Via IDE (VS Code, IntelliJ, Eclipse)
1. Abra o projeto na IDE
2. Execute o arquivo `GameManager.java`
3. O jogo abrirá no terminal integrado

# Opção 2: Via Terminal/Prompt de Comando
1. Navegue até a pasta `src`:
```bash
   cd pacman-java/src
```

2. Compile os arquivos:
```bash
   javac GameManager.java Jogo.java Recordes.java
```

3. Execute o jogo:
```bash
   java GameManager
```

# Funcionalidades Implementadas

# Requisitos Funcionais
- ✅ RF01: Menu Principal (Novo Jogo, Recordes, Instruções, Sair)
- ✅ RF02: Movimentação do Pac-Man via comandos de teclado
- ✅ RF03: Colisão com paredes
- ✅ RF04: Coleta de itens (bolinhas e power pellets)
- ✅ RF05: Comportamento dos fantasmas (movimento aleatório)
- ✅ RF06: Power-Up (modo vulnerável)
- ✅ RF07: Sistema de vidas
- ✅ RF08: Progressão de níveis
- ✅ RF09: Exibição de informações (score, vidas, nível)
- ✅ RF10: Sistema de pausa
- ✅ RF11: Tela de Game Over
- ✅ RF12: Registro de recordes em arquivo
- ✅ RF13: Tela de instruções

# Requisitos Não Funcionais
- ✅ RNF04: Portabilidade (Java 11+)
- ✅ RNF05: Código orientado a objetos
- ✅ RNF06: Tratamento de exceções
- ✅ RNF07: Funcionamento offline

# Conceitos de Programação Aplicados

- **Orientação a Objetos:** Classes, métodos, encapsulamento
- **Arrays Bidimensionais:** Representação do labirinto
- **Estruturas de Controle:** If/else, switch, loops (for, while)
- **Manipulação de Arquivos:** Leitura e escrita de recordes
- **Tratamento de Entrada:** Scanner para comandos do usuário
- **Aleatoriedade:** Random para movimento dos fantasmas
- **Lógica de Colisão:** Verificação de posições no labirinto

# Padrões de Projeto Aplicados

- **Singleton:** GameManager como ponto único de entrada
- **Separação de Responsabilidades:** Cada classe com função específica
  - GameManager: Controle de menu e fluxo principal
  - Jogo: Lógica do gameplay
  - Recordes: Persistência de dados


# Por que Terminal/Console?
- Facilita o aprendizado de lógica de programação
- Foco nos conceitos fundamentais sem complexidade gráfica
- Execução simples e universal

# Simplicidade do Código
O código foi desenvolvido priorizando:
- Clareza e legibilidade
- Conceitos adequados ao nível de aprendizado
- Facilidade de manutenção
- Demonstração prática de fundamentos

# Status do Projeto

Em desenvolvimento.


# Licença

Projeto acadêmico desenvolvido para fins educacionais.


Projeto desenvolvido por alunos do Centro Universitário Senac - 2025
