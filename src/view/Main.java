package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JLabel jlNameCadastro;
    private JTextField jfNameCadastro;
    private JButton jbCadastro;
    private JPanel MainPanel;
    private JLabel jlCPFCadastro;
    private JTextField jfCPFCadastro;
    private JTextField jfUserNameCadastro;
    private JLabel jlSenhaCadastro;
    private JLabel jlUserNameCadastro;
    private JPasswordField jfPasswordCadastro;
    private JPanel SidePanel;
    private JLabel jlCadastrese;
    private JLabel jlLogin;
    private JTextField jfUsernameLogin;
    private JPasswordField jfPasswordLogin;
    private JLabel jlUsernameLogin;
    private JLabel jlPasswordLogin;
    private JComboBox jcDayCadastro;
    private JComboBox jcMonthCadastro;
    private JComboBox jcYearCadastro;
    private JButton jbLogin;
    private JLabel jlBirthDay;

    public Main() {
        ImageIcon img = new ImageIcon("C:\\Users\\Pedro Lira\\Documents\\projetos\\projetoPoo\\icon\\user.png");
        setIconImage(img.getImage());
        setContentPane(MainPanel);
        setTitle("Cadastro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        jbCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Main.this, "Your first name is " + jfNameCadastro.getText());
                jfNameCadastro.setText("");
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}