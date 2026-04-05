package UNI1.aula6.exercicio3_4;

import UNI1.aula3.NoLista;

public class ListaEncadeada<T> {

    private NoLista<T> primeiro;
    private NoLista<T> ultimo;
    
    public ListaEncadeada() {
        primeiro = null;
        ultimo = null;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }

    public void inserir(T valor) {
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(valor);

        if (estaVazia()) {
            primeiro = novo;
            ultimo = novo;
        } else {
            novo.setProximo(primeiro);
            primeiro = novo;
        }
    }

    public void inserirNoFinal(T valor) {
        NoLista<T> novo = new NoLista<>();
        novo.setInfo(valor);
        novo.setProximo(null);

        if (estaVazia()) {
            primeiro = novo;
            ultimo = novo;
        } else {
            ultimo.setProximo(novo);
            ultimo = novo;
        }
    }

    public void retirar(T valor) {
        NoLista<T> atual = primeiro;
        NoLista<T> anterior = null;

        while (atual != null && !atual.getInfo().equals(valor)) {
            anterior = atual;
            atual = atual.getProximo();
        }

        if (atual == null) return;

        if (anterior == null) {
            primeiro = atual.getProximo();

            if (primeiro == null) {
                ultimo = null;
            }
        } else {
            anterior.setProximo(atual.getProximo());

            if (atual == ultimo) {
                ultimo = anterior;
            }
        }
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

    @Override
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

    public NoLista<T> getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(NoLista<T> primeiro) {
        this.primeiro = primeiro;
    }

    public NoLista<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NoLista<T> ultimo) {
        this.ultimo = ultimo;
    }
}