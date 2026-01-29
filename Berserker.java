import java.util.Random;

public class Berserker extends Soldado {
    private Random random = new Random();

    public Berserker(String nome, int nivel) {
        super(nome, nivel, 100);
    }

    @Override
    public void atacar(Soldado inimigo) {
        int dano = 18 + getNivel() * 3;
        boolean critico = random.nextInt(100) < 10; 

       
        if (getPontosDeVida() <= 30) {
            dano += 15 + getNivel() * 2;
            System.out.println(getNome() + " entrou em FÚRIA! Dano aumentado.");
        }

        if (critico) {
            dano *= 2;
            System.out.println(getNome() + " acertou um CRÍTICO!");
        } else {
            System.out.println(getNome() + " investe no inimigo!");
        }

        inimigo.defender(dano);
    }
}
