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
        int dano = 20 + nivel * 2;
        boolean critico = random.nextInt(100) < 25;

        if (critico) {
            dano *= 2;
            System.out.println(nome + " acertou um ATAQUE CRÍTICO!");
        } else {
            System.out.println(nome + " disparou uma flecha!");
        }

        inimigo.defender(dano);
    }
//método de defesa simples
    @Override
    public void defender(int danoRecebido) {
        dano(danoRecebido);
        System.out.println(nome + " recebeu " + danoRecebido + " de dano.");
    }
}