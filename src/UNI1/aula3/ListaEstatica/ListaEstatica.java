package UNI1.aula3.ListaEstatica;

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
        for (int i = pos + 1; i < tamanho; i++) {
            info[i - 1 ] = info[i];
        }
        tamanho--;
        info[tamanho] =  null;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void liberar() {
        info = new Object[10];
        tamanho = 0;
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

    public ClasseFornecida obterElemento(int posicao) {
        if (posicao >= tamanho || posicao < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (ClasseFornecida) info[posicao];
    }

    public void inverter() {
        int j = tamanho - 1;
        for(int i =0; i < tamanho /2; i++){
            Object temp = info[i];
            info[i] = info[j];
            info[j] = temp;
            j--;
        }
    }
}
