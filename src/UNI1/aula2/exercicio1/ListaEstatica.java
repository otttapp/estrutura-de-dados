package UNI1.aula2.exercicio1;

public class ListaEstatica<ClasseFornecida>{

    private Object[] info;
    private int tamanho;

    public ListaEstatica() {
        info = new Object[10];
        tamanho = 0;
    }

    private void redimensionar() {
        Object[] novo = new Object[(int) (info.length * 1.5)];
        for (int i = 0; i < tamanho; i++) {
            novo[i] = info[i];
        }
        info = novo;
    }

    public void inserir(ClasseFornecida valor) {
        if (tamanho == info.length) {
            redimensionar();
        }
        info[tamanho] = valor;
        tamanho++;
    }

    public void exibir() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(info[i]);
        }
    }

    public int buscar(ClasseFornecida valor) {
        for (int i = 0; i < tamanho; i++) {
            if (info[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }

    public void retirar(ClasseFornecida valor) {
        int pos = buscar(valor);
        if (pos == -1) {
            return;
        }
        for (int i = pos; i < tamanho - 1; i++) {
            info[i] = info[i + 1];
        }
        tamanho--;
    }

    public void liberar() {
        info = new Object[10];
        tamanho = 0;
    }

    public ClasseFornecida obterElemento(int posicao) {
        if (posicao >= tamanho || posicao < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (ClasseFornecida) info[posicao];
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public String toString() {
        if (tamanho == 0) {
            return "";
        }
        String retorno = "" + info[0];
        for (int i = 1; i < tamanho; i++) {
            retorno += "," + info[i];
        }
        return retorno;
    }

    public void inverter() {
        for (int inicio = 0, fim = tamanho - 1; inicio < fim; inicio++, fim--) { // percorre so a metade do array
            Object temp = info[inicio];
            info[inicio] = info[fim]; // aloca o ultimo para o primeiro
            info[fim] = temp; // aloca o primeiro para o ultimo
        }
    }
}
