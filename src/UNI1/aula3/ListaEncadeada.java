package UNI1.aula3;

public class ListaEncadeada<T> {

    private NoLista<T> primeiro;

    public ListaEncadeada() {
        primeiro = null;
    }

    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public NoLista<T> buscar(T valor) {
        NoLista<T> p = primeiro;

        while (p != null) {
            if (p.getInfo().equals(valor)) {
                return p;
            }
            p = p.getProximo();
        }

        return null;
    }

    public void retirar(T valor) {
        NoLista<T> atual = primeiro;
        NoLista<T> anterior = null;

        while (atual != null && !atual.getInfo().equals(valor)) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual == null) {
            return;
        }

        if (anterior == null) {
            primeiro = atual.getProximo();
        } else {
            anterior.setProximo(atual.getProximo());
        }
    }

    public void inserir(T valor) {
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(valor);
        novo.setProximo(primeiro);
        primeiro = novo;
    }

   public NoLista<T> obterNo(int idx) {
        NoLista<T> atual = primeiro;
        int i = 0;

        if (idx < 0) {
            throw new IndexOutOfBoundsException("Índice não pode ser negativo.");
        }

        while (atual != null && i < idx) {
            atual = atual.getProximo();
            i++;
        }

        if (atual == null) {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista.");
        }
        return atual;
    }

    public int obterComprimento() {

        int contador = 0;
        NoLista<T> atual = primeiro;

        while (atual != null) {
            contador++;
            atual = atual.getProximo();
        }

        return contador;
    }

     public String toString() {

        String resultado = "";
        NoLista<T> atual = primeiro;

        while (atual != null) {
            resultado += atual.getInfo();

            if (atual.getProximo() != null) {
                resultado += ",";
            }

            atual = atual.getProximo();
        }

        return resultado;
    }
}
