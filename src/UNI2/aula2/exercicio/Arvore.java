package UNI2.aula2.exercicio;

public class Arvore<T> {
    private NoArvore<T> raiz;

    public Arvore() {
        raiz = null;
    }

    public NoArvore<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NoArvore<T> raiz) {
        this.raiz = raiz;
    }

    @Override
    public String toString() {
        if (raiz == null) {
            return "";
        }
        return obterRepresentacaoTextual(raiz);
    }

    private String obterRepresentacaoTextual(NoArvore<T> no) {
        String s = "<";
        s = s + no.getInfo();

        NoArvore<T> p = no.getPrimeiro();

        while (p != null) {
            s = s + obterRepresentacaoTextual(p);
            p = p.getProximo();
        }
        s = s + ">";
        return s;
    }

    public boolean pertence(T info) {
        if (raiz == null) {
            return false;
        }
        return pertence(raiz, info);
    }

    private boolean pertence(NoArvore<T> no, T info) {
        if (no.getInfo() == info) {
            return true;
        } else {

            NoArvore<T> p;
            p = no.getPrimeiro();
            while (p != null) {
                if (pertence(p, info)) {
                    return true;
                }
                p = p.getProximo();
            }
            return false;
        }
    }

    public int contarNos() {
        if (raiz == null) {
            return 0;
        }
        return contarNos(raiz);
    }

    private int contarNos(NoArvore<T> no) {
        int total = 1; 

        NoArvore<T> p = no.getPrimeiro();

        while (p != null) {
            total += contarNos(p); 
            p = p.getProximo();
        }

        return total;
    }
}
