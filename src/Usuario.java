import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private List<Amigo> amigos;
    private List<Mensagem> mensagensEnviadas;
    private List<Mensagem> mensagensRecebidas;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.amigos = new ArrayList<>();
        this.mensagensEnviadas = new ArrayList<>();
        this.mensagensRecebidas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public List<Amigo> getAmigos() {
        return amigos;
    }

    public List<Mensagem> getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public List<Mensagem> getMensagensRecebidas() {
        return mensagensRecebidas;
    }

    public void adicionarAmigo(Amigo amigo) {
        amigos.add(amigo);
    }

    public void adicionarMensagemEnviada(Mensagem mensagem) {
        mensagensEnviadas.add(mensagem);
    }

    public void adicionarMensagemRecebida(Mensagem mensagem) {
        mensagensRecebidas.add(mensagem);
    }
}
