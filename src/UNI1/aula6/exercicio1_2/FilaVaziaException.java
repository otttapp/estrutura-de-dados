package UNI1.aula6.exercicio1_2;

public class FilaVaziaException extends RuntimeException {

    public FilaVaziaException() {
        super("A fila está vazia!");
    }

    public FilaVaziaException(String mensagem) {
        super(mensagem);
    }
}