import java.util.Random;

public abstract class Soldado {

    private String nome;
    private int pontosDeVida;
    private int nivel;

    public Soldado(String nome, int nivel, int pontosDeVida) {
        this.nome = nome;
        this.nivel = nivel;
        this.pontosDeVida = pontosDeVida;
    }

    public abstract void atacar(Soldado inimigo);
    public void defender(int danoRecebido){
        aplicarDano(danoRecebido);
        System.out.println(this.nome + " recebeu " + danoRecebido + " de dano. Vida restante " + this.pontosDeVida);
    }

    protected void aplicarDano(int valor) {
        this.pontosDeVida -= valor;
        if (this.pontosDeVida < 0) {
            this.pontosDeVida = 0;
        }
    }

    public void subirNivel(){
    this.nivel++;
    System.out.println(nome + " subiu para o nível " + nivel + "!");
}
public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getPontosDeVida() { return pontosDeVida; }

    public boolean estarVivo() {
        return pontosDeVida > 0;
    }
}