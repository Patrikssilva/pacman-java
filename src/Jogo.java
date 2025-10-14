import java.util.Scanner;
import java.util.Random;

public class Jogo {
    private char[][] labirinto;
    private int pacmanX, pacmanY;
    private int[] fantasmaX, fantasmaY;
    private int pontuacao;
    private int vidas;
    private int nivel;
    private boolean jogoAtivo;
    private boolean modoVulneravel;
    private int tempoVulneravel;
    private Scanner scanner;
    private Random random;
    private int totalBolinhas;
    private int bolinhasColetadas;
    
    public Jogo() {
        labirinto = new char[15][20];
        fantasmaX = new int[4];
        fantasmaY = new int[4];
        pontuacao = 0;
        vidas = 3;
        nivel = 1;
        jogoAtivo = true;
        modoVulneravel = false;
        tempoVulneravel = 0;
        scanner = new Scanner(System.in);
        random = new Random();
        totalBolinhas = 0;
        bolinhasColetadas = 0;
    }
    
    public void iniciar() {
        criarLabirinto();
        
        while (jogoAtivo && vidas > 0) {
            GameManager.limparTela();
            exibirLabirinto();
            exibirInfo();
            
            if (bolinhasColetadas >= totalBolinhas) {
                nivel++;
                pontuacao += 1000;
                System.out.println("\n🎉 NÍVEL COMPLETO! +1000 pontos!");
                System.out.println("Avançando para o nível " + nivel + "...");
                GameManager.esperarTecla();
                criarLabirinto();
                continue;
            }
            
            System.out.print("\nMovimento (W/A/S/D) ou P para pausar: ");
            String movimento = scanner.next().toUpperCase();
            
            if (movimento.equals("P")) {
                pausar();
                continue;
            }
            
            moverPacman(movimento);
            
            if (modoVulneravel) {
                tempoVulneravel--;
                if (tempoVulneravel <= 0) {
                    modoVulneravel = false;
                    System.out.println("\n⚠️  Modo vulnerável terminou!");
                }
            }
            
            moverFantasmas();
            verificarColisoes();
        }
        
        if (vidas <= 0) {
            exibirGameOver();
        }
    }
private void criarLabirinto() {
    totalBolinhas = 0;
    bolinhasColetadas = 0;
    
    // Preencher com bolinhas
    for (int y = 0; y < 15; y++) {
        for (int x = 0; x < 20; x++) {
            labirinto[y][x] = '.';
        }
    }
    
    // Criar paredes (bordas)
    for (int x = 0; x < 20; x++) {
        labirinto[0][x] = '#';
        labirinto[14][x] = '#';
    }
    for (int y = 0; y < 15; y++) {
        labirinto[y][0] = '#';
        labirinto[y][19] = '#';
    }
    
    // Paredes internas - criar blocos maiores
    // Bloco superior esquerdo
    for (int y = 2; y < 6; y++) {
        for (int x = 2; x < 6; x++) {
            labirinto[y][x] = '#';
        }
    }
    
    // Bloco superior direito
    for (int y = 2; y < 6; y++) {
        for (int x = 14; x < 18; x++) {
            labirinto[y][x] = '#';
        }
    }
    

    
    // Bloco inferior esquerdo
    for (int y = 9; y < 13; y++) {
        for (int x = 2; x < 6; x++) {
            labirinto[y][x] = '#';
        }
    }
    
    // Bloco inferior direito
    for (int y = 9; y < 13; y++) {
        for (int x = 14; x < 18; x++) {
            labirinto[y][x] = '#';
        }
    }
    
    // Paredes verticais laterais
    for (int y = 2; y < 13; y++) {
        if (y != 7) {
            labirinto[y][7] = '#';
            labirinto[y][12] = '#';
        }
    }
    
    // Adicionar Power Pellets nos cantos
    labirinto[1][1] = 'O';
    labirinto[1][18] = 'O';
    labirinto[13][1] = 'O';
    labirinto[13][18] = 'O';
    
    // Contar bolinhas
    for (int y = 0; y < 15; y++) {
        for (int x = 0; x < 20; x++) {
            if (labirinto[y][x] == '.' || labirinto[y][x] == 'O') {
                totalBolinhas++;
            }
        }
    }
    
    // Posicionar Pac-Man
    pacmanX = 10;
    pacmanY = 7;
    
    // Posicionar Fantasmas
    fantasmaX[0] = 10;
    fantasmaY[0] = 3;
    fantasmaX[1] = 6;
    fantasmaY[1] = 7;
    fantasmaX[2] = 13;
    fantasmaY[2] = 7;
    fantasmaX[3] = 10;
    fantasmaY[3] = 11;
}
    
    
    private void exibirLabirinto() {
        System.out.println("\n========================================");
        System.out.println("         PAC-MAN - NÍVEL " + nivel);
        System.out.println("========================================\n");
        
        char[][] temp = new char[15][20];
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 20; x++) {
                temp[y][x] = labirinto[y][x];
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if (fantasmaY[i] >= 0 && fantasmaY[i] < 15 && 
                fantasmaX[i] >= 0 && fantasmaX[i] < 20) {
                if (modoVulneravel) {
                    temp[fantasmaY[i]][fantasmaX[i]] = 'V';
                } else {
                    temp[fantasmaY[i]][fantasmaX[i]] = 'F';
                }
            }
        }
    
        
        temp[pacmanY][pacmanX] = 'C';
        
        for (int y = 0; y < 15; y++) {
            System.out.print("  ");
            for (int x = 0; x < 20; x++) {
                System.out.print(temp[y][x] + " ");
            }
            System.out.println();
        }
        
        System.out.println("\n  # = Parede  . = Bolinha  O = Power Pellet");
        System.out.println("  C = Pac-Man  F = Fantasma  V = Vulnerável");
    }
    
    private void exibirInfo() {
        System.out.println("\n========================================");
        System.out.printf("  PONTUAÇÃO: %d  |  VIDAS: %d  |  NÍVEL: %d\n", 
                         pontuacao, vidas, nivel);
        System.out.printf("  Bolinhas: %d/%d", bolinhasColetadas, totalBolinhas);
        if (modoVulneravel) {
            System.out.printf("  |  ⚡ MODO VULNERÁVEL: %d", tempoVulneravel);
        }
        System.out.println("\n========================================");
    }
    
    private void moverPacman(String direcao) {
        int novoX = pacmanX;
        int novoY = pacmanY;
        
        switch (direcao) {
            case "W": novoY--; break;
            case "S": novoY++; break;
            case "A": novoX--; break;
            case "D": novoX++; break;
            default:
                System.out.println("\n❌ Movimento inválido! Use W/A/S/D");
                GameManager.esperarTecla();
                return;
        }
        
        if (novoY >= 0 && novoY < 15 && novoX >= 0 && novoX < 20) {
            char destino = labirinto[novoY][novoX];
            
            if (destino != '#') {
                if (destino == '.') {
                    pontuacao += 10;
                    bolinhasColetadas++;
                    labirinto[novoY][novoX] = ' ';
                }
                else if (destino == 'O') {
                    pontuacao += 50;
                    bolinhasColetadas++;
                    modoVulneravel = true;
                    tempoVulneravel = 10;
                    labirinto[novoY][novoX] = ' ';
                    System.out.println("\n⚡ POWER PELLET! Fantasmas vulneráveis!");
                }
                
                pacmanX = novoX;
                pacmanY = novoY;
            }
        }
    }
    
    private void moverFantasmas() {
        for (int i = 0; i < 4; i++) {
            if (random.nextInt(10) < (10 - nivel)) {
                continue;
            }
            
            int direcao = random.nextInt(4);
            int novoX = fantasmaX[i];
            int novoY = fantasmaY[i];
            
            switch (direcao) {
                case 0: novoY--; break;
                case 1: novoY++; break;
                case 2: novoX--; break;
                case 3: novoX++; break;
            }
            
            if (novoY > 0 && novoY < 14 && novoX > 0 && novoX < 19) {
                if (labirinto[novoY][novoX] != '#') {
                    fantasmaX[i] = novoX;
                    fantasmaY[i] = novoY;
                }
            }
        }
    }
    
    private void verificarColisoes() {
        for (int i = 0; i < 4; i++) {
            if (pacmanX == fantasmaX[i] && pacmanY == fantasmaY[i]) {
                if (modoVulneravel) {
                    pontuacao += 200;
                    System.out.println("\n🍽️  Você comeu um fantasma! +200 pontos!");
                    
                    fantasmaX[i] = 10;
                    fantasmaY[i] = 3;
                    
                    GameManager.esperarTecla();
                } else {
                    vidas--;
                    System.out.println("\n💥 Você foi pego por um fantasma!");
                    
                    if (vidas > 0) {
                        System.out.println("Vidas restantes: " + vidas);
                        GameManager.esperarTecla();
                        
                        pacmanX = 10;
                        pacmanY = 7;
                        
                        fantasmaX[0] = 5; fantasmaY[0] = 3;
                        fantasmaX[1] = 14; fantasmaY[1] = 3;
                        fantasmaX[2] = 5; fantasmaY[2] = 11;
                        fantasmaX[3] = 14; fantasmaY[3] = 11;
                    }
                }
            }
        }
    }
    
    private void pausar() {
        GameManager.limparTela();
        System.out.println("\n========================================");
        System.out.println("              JOGO PAUSADO");
        System.out.println("========================================\n");
        System.out.println("  1 - Continuar");
        System.out.println("  2 - Desistir e voltar ao menu\n");
        System.out.println("========================================");
        System.out.print("\nEscolha: ");
        
        int opcao = scanner.nextInt();
        scanner.nextLine();
        
        if (opcao == 2) {
            jogoAtivo = false;
            vidas = 0;
            System.out.println("\nVoltando ao menu principal...");
            GameManager.esperarTecla();
        }
    }
    
    private void exibirGameOver() {
        GameManager.limparTela();
        System.out.println("\n");
        System.out.println("                                        ");
        System.out.println("            💀 GAME OVER! 💀           ");
        System.out.println("                                        ");
        System.out.println();
        System.out.println("  Pontuação Final: " + pontuacao);
        System.out.println("  Nível Alcançado: " + nivel);
        System.out.println();
        System.out.println("                                        ");
        
        Recordes.salvar(pontuacao);
        GameManager.esperarTecla();
    }
}