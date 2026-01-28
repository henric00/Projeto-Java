import java.util.Random;
public class Arcanista extends Soldado {
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
            int dano = 30 + getNivel() * 3;
            mana -= 20;
            System.out.println(getNome() + " lança um feitiço!");
            inimigo.defender(dano);
        } else {
            int dano = 8;
            mana += 10;
            System.out.println(getNome() + " ataca fisicamente e recupera mana!");
            inimigo.defender(dano);
        }
    }

}