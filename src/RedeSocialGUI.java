import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RedeSocialGUI extends JFrame  {
    private JPanel panel1 = new JPanel();
    private JButton buttonTarefa1 = new JButton("Cadastrar Usuário");
    private JButton buttonTarefa2 = new JButton("Login");
    private JButton buttonTarefa3 = new JButton("Adicionar Amigo");
    private JButton buttonTarefa4 = new JButton("Consultar Amigos");
    private JButton buttonTarefa5 = new JButton("Enviar Mensagem");
    private JButton buttonTarefa6 = new JButton("Visualizar Mensagens Enviadas");
    private JButton buttonTarefa7 = new JButton("Visualizar Mensagens Recebidas");
    private static Sistema sistema = new Sistema();

    public RedeSocialGUI() {
        this.setTitle("RedeSocial - Interface Grafica");
        this.setSize(300, 400);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        panel1.setBackground(new Color(255, 255, 255));
        ArrayList<String> listaTarefas = new ArrayList<String>();
        buttonTarefa1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Nome:");
                String nome = input;
                String input2 = JOptionPane.showInputDialog(null, "Email:");
                String email = input2;
                String input3 = JOptionPane.showInputDialog(null, "Senha:");
                String senha = input3;
                sistema.cadastrarUsuarioGui(nome, email, senha);
            }
        });
        buttonTarefa2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Email:");
                String emailLogin = input;
                String input2 = JOptionPane.showInputDialog(null, "Senha:");
                String senhaLogin = input2;
                sistema.loginGui(emailLogin, senhaLogin);
            }
        });
        buttonTarefa3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Email do Amigo:");
                String emailAmigo = input;
                sistema.adicionarAmigoGui(emailAmigo);
            }
        });
        buttonTarefa4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sistema.consultarAmigosGUI();
            }
        });

        panel1.add(buttonTarefa1);
        panel1.add(buttonTarefa2);
        panel1.add(buttonTarefa3);
        panel1.add(buttonTarefa4);
        panel1.add(buttonTarefa5);
        panel1.add(buttonTarefa6);
        panel1.add(buttonTarefa7);


        this.getContentPane().add(panel1);
        this.setLocationRelativeTo(null); // Centralizar janela
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true); // Exibir janela
    }
    public static void main(String[] args) {
        new RedeSocialGUI();
    }
}