package UNI1.aula5.exercicio1_2;
public class PilhaCheiaException extends RuntimeException {

    public PilhaCheiaException() {
        super("A pilha está cheia!");
    }

    public PilhaCheiaException(String mensagem) {
        super(mensagem);
    }
}