package view;

import controller.CadastroController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginCadastro extends JFrame {

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

    public JTextField getJfNameCadastro() {
        return jfNameCadastro;
    }

    public void setJfNameCadastro(JTextField jfNameCadastro) {
        this.jfNameCadastro = jfNameCadastro;
    }

    public JButton getJbCadastro() {
        return jbCadastro;
    }

    public void setJbCadastro(JButton jbCadastro) {
        this.jbCadastro = jbCadastro;
    }

    public JTextField getJfCPFCadastro() {
        return jfCPFCadastro;
    }

    public void setJfCPFCadastro(JTextField jfCPFCadastro) {
        this.jfCPFCadastro = jfCPFCadastro;
    }

    public JTextField getJfUserNameCadastro() {
        return jfUserNameCadastro;
    }

    public void setJfUserNameCadastro(JTextField jfUserNameCadastro) {
        this.jfUserNameCadastro = jfUserNameCadastro;
    }

    public JPasswordField getJfPasswordCadastro() {
        return jfPasswordCadastro;
    }

    public void setJfPasswordCadastro(JPasswordField jfPasswordCadastro) {
        this.jfPasswordCadastro = jfPasswordCadastro;
    }

    public JTextField getJfUsernameLogin() {
        return jfUsernameLogin;
    }

    public void setJfUsernameLogin(JTextField jfUsernameLogin) {
        this.jfUsernameLogin = jfUsernameLogin;
    }

    public JPasswordField getJfPasswordLogin() {
        return jfPasswordLogin;
    }

    public void setJfPasswordLogin(JPasswordField jfPasswordLogin) {
        this.jfPasswordLogin = jfPasswordLogin;
    }

    public JComboBox getJcDayCadastro() {
        return jcDayCadastro;
    }

    public void setJcDayCadastro(JComboBox jcDayCadastro) {
        this.jcDayCadastro = jcDayCadastro;
    }

    public JComboBox getJcMonthCadastro() {
        return jcMonthCadastro;
    }

    public void setJcMonthCadastro(JComboBox jcMonthCadastro) {
        this.jcMonthCadastro = jcMonthCadastro;
    }

    public JComboBox getJcYearCadastro() {
        return jcYearCadastro;
    }

    public void setJcYearCadastro(JComboBox jcYearCadastro) {
        this.jcYearCadastro = jcYearCadastro;
    }

    public JButton getJbLogin() {
        return jbLogin;
    }

    public void setJbLogin(JButton jbLogin) {
        this.jbLogin = jbLogin;
    }

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

    public LoginCadastro() {
        ImageIcon img = new ImageIcon("icon/user.png");
        
        setIconImage(img.getImage());
        setContentPane(MainPanel);
        setTitle("Cadastro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        LoginCadastro tela = new LoginCadastro();
        new CadastroController(tela);
    }
}