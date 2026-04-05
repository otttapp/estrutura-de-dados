package UNI1.aula5.exercicio1_2;

public class PilhaVetor<T> implements Pilha {
    private Object[] info;
    private int limite;
    private int tamanho;

    public PilhaVetor(int limite) {
        this.tamanho = 0;
        this.limite = limite;
        info = new Object[limite];
    }

    @Override
    public void push(Object info) {
        if (tamanho == limite) {
            throw new PilhaCheiaException();
        }
        this.info[tamanho] = info;
        tamanho++;
    }

    @Override
    public Object pop() {
        if (tamanho == 0) {
            throw new PilhaVaziaException();
        }

        Object valor = info[tamanho - 1];
        tamanho--;

        return valor;
    }

    @Override
    public Object peek() {
        if (tamanho == 0) {
            throw new PilhaVaziaException();
        }
        Object valor = info[tamanho - 1];
        return valor;
    }

    @Override
    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public void liberar() {
        info = new Object[limite];
        tamanho = 0;
    }

    @Override
    public String toString() {
        String frase = "";

        for (int i = tamanho - 1; i >= 0; i--) {
            frase += info[i];
            if (i != 0) {
                frase += ",";
            }
        }

        return frase;
    }

    public void concatenar(PilhaVetor<T> p) {
        // verifica capacidade
        if (this.tamanho + p.tamanho > this.limite) {
            throw new PilhaCheiaException("Sem capacidade para concatenar");
        }

        PilhaVetor<T> aux = new PilhaVetor<>(p.limite);

        while (!p.estaVazia()) {
            aux.push((T) p.pop());
        }

        while (!aux.estaVazia()) {
            T valor = (T) aux.pop();
            p.push(valor);
            this.push(valor);
        }
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object[] info) {
        this.info = info;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

}