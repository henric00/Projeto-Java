import java.util.Random;
public class Cacador extends Soldado {

//terceira subclasse de Soldado (Caçador)
    private Random random = new Random();

    public Cacador(String nome, int nivel) {
        super(nome, nivel, 70);
    }
//método de ataque com chance de acerto crítico
    @Override
    public void atacar(Soldado inimigo) {
        int dano = 20 + getNivel() * 2;
        boolean critico = random.nextInt(100) < 25;

        if (critico) {
            dano *= 2;
            System.out.println(getNome() + " acertou um ATAQUE CRÍTICO!");
        } else {
            System.out.println(getNome() + " disparou uma flecha!");
        }

        inimigo.defender(dano);
    }
}