package UNI1.aula4;

public class ListaDupla<T> {
    private NoListaDupla<T> primeiro;

    public ListaDupla() {
        primeiro = null;
    }

    public NoListaDupla<T> getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(NoListaDupla<T> primeiro) {
        this.primeiro = primeiro;
    }

    public void inserir(T valor) {
        NoListaDupla<T> p = new NoListaDupla<>();
        p.setInfo(valor);

        p.setProximo(primeiro);
        p.setAnterior(null);

        if (primeiro != null) {
            primeiro.setAnterior(p);
        }

        primeiro = p;
    }

    public NoListaDupla<T> buscar(T valor) {
        NoListaDupla<T> p = primeiro;
        while (p != null) {
            if (p.getInfo().equals(valor)) {
                return p;
            }
            p = p.getProximo();
        }
        return null;
    }

    public void retirar(T valor) {
        NoListaDupla<T> p = buscar(valor);

        if (p == null) {
            return;
        }

        if (p == primeiro) {
            primeiro = p.getProximo();
            if (primeiro != null) {
                primeiro.setAnterior(null);
            }
        } else {

            NoListaDupla<T> ant = p.getAnterior();
            NoListaDupla<T> prox = p.getProximo();

            ant.setProximo(prox);

            if (prox != null) {
                prox.setAnterior(ant);
            }
        }
    }

    public void exibirOrdemInversa() {
        if (primeiro == null) {
            return;
        }

        NoListaDupla<T> p = primeiro;

        while (p.getProximo() != null) {
            p = p.getProximo();
        }

        while (p != null) {
            System.out.println(p.getInfo());
            p = p.getAnterior();
        }
    }

    public void liberar() {

        NoListaDupla<T> p = primeiro;

        while (p != null) {

            NoListaDupla<T> prox = p.getProximo();

            p.setAnterior(null);
            p.setProximo(null);
            p.setInfo(null);

            p = prox;
        }

        primeiro = null;
    }

    public String toString() {
        NoListaDupla<T> p = primeiro;
        String s = "";
        while (p != null) {
            s += p.getInfo();
            if (p.getProximo() != null) {
                s += ",";
            }
            p = p.getProximo();
        }

        return s;
    }
}
