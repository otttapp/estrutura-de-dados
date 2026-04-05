package UNI1.aula6.exercicio1_2;

public interface Fila<T> {
    public void inserir(T valor);
    public boolean estaVazia();
    public T peek();
    public T retirar();
    public void liberar();
}
