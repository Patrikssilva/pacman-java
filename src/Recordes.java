import java.io.*;
import java.util.*;

public class Recordes {
    private static final String ARQUIVO = "recordes.txt";
    
    public static void salvar(int pontuacao) {
        List<Integer> recordes = carregar();
        recordes.add(pontuacao);
        Collections.sort(recordes, Collections.reverseOrder());
        
        if (recordes.size() > 3) {
            recordes = recordes.subList(0, 3);
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (int score : recordes) {
                writer.println(score);
            }
            System.out.println("\n💾 Pontuação salva com sucesso!");
        } catch (IOException e) {
            System.out.println("\n❌ Erro ao salvar recordes.");
        }
    }
    
    public static List<Integer> carregar() {
        List<Integer> recordes = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                recordes.add(Integer.parseInt(linha));
            }
        } catch (FileNotFoundException e) {
            // Arquivo não existe ainda
        } catch (IOException e) {
            System.out.println("❌ Erro ao carregar recordes.");
        }
        
        return recordes;
    }
    
    public static void exibir() {
        GameManager.limparTela();
        System.out.println("\n========================================");
        System.out.println("         MELHORES PONTUAÇÕES ");
        System.out.println("========================================\n");
        
        List<Integer> recordes = carregar();
        
        if (recordes.isEmpty()) {
            System.out.println("  Nenhum recorde registrado ainda!");
            System.out.println("  Seja o primeiro a jogar!\n");
        } else {
            String[] medalhas = {"🥇", "🥈", "🥉"};
            for (int i = 0; i < recordes.size(); i++) {
                System.out.printf("  %s  %dº lugar: %d pontos\n", 
                                 medalhas[i], (i + 1), recordes.get(i));
            }
            System.out.println();
        }
        
        System.out.println("========================================");
        GameManager.esperarTecla();
    }
}