import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCriacao tela = new TelaCriacao();
            tela.setVisible(true);
        });
    }
}