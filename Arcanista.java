public class Arcanista extends Soldado {

    private int mana;

    public Arcanista(String nome, int nivel) throws NomeInvalidoException {
        super(nome, nivel, 80);
        this.mana = 100;
    }

    @Override
    public String atacar(Soldado inimigo) {
        StringBuilder msg = new StringBuilder();

        if (mana >= 20) {
            int dano = 30 + getNivel() * 3;
            mana -= 20;
            msg.append(getNome()).append(" lança um feitiço!\n");
            msg.append(inimigo.defender(dano)).append("\n");
        } else {
            int dano = 8;
            mana += 15; // recuperação de mana aumentada
            if (mana > 100) {
                mana = 100;
            }
            msg.append(getNome()).append(" ataca fisicamente e recupera mana!\n");
            msg.append(inimigo.defender(dano)).append("\n");
        }

        return msg.toString();
    }
}
