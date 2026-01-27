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