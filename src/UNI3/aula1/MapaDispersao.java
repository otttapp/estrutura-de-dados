package UNI3.aula1;

import UNI1.aula3.ListaEncadeada;
import UNI1.aula3.NoLista;

public class MapaDispersao<T> {

    private ListaEncadeada<NoMapa<T>>[] info;

    @SuppressWarnings("unchecked")
    public MapaDispersao(int tamanho) {
        info = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            info[i] = new ListaEncadeada<>();
        }
    }

    public int calcularHash(int chave) {
        return chave % info.length;
    }

    public void inserir(int chave, T dado) {
        int indice = calcularHash(chave);
        NoMapa<T> novoNo = new NoMapa<>(chave, dado);
        info[indice].inserir(novoNo);
    }

    public void remover(int chave) {
        int indice = calcularHash(chave);
        NoMapa<T> chaveBusca = new NoMapa<>(chave, null);
        info[indice].retirar(chaveBusca);
    }

    public T buscar(int chave) {
        int indice = calcularHash(chave);
        NoMapa<T> chaveBusca = new NoMapa<>(chave, null);

        NoLista<NoMapa<T>> resultado = info[indice].buscar(chaveBusca);

        if (resultado != null) {
            return resultado.getInfo().getValor();
        }

        return null;
    }

    public double calcularFatorCarga() {
        int totalElementos = 0;
        for (int i = 0; i < info.length; i++) {
            totalElementos += info[i].obterComprimento();
        }
        return (double) totalElementos / info.length;
    }
}
