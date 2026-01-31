import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaBatalha extends JFrame {
    private GerenciadorBatalha gerenciador;
    private JPanel painelLuz;
    private JPanel painelTrevas;
    private JButton botaoProxima;
    private JLabel labelRodada;
    private JLabel labelStatus;

    public TelaBatalha(ArrayList<Soldado> equipeLuz, ArrayList<Soldado> equipeTrevas) {
        this.gerenciador = new GerenciadorBatalha(equipeLuz, equipeTrevas);

        configurarJanela();
        criarComponentes();
        gerenciador.iniciarBatalha();
        atualizarInterface();
    }

    private void configurarJanela() {
        setTitle("⚔️ BATALHA: LUZ vs TREVAS ⚔️");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(30, 30, 40));
    }

    private void criarComponentes() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBackground(new Color(30, 30, 40));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel superior
        JPanel painelSuperior = criarPainelSuperior();
        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);

        // Painel central com os dois lados
        JPanel painelCentral = new JPanel(new GridLayout(1, 2, 15, 0));
        painelCentral.setBackground(new Color(30, 30, 40));

        painelLuz = new JPanel();
        painelLuz.setLayout(new BoxLayout(painelLuz, BoxLayout.Y_AXIS));
        painelLuz.setBackground(new Color(40, 60, 30));
        painelLuz.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 255, 100), 2),
            "LADO DA LUZ", 0, 0, new Font("Arial", Font.BOLD, 14), Color.GREEN));

        painelTrevas = new JPanel();
        painelTrevas.setLayout(new BoxLayout(painelTrevas, BoxLayout.Y_AXIS));
        painelTrevas.setBackground(new Color(60, 30, 30));
        painelTrevas.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(255, 100, 100), 2),
            "LADO DAS TREVAS", 0, 0, new Font("Arial", Font.BOLD, 14), Color.RED));

        JScrollPane scrollLuz = new JScrollPane(painelLuz);
        scrollLuz.setBackground(new Color(40, 60, 30));
        scrollLuz.setBorder(null);

        JScrollPane scrollTrevas = new JScrollPane(painelTrevas);
        scrollTrevas.setBackground(new Color(60, 30, 30));
        scrollTrevas.setBorder(null);

        painelCentral.add(scrollLuz);
        painelCentral.add(scrollTrevas);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // Painel inferior com botões
        JPanel painelInferior = criarPainelInferior();
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private JPanel criarPainelSuperior() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        painel.setBackground(new Color(30, 30, 40));

        labelRodada = new JLabel("RODADA: 0");
        labelRodada.setFont(new Font("Arial", Font.BOLD, 20));
        labelRodada.setForeground(Color.YELLOW);

        labelStatus = new JLabel("Preparando batalha...");
        labelStatus.setFont(new Font("Arial", Font.PLAIN, 16));
        labelStatus.setForeground(Color.WHITE);

        painel.add(labelRodada);
        painel.add(new JSeparator(SwingConstants.VERTICAL) {
            { setPreferredSize(new Dimension(2, 30)); }
        });
        painel.add(labelStatus);

        return painel;
    }

    private JPanel criarPainelInferior() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painel.setBackground(new Color(30, 30, 40));

        botaoProxima = new JButton("▶ Próxima Rodada");
        botaoProxima.setFont(new Font("Arial", Font.BOLD, 13));
        botaoProxima.setBackground(new Color(0, 150, 0));
        botaoProxima.setForeground(Color.WHITE);
        botaoProxima.setFocusPainted(false);
        botaoProxima.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoProxima.setPreferredSize(new Dimension(200, 45));
        botaoProxima.addActionListener(e -> executarProximaRodada());

        JButton botaoVoltar = new JButton("← Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 13));
        botaoVoltar.setBackground(new Color(100, 100, 100));
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltar.setPreferredSize(new Dimension(150, 45));
        botaoVoltar.addActionListener(e -> voltarTelaCriacao());

        painel.add(botaoProxima);
        painel.add(botaoVoltar);

        return painel;
    }

    private void executarProximaRodada() {
        if (!gerenciador.executarProximaRodada()) {
            botaoProxima.setEnabled(false);
            botaoProxima.setText("⚠ Batalha Finalizada");
        }
        atualizarInterface();
    }

    private void atualizarInterface() {
        atualizarPainelLados();
        atualizarLabels();
    }

    private void atualizarPainelLados() {
        painelLuz.removeAll();
        painelTrevas.removeAll();

        for (Soldado s : gerenciador.getLadoA()) {
            painelLuz.add(criarPainelSoldado(s, true));
            painelLuz.add(Box.createVerticalStrut(8));
        }

        for (Soldado s : gerenciador.getLadoB()) {
            painelTrevas.add(criarPainelSoldado(s, false));
            painelTrevas.add(Box.createVerticalStrut(8));
        }

        painelLuz.revalidate();
        painelLuz.repaint();
        painelTrevas.revalidate();
        painelTrevas.repaint();
    }

    private JPanel criarPainelSoldado(Soldado soldado, boolean isLuz) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setMaximumSize(new Dimension(500, 100));
        painel.setBackground(isLuz ? new Color(50, 80, 50) : new Color(80, 50, 50));
        painel.setBorder(BorderFactory.createLineBorder(
            isLuz ? new Color(100, 255, 100) : new Color(255, 100, 100), 2));
        painel.setOpaque(true);

        // Primeira linha: Nome e Classe
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        linha1.setBackground(isLuz ? new Color(50, 80, 50) : new Color(80, 50, 50));

        String classe = soldado.getClass().getSimpleName();
        JLabel labelClasse = new JLabel(classe);
        labelClasse.setFont(new Font("Arial", Font.BOLD, 12));
        labelClasse.setForeground(Color.YELLOW);

        JLabel labelNome = new JLabel(soldado.getNome());
        labelNome.setFont(new Font("Arial", Font.BOLD, 14));
        labelNome.setForeground(Color.WHITE);

        linha1.add(labelClasse);
        linha1.add(labelNome);

        // Segunda linha: Barra de vida
        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        linha2.setBackground(isLuz ? new Color(50, 80, 50) : new Color(80, 50, 50));

        JProgressBar barraVida = new JProgressBar(0, soldado.getPontosDeVida());
        barraVida.setValue(soldado.getPontosDeVida());
        barraVida.setStringPainted(true);
        barraVida.setString(String.format("❤ %d/%d", soldado.getPontosDeVida(), soldado.getPontosDeVida()));
        barraVida.setForeground(soldado.getPontosDeVida() > soldado.getPontosDeVida() / 2 ? 
            new Color(0, 200, 0) : new Color(255, 50, 50));
        barraVida.setBackground(new Color(50, 50, 50));
        barraVida.setPreferredSize(new Dimension(400, 25));
        barraVida.setFont(new Font("Arial", Font.BOLD, 11));

        linha2.add(barraVida);

        painel.add(linha1);
        painel.add(linha2);

        return painel;
    }

    private void atualizarLabels() {
        labelRodada.setText(String.format("RODADA: %d", gerenciador.getNumRodada()));

        if (gerenciador.temVencedor()) {
            String vencedor = gerenciador.getVencedor();
            labelStatus.setText("🏆 " + vencedor + " VENCEU! 🏆");
            labelStatus.setForeground(new Color(255, 215, 0));
        } else {
            int restanteLuz = gerenciador.getLadoA().size();
            int restanteTrevas = gerenciador.getLadoB().size();
            labelStatus.setText(String.format("Luz: %d ⚔️ Trevas: %d", restanteLuz, restanteTrevas));
            labelStatus.setForeground(Color.WHITE);
        }
    }

    private void voltarTelaCriacao() {
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            TelaCriacao tela = new TelaCriacao();
            tela.setVisible(true);
        });
    }
}
