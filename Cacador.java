import java.util.Random;
public class Cacador extends Soldado {

    private Random random = new Random();

    public Cacador(String nome, int nivel) {
        super(nome, nivel, 70);
    }
    @Override
    public String atacar(Soldado inimigo) {
        StringBuilder msg = new StringBuilder();
        int dano = 20 + getNivel() * 2;
        boolean critico = random.nextInt(100) < 25;

        if (critico) {
            dano *= 2;
            msg.append(getNome()).append(" acertou um ATAQUE CRÍTICO!\n");
        } else {
            msg.append(getNome()).append(" disparou uma flecha!\n");
        }

        msg.append(inimigo.defender(dano)).append("\n");
        return msg.toString();
    }
}
