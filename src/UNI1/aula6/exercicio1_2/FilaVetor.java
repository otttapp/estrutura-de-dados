package UNI1.aula6.exercicio1_2;

public class FilaVetor<T> implements Fila {
    private Object[] info;
    private int limite;
    private int tamanho;
    private int inicio;

    public FilaVetor(int limite) {
        info = new Object[limite];
        tamanho = 0;
        inicio = 0;
        this.limite = limite;
    }

    public int getLimite() {
        return limite;
    }

    @Override
    public void inserir(Object valor) {
        if (tamanho == limite) {
            throw new FilaCheiaException();
        }
        int posicaoInserir;
        posicaoInserir = (inicio + tamanho) % limite;
        info[posicaoInserir] = valor;
        tamanho++;
    }

    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public Object peek() {
        if (estaVazia()) {
            throw new FilaVaziaException();
        }
        return info[inicio];
    }

    @Override
    public Object retirar() {
        Object valor = peek();

        inicio = (inicio + 1) % limite;
        tamanho--;
        return valor;
    }

    @Override
    public void liberar() {
        info = new Object[limite];
    }

    public FilaVetor<T> criarFilaConcatenada(FilaVetor f2) {
        FilaVetor f3 = new FilaVetor<>(limite + f2.getLimite());

        for (int i = 0; i < tamanho; i++) {
            int pos = (inicio + i) % this.limite;
            f3.inserir((T) info[pos]);
        }

        for (int i = 0; i < f2.tamanho; i++) {
            int pos = (f2.inicio + i) % f2.limite;
            f3.inserir((T) f2.info[pos]);
        }

        return f3;
    }

    @Override
    public String toString() {
        String concatenacao = "";

        for (int i = 0; i < tamanho; i++) {
            int pos = (inicio + i) % limite;
            concatenacao += info[pos];

            if (i != tamanho - 1) {
                concatenacao += ",";
            }
        }

        return concatenacao;
    }
}
