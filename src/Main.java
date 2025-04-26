import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Banco banco = new Banco("Banco Digital");
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        Map<Integer, Runnable> menuOperacoes = new HashMap<>();

        while (executando) {
            System.out.println("\n=== MENU BANCO DIGITAL ===");
            System.out.println("1 - Criar Conta Corrente");
            System.out.println("2 - Criar Conta Poupança");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Ver Extrato");
            System.out.println("7 - Listar Contas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarConta(banco, scanner, true);
                case 2 -> criarConta(banco, scanner, false);
                case 3 -> executarOperacao(banco, scanner, (conta, sc) -> {
                    System.out.println("Valor do depósito: ");
                    double valor = sc.nextDouble();
                    conta.depositar(valor);
                });
                case 4 -> executarOperacao(banco, scanner, ((conta, sc) -> {
                    System.out.println("Valor do Saque: ");
                    double valor = sc.nextDouble();
                    conta.sacar(valor);
                }));
                case 5 -> transferir(banco, scanner);
                case 6 -> executarOperacao(banco, scanner, (conta, sc) -> conta.imprimirExtrato());
                case 7 -> listarConta(banco);
                case 0 -> {
                    executando = false;
                    System.out.println("Saindo do Banco...");
                }
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private static void criarConta(Banco banco, Scanner scanner, boolean isCorrente) {
        System.out.println("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.println("CPF do cliente: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        int numeroConta = banco.gerarNumeroConta();

        Conta conta;
        if (isCorrente) {
            conta = new ContaCorrente(numeroConta, cliente);
        } else {
            conta = new ContaPoupanca(numeroConta, cliente);
        }

        banco.adicionarConta(conta);
        System.out.println("Conta criada com sucesso! Número da conta: " + numeroConta);
    }

    private static void executarOperacao(Banco banco, Scanner scanner, OperacaoBancaria operacao) {
        System.out.println("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = banco.buscaConta(numero);

        if (conta != null) {
            operacao.executar(conta, scanner);
            System.out.println("Saldo atual da conta: " + conta.saldo);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void transferir(Banco banco, Scanner scanner) {
        System.out.println("Número da conta de origem: ");
        int numeroOrigem = scanner.nextInt();
        System.out.println("Número da conta de destino: ");
        int numeroDestino = scanner.nextInt();;
        System.out.println("Valor de transferência: ");
        double valor = scanner.nextDouble();

        Conta origem = banco.buscaConta(numeroOrigem);
        Conta destino = banco.buscaConta(numeroDestino);

        if (origem != null && destino != null) {
            origem.transferir(valor, destino);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Conta(s) não encontrada(s).");
        }
    }

    private static void listarConta(Banco banco) {
        System.out.println("\n=== LISTA DE CONTAS ===");
        for (Conta c : banco.getContas()) {
            System.out.println("Conta Nª " + c.getNumero() + " | Cliente: " + c.getCliente().getNome());
        }
    }
}
