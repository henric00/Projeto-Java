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

