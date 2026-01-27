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

