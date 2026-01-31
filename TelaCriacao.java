import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaCriacao extends JFrame {
    private ArrayList<Soldado> equipeLuz;
    private ArrayList<Soldado> equipeTrevas;
    
    private JComboBox<String> comboClasse;
    private JComboBox<String> comboLado;
    private JTextField textNome;
    private JButton botaoCriar;
    private JButton botaoIniciar;
    
    private JLabel labelLuz;
    private JLabel labelTrevas;
    private JPanel painelListaLuz;
    private JPanel painelListaTrevas;

    public TelaCriacao() {
        this.equipeLuz = new ArrayList<>();
        this.equipeTrevas = new ArrayList<>();
        
        configurarJanela();
        criarComponentes();
    }

    private void configurarJanela() {
        setTitle("⚔️ CRIADOR DE PERSONAGENS ⚔️");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(30, 30, 40));
    }

    private void criarComponentes() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBackground(new Color(30, 30, 40));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Painel de criação
        JPanel painelCriacao = criarPainelCriacao();
        painelPrincipal.add(painelCriacao, BorderLayout.WEST);

        // Painel de equipes
        JPanel painelEquipes = criarPainelEquipes();
        painelPrincipal.add(painelEquipes, BorderLayout.CENTER);

        add(painelPrincipal);
    }

    private JPanel criarPainelCriacao() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(30, 30, 40));
        painel.setMaximumSize(new Dimension(300, 600));

        JLabel titulo = new JLabel("Novo Personagem");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(Color.YELLOW);
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(15));

        // Nome
        JLabel labelNome = new JLabel("Nome:");
        labelNome.setForeground(Color.WHITE);
        painel.add(labelNome);
        textNome = new JTextField(15);
        textNome.setMaximumSize(new Dimension(250, 30));
        textNome.setBackground(new Color(60, 60, 70));
        textNome.setForeground(Color.WHITE);
        painel.add(textNome);
        painel.add(Box.createVerticalStrut(10));

        // Classe
        JLabel labelClasse = new JLabel("Classe:");
        labelClasse.setForeground(Color.WHITE);
        painel.add(labelClasse);
        comboClasse = new JComboBox<>(new String[]{"Caçador", "Arcanista", "Guardião de Ferro", "Assassino", "Berserker"});
        comboClasse.setBackground(new Color(60, 60, 70));
        comboClasse.setForeground(Color.WHITE);
        comboClasse.setMaximumSize(new Dimension(250, 30));
        painel.add(comboClasse);
        painel.add(Box.createVerticalStrut(10));

        // Nível removido: todos começarão no nível 1

        // Lado
        JLabel labelLadoCombo = new JLabel("Lado:");
        labelLadoCombo.setForeground(Color.WHITE);
        painel.add(labelLadoCombo);
        comboLado = new JComboBox<>(new String[]{"Luz", "Trevas"});
        comboLado.setBackground(new Color(60, 60, 70));
        comboLado.setForeground(Color.WHITE);
        comboLado.setMaximumSize(new Dimension(250, 30));
        painel.add(comboLado);
        painel.add(Box.createVerticalStrut(20));

        // Botão Criar
        botaoCriar = new JButton("✚ Criar Personagem");
        botaoCriar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoCriar.setBackground(new Color(0, 150, 0));
        botaoCriar.setForeground(Color.WHITE);
        botaoCriar.setFocusPainted(false);
        botaoCriar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoCriar.setMaximumSize(new Dimension(250, 40));
        botaoCriar.addActionListener(e -> criarPersonagem());
        painel.add(botaoCriar);

        painel.add(Box.createVerticalGlue());

        // Botão Iniciar
        botaoIniciar = new JButton("▶ Iniciar Batalha");
        botaoIniciar.setFont(new Font("Arial", Font.BOLD, 13));
        botaoIniciar.setBackground(new Color(150, 100, 0));
        botaoIniciar.setForeground(Color.WHITE);
        botaoIniciar.setFocusPainted(false);
        botaoIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoIniciar.setMaximumSize(new Dimension(250, 40));
        botaoIniciar.setEnabled(false);
        botaoIniciar.addActionListener(e -> iniciarBatalha());
        painel.add(botaoIniciar);

        return painel;
    }

    private JPanel criarPainelEquipes() {
        JPanel painel = new JPanel(new GridLayout(1, 2, 15, 0));
        painel.setBackground(new Color(30, 30, 40));

        // Lado Luz
        JPanel painelLuzPanel = new JPanel(new BorderLayout());
        painelLuzPanel.setBackground(new Color(40, 60, 30));
        painelLuzPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 255, 100), 2),
            "LADO DA LUZ", 0, 0, new Font("Arial", Font.BOLD, 14), Color.GREEN));

        labelLuz = new JLabel("0 personagens");
        labelLuz.setFont(new Font("Arial", Font.BOLD, 12));
        labelLuz.setForeground(Color.YELLOW);
        painelLuzPanel.add(labelLuz, BorderLayout.NORTH);

        painelListaLuz = new JPanel();
        painelListaLuz.setLayout(new BoxLayout(painelListaLuz, BoxLayout.Y_AXIS));
        painelListaLuz.setBackground(new Color(40, 60, 30));

        JScrollPane scrollLuz = new JScrollPane(painelListaLuz);
        scrollLuz.setBackground(new Color(40, 60, 30));
        scrollLuz.setBorder(null);
        painelLuzPanel.add(scrollLuz, BorderLayout.CENTER);

        // Lado Trevas
        JPanel painelTevasPanel = new JPanel(new BorderLayout());
        painelTevasPanel.setBackground(new Color(60, 30, 30));
        painelTevasPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(255, 100, 100), 2),
            "LADO DAS TREVAS", 0, 0, new Font("Arial", Font.BOLD, 14), Color.RED));

        labelTrevas = new JLabel("0 personagens");
        labelTrevas.setFont(new Font("Arial", Font.BOLD, 12));
        labelTrevas.setForeground(Color.YELLOW);
        painelTevasPanel.add(labelTrevas, BorderLayout.NORTH);

        painelListaTrevas = new JPanel();
        painelListaTrevas.setLayout(new BoxLayout(painelListaTrevas, BoxLayout.Y_AXIS));
        painelListaTrevas.setBackground(new Color(60, 30, 30));

        JScrollPane scrollTrevas = new JScrollPane(painelListaTrevas);
        scrollTrevas.setBackground(new Color(60, 30, 30));
        scrollTrevas.setBorder(null);
        painelTevasPanel.add(scrollTrevas, BorderLayout.CENTER);

        painel.add(painelLuzPanel);
        painel.add(painelTevasPanel);

        return painel;
    }

    private void criarPersonagem() {
        String nome = textNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um nome para o personagem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nivel = 1; // força nível inicial 1
        String classe = (String) comboClasse.getSelectedItem();
        String lado = (String) comboLado.getSelectedItem();

        Soldado soldado;
        switch (classe) {
            case "Caçador" -> soldado = new Cacador(nome, nivel);
            case "Arcanista" -> soldado = new Arcanista(nome, nivel);
            case "Guardião de Ferro" -> soldado = new GuardiaoDeFerro(nome, nivel);
            case "Assassino" -> soldado = new Assassino(nome, nivel);
            case "Berserker" -> soldado = new Berserker(nome, nivel);
            default -> soldado = new Cacador(nome, nivel);
        }

        if (lado.equals("Luz")) {
            equipeLuz.add(soldado);
            atualizarListaLuz();
        } else {
            equipeTrevas.add(soldado);
            atualizarListaTrevas();
        }

        textNome.setText("");

        // Só permite iniciar se ambos os lados tiverem o mesmo número de personagens (mínimo 1)
        if (equipeLuz.size() >= 1 && equipeTrevas.size() >= 1 && equipeLuz.size() == equipeTrevas.size()) {
            botaoIniciar.setEnabled(true);
        } else {
            botaoIniciar.setEnabled(false);
        }
    }

    private void atualizarListaLuz() {
        painelListaLuz.removeAll();
        labelLuz.setText(equipeLuz.size() + " personagem" + (equipeLuz.size() > 1 ? "s" : ""));

        for (Soldado s : equipeLuz) {
            painelListaLuz.add(criarPainelPersonagem(s, true));
            painelListaLuz.add(Box.createVerticalStrut(5));
        }

        painelListaLuz.revalidate();
        painelListaLuz.repaint();
    }

    private void atualizarListaTrevas() {
        painelListaTrevas.removeAll();
        labelTrevas.setText(equipeTrevas.size() + " personagem" + (equipeTrevas.size() > 1 ? "s" : ""));

        for (Soldado s : equipeTrevas) {
            painelListaTrevas.add(criarPainelPersonagem(s, false));
            painelListaTrevas.add(Box.createVerticalStrut(5));
        }

        painelListaTrevas.revalidate();
        painelListaTrevas.repaint();
    }

    private JPanel criarPainelPersonagem(Soldado soldado, boolean isLuz) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.X_AXIS));
        painel.setMaximumSize(new Dimension(300, 50));
        painel.setBackground(isLuz ? new Color(50, 80, 50) : new Color(80, 50, 50));
        painel.setBorder(BorderFactory.createLineBorder(
            isLuz ? new Color(100, 255, 100) : new Color(255, 100, 100), 1));

        String classe = soldado.getClass().getSimpleName();
        String info = String.format("%s", soldado.getNome());

        JLabel label = new JLabel(info);
        label.setFont(new Font("Courier New", Font.PLAIN, 11));
        label.setForeground(Color.WHITE);

        JLabel labelClasse = new JLabel(classe);
        labelClasse.setFont(new Font("Arial", Font.BOLD, 9));
        labelClasse.setForeground(Color.YELLOW);

        painel.add(Box.createHorizontalStrut(5));
        painel.add(labelClasse);
        painel.add(Box.createHorizontalStrut(10));
        painel.add(label);
        painel.add(Box.createHorizontalGlue());

        return painel;
    }

    private void iniciarBatalha() {
        if (equipeLuz.size() < 1 || equipeTrevas.size() < 1) {
            JOptionPane.showMessageDialog(this, "Cada lado precisa ter no mínimo 1 personagem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.setVisible(false);
        SwingUtilities.invokeLater(() -> {
            try {
                TelaBatalha telaBatalha = new TelaBatalha(equipeLuz, equipeTrevas);
                telaBatalha.setVisible(true);
            } catch (BatalhaInvalidaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                TelaCriacao.this.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCriacao tela = new TelaCriacao();
            tela.setVisible(true);
        });
    }
}
