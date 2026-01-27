import java.util.Random;
//A IA SUGERIU USAR import java.util.Random;
// que é um comando em Java que importa a classe Random do pacote java.util.
// Ela é usada para criar objetos que geram números pseudoaleatórios (int, double, boolean, etc.),
// essenciais para jogos, simulações e testes, sendo inicializada com Random gerador = new Random();
abstract class Soldado {
//classe base para os soldados
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

//método para aplicar dano ao soldado

    public void dano(int valor) {
        pontosDeVida -= valor;
        if (pontosDeVida < 0) {
            pontosDeVida = 0;
        }
    }
//metodo para verificar se o soldado está vivo e os pontos de vida
    public boolean estarVivo() {
        return pontosDeVida > 0;
    }
}

//muitos commits de uma vez,pois tive problemas de conexão e passei 3 dias sem conseguir salvar o código e nem subir no git