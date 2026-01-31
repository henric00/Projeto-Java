import java.util.Random;

public class Berserker extends Soldado {
    private Random random = new Random();

    public Berserker(String nome, int nivel) {
        super(nome, nivel, 100);
    }

    @Override
    public String atacar(Soldado inimigo) {
        StringBuilder msg = new StringBuilder();
        int dano = 18 + getNivel() * 3;
        boolean critico = random.nextInt(100) < 10; 
        if (getPontosDeVida() <= 30) {
            dano += 15 + getNivel() * 2;
            msg.append(getNome()).append(" entrou em FÚRIA! Dano aumentado.\n");
        }

        if (critico) {
            dano *= 2;
            msg.append(getNome()).append(" acertou um CRÍTICO!\n");
        } else {
            msg.append(getNome()).append(" investe no inimigo!\n");
        }

        msg.append(inimigo.defender(dano)).append("\n");
        return msg.toString();
    }
}
