import java.util.Random;

public class GuardiaoDeFerro extends Soldado {
//primeira subclasse de Soldado (Guardiao de ferro)
    private int vigor;
    private Random random = new Random();

    public GuardiaoDeFerro(String nome, int nivel) {
        super(nome, nivel, 150);
        this.vigor = 50;
    }

    @Override
    public void atacar(Soldado inimigo) {
        int dano = 15 + getNivel() * 2;
        System.out.println(getNome() + " ataca com arma pesada!");
        inimigo.defender(dano);
    }

//método de defesa com chance de bloquear o ataque

    @Override
    public void defender(int danoRecebido) {
        boolean bloqueio = random.nextInt(100) < 30;

        if (bloqueio && vigor >= 10) {
            vigor -= 10;
            System.out.println(getNome() + " bloqueou totalmente o ataque!");
        } else {
           super.defender(danoRecebido);
        }
    }
//método para recuperar vigor
    public void recuperarVigor(int quantidade) {
        vigor += quantidade;
        if (vigor > 50) {
            vigor = 50;
        }
    }
}
