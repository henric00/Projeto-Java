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
class GuardiaoDeFerro extends Soldado {
//primeira subclasse de Soldado (Guardiao de ferro)
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

//método de defesa com chance de bloquear o ataque

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
//método para recuperar vigor
    public void recuperarVigor(int quantidade) {
        vigor += quantidade;
        if (vigor > 50) {
            vigor = 50;
        }
    }
}

class Arcanista extends Soldado {
//segunda subclasse de Soldado (Arcanista)
    private int mana;

    public Arcanista(String nome, int nivel) {
        super(nome, nivel, 80);
        this.mana = 100;
    }
//método de ataque que usa mana para lançar feitiços
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
//método de defesa que reduz dano com mana
    @Override
    public void defender(int danoRecebido) {
        dano(danoRecebido);
        System.out.println(nome + " sofreu " + danoRecebido + " de dano.");
    }
}

public class Arena {
    //classe principal para testar os soldados
    public static void main(String[] args) {

        Soldado tanque = new GuardiaoDeFerro("Ares", 5);
        Soldado mago = new Arcanista("Merlin", 5);
        Soldado cacador = new Cacador("Legolas", 5);

        System.out.println("=== INÍCIO DA BATALHA ===\n");

        tanque.atacar(mago);
        mago.atacar(cacador);
        cacador.atacar(tanque);
//rodada 2
        System.out.println("\n=== STATUS FINAL ===");
        System.out.println("Tanque vivo? " + tanque.estarVivo());
        System.out.println("Mago vivo? " + mago.estarVivo());
        System.out.println("Caçador vivo? " + cacador.estarVivo());
    }
}

//muitos commits de uma vez,pois tive problemas de conexão e passei 3 dias sem conseguir salvar o código e nem subir no git