public abstract class Soldado {

    private String nome;
    private int pontosDeVida;
    private int maxPontosDeVida;
    private int nivel;

    public Soldado(String nome, int nivel, int pontosDeVida) {
        this.nome = nome;
        // Todos os soldados começam no nível 1 independentemente do parâmetro
        this.nivel = 1;
        this.pontosDeVida = pontosDeVida;
        this.maxPontosDeVida = pontosDeVida;
    }

    public abstract String atacar(Soldado inimigo);
    public String defender(int danoRecebido){
        aplicarDano(danoRecebido);
        return this.nome + " recebeu " + danoRecebido + " de dano.";
    }

    protected void aplicarDano(int valor) {
        this.pontosDeVida -= valor;
        if (this.pontosDeVida < 0) {
            this.pontosDeVida = 0;
        }
    }

    public String subirNivel(){
        this.nivel += 1;
        return nome + " subiu para o nível " + nivel + "!";
    }
    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getPontosDeVida() { return pontosDeVida; }
    public int getMaxPontosDeVida() { return maxPontosDeVida; }

    public boolean estarVivo() {
        return pontosDeVida > 0;
    }
}