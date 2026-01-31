import java.util.Random;

public class Assassino extends Soldado {
    private Random random = new Random();

    public Assassino(String nome, int nivel) {
        super(nome, nivel, 60);
    }

    @Override
    public String atacar(Soldado inimigo) {
        StringBuilder msg = new StringBuilder();
        boolean ataqueFurtivo = random.nextInt(100) < 35;
        if (ataqueFurtivo) {
            int dano = 35 + getNivel() * 4;
            msg.append(getNome()).append(" executou um ATAQUE FURTIVO! (Especial)\n");
            msg.append(inimigo.defender(dano)).append("\n");
        } else {
            int dano = 12 + getNivel() * 2;
            msg.append(getNome()).append(" ataca rapidamente!\n");
            msg.append(inimigo.defender(dano)).append("\n");
        }
        return msg.toString();
    }
}
