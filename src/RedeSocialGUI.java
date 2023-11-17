import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RedeSocialGUI extends JFrame {
    private JPanel panel1 = new JPanel();
    private JButton buttonTarefa1 = new JButton("Cadastrar Usuário");
    private JButton buttonTarefa2 = new JButton("Login");
    private JButton buttonTarefa3 = new JButton("Adicionar Amigo");
    private JButton buttonTarefa4 = new JButton("Consultar Amigos");
    private JButton buttonTarefa5 = new JButton("Enviar Mensagem");
    private JButton buttonTarefa6 = new JButton("Visualizar Mensagens Enviadas");
    private JButton buttonTarefa7 = new JButton("Visualizar Mensagens Recebidas");
    private JButton buttonTarefa8 = new JButton("LogOut");
    private static Sistema sistema = new Sistema();

    public RedeSocialGUI() {
        this.setTitle("RedeSocial - Interface Grafica");
        this.setSize(300, 450);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        panel1.setBackground(new Color(255, 255, 255));
        ArrayList<String> listaTarefas = new ArrayList<String>();
        buttonTarefa1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog(null, "Nome:");
                String email = JOptionPane.showInputDialog(null, "Email:");
                String senha = JOptionPane.showInputDialog(null, "Senha:");
                sistema.cadastrarUsuarioGui(nome, email, senha);
            }
        });
        buttonTarefa2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String emailLogin = JOptionPane.showInputDialog(null, "Email:");
                String senhaLogin = JOptionPane.showInputDialog(null, "Senha:");
                sistema.loginGui(emailLogin, senhaLogin);
            }
        });
        buttonTarefa3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sistema.getUsuarioLogado() != null) {
                    String emailAmigo = JOptionPane.showInputDialog(null, "Email do Amigo:");
                    sistema.adicionarAmigoGui(emailAmigo);
                } else {
                    JOptionPane.showMessageDialog(null, "Você precisa estar logado para enviar mensagens.");
                }

            }
        });
        buttonTarefa4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sistema.consultarAmigosGUI();
            }
        });
        buttonTarefa5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sistema.getUsuarioLogado() != null) {
                    String emailDestinario = JOptionPane.showInputDialog(null, "Email do Amigo:");
                    String conteudo = JOptionPane.showInputDialog(null, "Conteudo da Mensagem:");
                    sistema.enviarMensagemGui(emailDestinario, conteudo);
                } else {
                    JOptionPane.showMessageDialog(null, "Você precisa estar logado para enviar mensagens.");
                }

            }
        });
        buttonTarefa6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sistema.visualizarMensagensEnviadasGui();
            }
        });
        buttonTarefa7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sistema.visualizarMensagensRecebidasGui();
            }
        });
        buttonTarefa8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sistema.logoutGui();
            }
        });

        panel1.add(buttonTarefa1);
        panel1.add(buttonTarefa2);
        panel1.add(buttonTarefa3);
        panel1.add(buttonTarefa4);
        panel1.add(buttonTarefa5);
        panel1.add(buttonTarefa6);
        panel1.add(buttonTarefa7);
        panel1.add(buttonTarefa8);

        this.getContentPane().add(panel1);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new RedeSocialGUI();
    }
}