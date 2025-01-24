package view;

import javax.swing.*;

public class TelaPrincipal extends JFrame {
    private JPanel panel1;
    private JFormattedTextField TELAPRINCIPALFormattedTextField;

    // private JFormattedTextField TELAPRINCIPALFormattedTextField;
   // private JPanel panel1;
    public TelaPrincipal() {
        setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Ajuste o tamanho conforme necessário
        setLocationRelativeTo(null); // Centraliza a janela
        setResizable(false);

        JLabel label = new JLabel("Bem-vindo à Tela Principal!");
        add(label);
        setVisible(true);
    }
}
