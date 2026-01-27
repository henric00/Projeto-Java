abstract class Soldado {

    protected String nome;
    protected int pontosDeVida;
    protected int nivel;
    protected int pontosDeExperiencia;

    public Soldado(String nome, int nivel, int pontosDeVida) {
        this.nome = nome;
        this.nivel = nivel;
        this.pontosDeVida = pontosDeVida;
        this.pontosDeExperiencia = 0;
    }

    public abstract void atacar(Soldado inimigo);
    public abstract void defender(int danoRecebido);


    public void dano(int valor) {
        pontosDeVida -= valor;
        if (pontosDeVida < 0) {
            pontosDeVida = 0;
        }
    }

    public boolean estarVivo() {
        return pontosDeVida > 0;
    }
}
class GuardiaoDeFerro extends Soldado {

    private int vigor;
    private Random random = new Random();

    public GuardiaoDeFerro(String nome, int nivel) {
        super(nome, nivel, 150);
        this.vigor = 50;
    }

    @Override
    public void atacar(Soldado inimigo) {
        int dano = 15 + nivel * 2;
        System.out.println(nome + " ataca com arma pesada!");
        inimigo.defender(dano);
    }

    @Override
    public void defender(int danoRecebido) {
        boolean bloqueio = random.nextInt(100) < 30;

        if (bloqueio && vigor >= 10) {
            vigor -= 10;
            System.out.println(nome + " bloqueou totalmente o ataque!");
        } else {
            dano(danoRecebido);
            System.out.println(nome + " recebeu " + danoRecebido + " de dano.");
        }
    }
}
class Arcanista extends Soldado {

    private int mana;

    public Arcanista(String nome, int nivel) {
        super(nome, nivel, 80);
        this.mana = 100;
    }

    @Override
    public void atacar(Soldado inimigo) {
        if (mana >= 20) {
            int dano = 30 + nivel * 3;
            mana -= 20;
            System.out.println(nome + " lança um feitiço!");
            inimigo.defender(dano);
        } else {
            int dano = 8;
            mana += 10;
            System.out.println(nome + " ataca fisicamente e recupera mana!");
            inimigo.defender(dano);
        }
    }

    @Override
    public void defender(int danoRecebido) {
        dano(danoRecebido);
        System.out.println(nome + " sofreu " + danoRecebido + " de dano.");
    }
}
class Cacador extends Soldado {

    private Random random = new Random();

    public Cacador(String nome, int nivel) {
        super(nome, nivel, 70);
    }

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

    @Override
    public void defender(int danoRecebido) {
        dano(danoRecebido);
        System.out.println(nome + " recebeu " + danoRecebido + " de dano.");
    }
}

public class Arena {
    public static void main(String[] args) {

        Soldado tanque = new GuardiaoDeFerro("Ares", 5);
        Soldado mago = new Arcanista("Merlin", 5);
        Soldado cacador = new Cacador("Legolas", 5);

        System.out.println("=== INÍCIO DA BATALHA ===\n");

        tanque.atacar(mago);
        mago.atacar(cacador);
        cacador.atacar(tanque);

        System.out.println("\n=== STATUS FINAL ===");
        System.out.println("Tanque vivo? " + tanque.estarVivo());
        System.out.println("Mago vivo? " + mago.estarVivo());
        System.out.println("Caçador vivo? " + cacador.estarVivo());
    }
}



