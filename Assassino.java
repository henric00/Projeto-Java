import java.util.Random;

public class Assassino extends Soldado {
    private Random random = new Random();

    public Assassino(String nome, int nivel) {
        super(nome, nivel, 60);
    }

    @Override
    public void atacar(Soldado inimigo) {
        // Ataque especial: 35% de chance de ataque furtivo que causa dano maior
        boolean ataqueFurtivo = random.nextInt(100) < 35;
        if (ataqueFurtivo) {
            int dano = 35 + getNivel() * 4;
            System.out.println(getNome() + " executou um ATAQUE FURTIVO! (Especial)");
            inimigo.defender(dano);
        } else {
            int dano = 12 + getNivel() * 2;
            System.out.println(getNome() + " ataca rapidamente!");
            inimigo.defender(dano);
        }
    }
}
