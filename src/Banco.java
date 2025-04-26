import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nome;
    @Getter
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public Conta buscaConta(int numero) {
        return contas.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public int gerarNumeroConta() {
        return contas.size() + 1;
    }
}
