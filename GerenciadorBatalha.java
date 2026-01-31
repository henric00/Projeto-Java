import java.util.ArrayList;
import java.util.Random;

public class GerenciadorBatalha {
    private ArrayList<Soldado> ladoA;
    private ArrayList<Soldado> ladoB;
    private int numRodada;
    private boolean batalhaTerminada;
    private boolean ladoComecaA;
    private StringBuilder logs;

    public GerenciadorBatalha(ArrayList<Soldado> equipeA, ArrayList<Soldado> equipeB) throws BatalhaInvalidaException {
        if (equipeA.size() != equipeB.size()) {
            throw new BatalhaInvalidaException("Os lados devem ter a mesma quantidade de personagens para iniciar a batalha.");
        }
        this.ladoA = new ArrayList<>(equipeA);
        this.ladoB = new ArrayList<>(equipeB);
        this.numRodada = 0;
        this.batalhaTerminada = false;
        this.logs = new StringBuilder();
    }

    public void iniciarBatalha() {
        System.out.println("\n=== INÍCIO DA BATALHA ===");
        System.out.println("Lado A (Luz): " + ladoA.size() + " soldados");
        System.out.println("Lado B (Trevas): " + ladoB.size() + " soldados\n");
       
        this.ladoComecaA = new Random().nextBoolean();
        System.out.println("Lado inicial: " + (ladoComecaA ? "LUZ (Lado A)" : "TREVAS (Lado B)"));
    }

    public boolean executarProximaRodada() {
        if (batalhaTerminada) {
            return false;
        }

        if (!ambosLadosTemSoldados()) {
            exibirResultadoBatalha();
            batalhaTerminada = true;
            return false;
        }

        numRodada++;
        System.out.println("======== TURNO " + numRodada + " ========");
        executarRodada();
        System.out.println();
        return true;
    }

    private void executarRodada() {
        executarRodadaAlternada();
        removerMortos();
        subirNivelSobreviventes();
        exibirStatus();
    }
    private void executarRodadaAlternada() {
        System.out.println("\n[Executando ações alternadas nesta rodada]");

        int max = Math.max(ladoA.size(), ladoB.size());

        for (int i = 0; i < max; i++) {
            if (ladoComecaA) {
                if (i < ladoA.size()) {
                    realizarAcao(ladoA.get(i), ladoB, i);
                }
                if (i < ladoB.size()) {
                    realizarAcao(ladoB.get(i), ladoA, i);
                }
            } else {
                if (i < ladoB.size()) {
                    realizarAcao(ladoB.get(i), ladoA, i);
                }
                if (i < ladoA.size()) {
                    realizarAcao(ladoA.get(i), ladoB, i);
                }
            }
        }
    }

    private void realizarAcao(Soldado atacante, ArrayList<Soldado> inimigos, int indicePreferencial) {
        if (atacante == null || !atacante.estarVivo()) return;

        Soldado alvo = escolherAlvoValido(inimigos, indicePreferencial);
        if (alvo == null) {
            return;
        }

        System.out.println("\n[Turno de " + atacante.getNome() + "]");
        System.out.println(atacante.atacar(alvo));
    }

    private Soldado escolherAlvoValido(ArrayList<Soldado> inimigos, int indicePreferencial) {
        if (inimigos.isEmpty()) return null;

        if (indicePreferencial >= 0 && indicePreferencial < inimigos.size()) {
            Soldado cand = inimigos.get(indicePreferencial);
            if (cand != null && cand.estarVivo()) return cand;
        }

        for (Soldado s : inimigos) {
            if (s.estarVivo()) return s;
        }

        return null;
    }

    private void removerMortos() {
        System.out.println("\n[Removendo combatentes mortos...]");
        removerMortosDoLado(ladoA, "Lado A");
        removerMortosDoLado(ladoB, "Lado B");
    }

    private void removerMortosDoLado(ArrayList<Soldado> lado, String nomeLado) {
        for (int i = lado.size() - 1; i >= 0; i--) {
            if (!lado.get(i).estarVivo()) {
                System.out.println("  - " + lado.get(i).getNome() + " foi removido do " + nomeLado);
                lado.remove(i);
            }
        }
    }

    private void subirNivelSobreviventes() {
        System.out.println("\n[Evolução dos sobreviventes...]");
        subirNivelLado(ladoA);
        subirNivelLado(ladoB);
    }

    private void subirNivelLado(ArrayList<Soldado> lado) {
        for (Soldado soldado : lado) {
            if (soldado.estarVivo()) {
                System.out.println(soldado.subirNivel());
            }
        }
    }

    private void exibirStatus() {
        System.out.println("\n[Status após a rodada]");
        exibirStatusLado(ladoA, "Lado A");
        exibirStatusLado(ladoB, "Lado B");
    }

    private void exibirStatusLado(ArrayList<Soldado> lado, String nomeLado) {
        System.out.println(nomeLado + ": " + lado.size() + " soldados");
        for (Soldado s : lado) {
            System.out.println("  - " + s.getNome() + " | Nível: " + s.getNivel() + " | Vida: " + s.getPontosDeVida());
        }
    }

    private boolean ambosLadosTemSoldados() {
        return !ladoA.isEmpty() && !ladoB.isEmpty();
    }

    private void exibirResultadoBatalha() {
        if (ladoA.isEmpty()) {
            System.out.println("=== LADO B (TREVAS) VENCEU A BATALHA! ===");
        } else {
            System.out.println("=== LADO A (LUZ) VENCEU A BATALHA! ===");
        }
    }

    public ArrayList<Soldado> getLadoA() {
        return ladoA;
    }

    public ArrayList<Soldado> getLadoB() {
        return ladoB;
    }

    public int getNumRodada() {
        return numRodada;
    }

    public boolean temVencedor() {
        return ladoA.isEmpty() || ladoB.isEmpty();
    }

    public String getVencedor() {
        if (ladoA.isEmpty()) {
            return "TREVAS";
        } else if (ladoB.isEmpty()) {
            return "LUZ";
        }
        return "NINGUÉM";
    }

    public String getLogs() {
        return logs.toString();
    }
}
