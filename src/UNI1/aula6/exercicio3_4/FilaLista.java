package UNI1.aula6.exercicio3_4;

import UNI1.aula6.exercicio1_2.Fila;

public class FilaLista<T> implements Fila {
    private ListaEncadeada<T> lista;

    public FilaLista() {
        this.lista = new ListaEncadeada<>();
    }

    @Override
    public void inserir(Object valor) {
        lista.inserirNoFinal((T) valor);
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public Object peek() {
        if (estaVazia()) {
            throw new RuntimeException("Fila vazia");
        }
        return lista.getPrimeiro().getInfo();
    }

    @Override
    public Object retirar() {
        if (estaVazia()) {
            throw new RuntimeException("Fila vazia");
        }

        T valor = (T) peek();
        lista.retirar(valor);
        return valor;
    }

    @Override
    public void liberar() {
        lista = new ListaEncadeada<>();
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}