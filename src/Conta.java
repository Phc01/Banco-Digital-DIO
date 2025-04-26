import lombok.Getter;

public abstract class Conta {

    @Getter
    protected int numero;
    protected double saldo;
    @Getter
    protected Cliente cliente;

    public Conta(int numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo Insuficiente!");
        }
    }

    public void transferir(double valor, Conta destino) {
        if (valor <= saldo) {
            this.sacar(valor);
            destino.depositar(valor);
        } else {
            System.out.println("Saldo Insuficiente para transferência!");
        }
    }

    public abstract void imprimirExtrato();

}
