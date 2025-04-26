public class ContaPoupanca extends Conta{


    public ContaPoupanca(int numero, Cliente cliente) {
        super(numero, cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("\n=== Extrato Conta Poupança ===");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Saldo: R$ " + saldo);
    }
}
