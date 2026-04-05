package UNI1.aula6.exercicio1_2;

public class FilaCheiaException extends RuntimeException {

    public FilaCheiaException() {
        super("A fila está cheia!");
    }

    public FilaCheiaException(String mensagem) {
        super(mensagem);
    }
}