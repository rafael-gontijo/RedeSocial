import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;  // Para manter o controle do usuário logado

    public Sistema() {
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
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
                System.out.println(mensagem);
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
                System.out.println(mensagem);
                System.out.println("-----------");
            }
        }
    }

    public void logout() {
        if (usuarioLogado != null) {
            System.out.println("Logout bem-sucedido. Até mais, " + usuarioLogado.getNome() + "!");
            usuarioLogado = null; // Limpa o usuário logado
        } else {
            System.out.println("Nenhum usuário logado.");
        }
    }


}
