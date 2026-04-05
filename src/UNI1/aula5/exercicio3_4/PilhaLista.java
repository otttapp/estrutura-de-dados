package UNI1.aula5.exercicio3_4;

import UNI1.aula3.ListaEncadeada;
import UNI1.aula5.exercicio1_2.PilhaVaziaException;
import UNI1.aula5.exercicio1_2.Pilha;

public class PilhaLista<T> implements Pilha{
    private ListaEncadeada<T> lista;
    
    public PilhaLista(ListaEncadeada<T> lista) {
        this.lista = lista;
    }

    @Override
    public void push(Object info) {
        lista.inserir((T) info);
    }

    @Override
    public Object pop() {
        if(lista.estaVazia()){
            throw new PilhaVaziaException();
        }
        Object valor = lista.obterNo(0);
        lista.retirar((T) valor);
        return valor;
    }

    @Override
    public Object peek() {
       return lista.obterNo(0);
    }

    @Override
    public boolean estaVazia() {
        return lista.estaVazia();
    }

    @Override
    public void liberar() {
        lista = new ListaEncadeada<>();
    }

    public String toString(){
        return lista.toString();
    }
    
}
