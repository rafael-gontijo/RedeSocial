import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;

    public Sistema() {
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void cadastrarUsuarioGui(String nome, String email, String senha) {
        if (buscarUsuarioPorEmail(email) == null) {
            Usuario novoUsuario = new Usuario(nome, email, senha);
            usuarios.add(novoUsuario);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Este email já está em uso. Tente outro.");
        }
    }

    public boolean loginGui(String email, String senha) {
        Usuario usuario = buscarUsuarioPorEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            usuarioLogado = usuario;
            JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha incorretos. Tente novamente.");
            return false;
        }
    }

    public void adicionarAmigoGui(String emailAmigo) {
        if (getUsuarioLogado() != null) {
            Usuario amigo = buscarUsuarioPorEmail(emailAmigo);
            if (amigo != null) {
                if (!usuarioLogado.equals(amigo) && !saoAmigos(usuarioLogado, amigo)) {
                    Amigo novoAmigo = new Amigo(amigo);
                    usuarioLogado.adicionarAmigo(novoAmigo);
                    JOptionPane.showMessageDialog(null, "Amigo adicionado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Você já é amigo deste usuário.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você precisa estar logado para adicionar amigos.");
        }
    }

    public void consultarAmigosGUI() {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(null, "Você precisa estar logado para consultar amigos.");
            return;
        }
        List<Amigo> amigos = usuarioLogado.getAmigos();
        if (amigos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você não tem amigos ainda.");
        } else {
            String msg = "";
            for (int i = 1; i <= amigos.size(); i++) {
                var name = amigos.get(i-1);
                msg += String.format("%d - %s\n", i, name.getUsuario().getNome() );
            }
            JOptionPane.showMessageDialog(null, "Lista de Amigos\n" + msg);
            }
        }
    public void enviarMensagemGui(String emailDestinatario, String conteudo) {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(null, "Você precisa estar logado para enviar mensagens.");
            return;
        }
        Usuario destinatario = buscarUsuarioPorEmail(emailDestinatario);

        if (destinatario != null) {
            Mensagem mensagem = new Mensagem(usuarioLogado, destinatario, conteudo);
            usuarioLogado.getMensagensEnviadas().add(mensagem);
            destinatario.getMensagensRecebidas().add(mensagem);
            JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
        }
    }
    public void visualizarMensagensEnviadasGui() {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(null, "Você precisa estar logado para visualizar mensagens enviadas.");
            return;
        }
        List<Mensagem> mensagensEnviadas = usuarioLogado.getMensagensEnviadas();

        if (mensagensEnviadas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você não enviou mensagens ainda.");
        } else {
            String msg = "";
            for (int i = 1; i <= mensagensEnviadas.size(); i++) {
                var mEnviada = mensagensEnviadas.get(i-1);
                msg += String.format("%d - %s - %s\n", i, mEnviada.getConteudo(), mEnviada.getDestinatario().getNome());
            }
            JOptionPane.showMessageDialog(null, "Mensagens Enviadas:\n" + msg);
        }
    }
    public void visualizarMensagensRecebidasGui() {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(null, "Você precisa estar logado para visualizar mensagens recebidas.");
            return;
        }
        List<Mensagem> mensagensRecebidas = usuarioLogado.getMensagensRecebidas();

        if (mensagensRecebidas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você não recebeu mensagens ainda.");
        } else {
            String msg = "";
            for (int i = 1; i <= mensagensRecebidas.size(); i++) {
                var mEnviada = mensagensRecebidas.get(i-1);
                msg += String.format("%d - %s - %s\n", i, mEnviada.getConteudo(), mEnviada.getRemetente().getNome());
            }
            JOptionPane.showMessageDialog(null, "Mensagens Recebidas:\n" + msg);
        }
    }

    public void logoutGui() {
        if (usuarioLogado != null) {
            JOptionPane.showMessageDialog(null, "Logout bem-sucedido. Até mais, " + usuarioLogado.getNome() + "!");
            usuarioLogado = null;
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum usuário logado.");
        }
    }
    public void cadastrarUsuario(String nome, String email, String senha) {
        // Verificar se o email já está em uso
        if (buscarUsuarioPorEmail(email) == null) {
            Usuario novoUsuario = new Usuario(nome, email, senha);
            usuarios.add(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Este email já está em uso. Tente outro.");
        }
    }

    public boolean login(String email, String senha) {
        Usuario usuario = buscarUsuarioPorEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            usuarioLogado = usuario;
            System.out.println("Login bem-sucedido!");
            return true;
        } else {
            System.out.println("Email ou senha incorretos. Tente novamente.");
            return false;
        }
    }
    public void adicionarAmigo(String emailAmigo) {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para adicionar amigos.");
            return;
        }

        Usuario amigo = buscarUsuarioPorEmail(emailAmigo);

        if (amigo != null) {
            if (!usuarioLogado.equals(amigo) && !saoAmigos(usuarioLogado, amigo)) {
                Amigo novoAmigo = new Amigo(amigo);
                usuarioLogado.adicionarAmigo(novoAmigo);
                System.out.println("Amigo adicionado com sucesso!");
            } else {
                System.out.println("Você já é amigo deste usuário.");
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void consultarAmigos() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para consultar amigos.");
            return;
        }

        List<Amigo> amigos = usuarioLogado.getAmigos();

        if (amigos.isEmpty()) {
            System.out.println("Você não tem amigos ainda.");
        } else {
            System.out.println("Seus amigos:");
            for (Amigo amigo : amigos) {
                System.out.println(amigo.getUsuario().getNome());
            }
        }
    }

    public void enviarMensagem(String emailDestinatario, String conteudo) {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para enviar mensagens.");
            return;
        }

        Usuario destinatario = buscarUsuarioPorEmail(emailDestinatario);

        if (destinatario != null) {
            Mensagem mensagem = new Mensagem(usuarioLogado, destinatario, conteudo);
            usuarioLogado.getMensagensEnviadas().add(mensagem);
            destinatario.getMensagensRecebidas().add(mensagem);
            System.out.println("Mensagem enviada com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    private boolean saoAmigos(Usuario usuario, Usuario amigo) {
        for (Amigo amigoAtual : usuario.getAmigos()) {
            if (amigoAtual.getUsuario().equals(amigo)) {
                return true;
            }
        }
        return false;
    }

    public void visualizarMensagensEnviadas() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para visualizar mensagens enviadas.");
            return;
        }

        List<Mensagem> mensagensEnviadas = usuarioLogado.getMensagensEnviadas();

        if (mensagensEnviadas.isEmpty()) {
            System.out.println("Você não enviou mensagens ainda.");
        } else {
            System.out.println("Suas mensagens enviadas:");
            for (Mensagem mensagem : mensagensEnviadas) {
                System.out.println(mensagem.getConteudo());
                System.out.println("-----------");
            }
        }
    }

    public void visualizarMensagensRecebidas() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para visualizar mensagens recebidas.");
            return;
        }

        List<Mensagem> mensagensRecebidas = usuarioLogado.getMensagensRecebidas();

        if (mensagensRecebidas.isEmpty()) {
            System.out.println("Você não recebeu mensagens ainda.");
        } else {
            System.out.println("Suas mensagens recebidas:");
            for (Mensagem mensagem : mensagensRecebidas) {
                System.out.println(mensagem.getConteudo());
                System.out.println("-----------");
            }
        }
    }

    public void logout() {
        if (usuarioLogado != null) {
            System.out.println("Logout bem-sucedido. Até mais, " + usuarioLogado.getNome() + "!");
            usuarioLogado = null;
        } else {
            System.out.println("Nenhum usuário logado.");
        }
    }


}
