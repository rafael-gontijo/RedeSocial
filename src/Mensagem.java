public class Mensagem {
    private Usuario remetente;
    private Usuario destinatario;
    private String conteudo;

    public Mensagem(Usuario remetente, Usuario destinatario, String conteudo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public String getConteudo() {
        return conteudo;
    }
}
