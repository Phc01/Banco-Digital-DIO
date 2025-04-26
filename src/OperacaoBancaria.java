import java.util.Scanner;

@FunctionalInterface
public interface OperacaoBancaria {
    void executar(Conta conta, Scanner scanner);
}
