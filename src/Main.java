import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Mini Simulador de Rede Social =====");
            if (sistema.getUsuarioLogado() != null) {
                System.out.println("Bem-vindo, " + sistema.getUsuarioLogado().getNome() + "!");
                System.out.println("1. Adicionar Amigo");
                System.out.println("2. Consultar Amigos");
                System.out.println("3. Enviar Mensagem");
                System.out.println("4. Visualizar Mensagens Enviadas");
                System.out.println("5. Visualizar Mensagens Recebidas");
                System.out.println("6. Logout");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        if (sistema.getUsuarioLogado() != null) {
                            System.out.print("Email do Amigo: ");
                            String emailAmigo = scanner.nextLine();
                            sistema.adicionarAmigo(emailAmigo);
                        } else {
                            System.out.println("Você precisa estar logado para adicionar amigos.");
                        }
                        break;
                    case 2:
                        sistema.consultarAmigos();
                        break;
                    case 3:
                        if (sistema.getUsuarioLogado() != null) {
                            System.out.print("Email do Destinatário: ");
                            String emailDestinatario = scanner.nextLine();
                            System.out.print("Conteúdo da Mensagem: ");
                            String conteudoMensagem = scanner.nextLine();
                            sistema.enviarMensagem(emailDestinatario, conteudoMensagem);
                        } else {
                            System.out.println("Você precisa estar logado para enviar mensagens.");
                        }
                        break;
                    case 4:
                        sistema.visualizarMensagensEnviadas();
                        break;
                    case 5:
                        sistema.visualizarMensagensRecebidas();
                        break;
                    case 6:
                        sistema.logout();
                        break;
                    case 0:
                        System.out.println("Saindo do programa. Até mais!");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida. Tente novamente.");


                }
            } else {
                System.out.println("1. Cadastrar Usuário");
                System.out.println("2. Login");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        sistema.cadastrarUsuario(nome, email, senha);
                        break;
                    case 2:
                        System.out.print("Email: ");
                        String emailLogin = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senhaLogin = scanner.nextLine();
                        sistema.login(emailLogin, senhaLogin);
                        break;
                    case 0:
                        System.out.println("Saindo do programa. Até mais!");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida. Tente novamente.");

                }
            }
        }
    }
}
