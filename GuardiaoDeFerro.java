import java.util.Random;

public class GuardiaoDeFerro extends Soldado {

    private int vigor;
    private Random random = new Random();

    public GuardiaoDeFerro(String nome, int nivel) {
        super(nome, nivel, 150);
        this.vigor = 50;
    }

    @Override
    public String atacar(Soldado inimigo) {
        int dano = 15 + getNivel() * 2;
        String msg = getNome() + " ataca com arma pesada!\n";
        msg += inimigo.defender(dano) + "\n";
        return msg;
    }



    @Override
    public String defender(int danoRecebido) {
        boolean bloqueio = random.nextInt(100) < 30;

        if (bloqueio && vigor >= 10) {
            vigor -= 10;
            return getNome() + " bloqueou totalmente o ataque!";
        } else {
           return super.defender(danoRecebido);
        }
    }

    public void recuperarVigor(int quantidade) {
        vigor += quantidade;
        if (vigor > 50) {
            vigor = 50;
        }
    }
}
