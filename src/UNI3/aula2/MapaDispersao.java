package UNI3.aula2;

import UNI1.aula3.ListaEncadeada;
import UNI1.aula3.NoLista;

/**
 * Mapa de Dispersão com chaves genéricas (K, T).
 * 
 * Evolução da implementação da aula 1: agora aceita qualquer tipo
 * como chave (String, Integer, etc.), ao invés de apenas int.
 * 
 * O cálculo do hash utiliza o método hashCode() nativo do Java,
 * aplicando Math.abs() para garantir que o índice seja positivo,
 * pois hashCode() pode retornar valores negativos.
 * 
 * @param <K> tipo da chave (ex: String)
 * @param <T> tipo do valor armazenado (ex: Veiculo)
 */
public class MapaDispersao<K, T> {

    private ListaEncadeada<NoMapa<K, T>>[] info;

    /**
     * Construtor — cria o vetor interno com o tamanho informado,
     * inicializando cada posição com uma ListaEncadeada vazia.
     * 
     * @param tamanho tamanho do vetor interno (número de buckets)
     */
    @SuppressWarnings("unchecked")
    public MapaDispersao(int tamanho) {
        info = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            info[i] = new ListaEncadeada<>();
        }
    }

    /**
     * Calcula o índice no vetor a partir de uma chave genérica.
     * 
     * Utiliza o hashCode() do objeto chave e aplica Math.abs()
     * para garantir que o resultado seja positivo, pois o
     * hashCode() do Java pode retornar números negativos.
     * 
     * Fórmula: Math.abs(chave.hashCode()) % tamanho_do_vetor
     * 
     * @param chave objeto usado como chave
     * @return índice no vetor (0 <= índice < info.length)
     */
    public int calcularHash(K chave) {
        return Math.abs(chave.hashCode()) % info.length;
    }

    /**
     * Insere um par chave-valor no mapa.
     * 
     * Passo a passo:
     * 1. Calcula o hash da chave → obtém o índice
     * 2. Cria um NoMapa com a chave e o valor
     * 3. Insere o NoMapa na lista encadeada daquele índice
     * 
     * @param chave chave genérica (ex: "AXQ-3041")
     * @param valor objeto a ser armazenado (ex: Veiculo)
     */
    public void inserir(K chave, T valor) {
        int indice = calcularHash(chave);
        NoMapa<K, T> novoNo = new NoMapa<>(chave, valor);
        info[indice].inserir(novoNo);
    }

    /**
     * Remove do mapa o objeto associado à chave informada.
     * 
     * Cria um NoMapa temporário com a chave (valor null) e
     * usa ListaEncadeada.retirar() que encontra e remove o nó
     * cujo equals() corresponde (comparação apenas pela chave).
     * 
     * @param chave chave do objeto a ser removido
     */
    public void remover(K chave) {
        int indice = calcularHash(chave);
        NoMapa<K, T> chaveBusca = new NoMapa<>(chave, null);
        info[indice].retirar(chaveBusca);
    }

    /**
     * Busca no mapa o objeto associado à chave informada.
     * 
     * Passo a passo:
     * 1. Calcula o hash da chave → obtém o índice
     * 2. Cria um NoMapa temporário com a chave e valor null
     * 3. Usa ListaEncadeada.buscar() que percorre a lista
     *    usando equals() (que compara apenas a chave)
     * 4. Se encontrou, retorna o valor do nó; senão, retorna null
     * 
     * @param chave chave a ser buscada
     * @return o valor associado à chave, ou null se não encontrado
     */
    public T buscar(K chave) {
        int indice = calcularHash(chave);
        NoMapa<K, T> chaveBusca = new NoMapa<>(chave, null);

        NoLista<NoMapa<K, T>> resultado = info[indice].buscar(chaveBusca);

        if (resultado != null) {
            return resultado.getInfo().getValor();
        }

        return null;
    }

    /**
     * Calcula e retorna o fator de carga do mapa.
     * 
     * Fórmula: totalDeElementos / tamanhoDovetor
     * 
     * Percorre todas as listas do vetor e soma seus comprimentos
     * para obter o total de elementos armazenados.
     * 
     * @return fator de carga (double)
     */
    public double calcularFatorCarga() {
        int totalElementos = 0;
        for (int i = 0; i < info.length; i++) {
            totalElementos += info[i].obterComprimento();
        }
        return (double) totalElementos / info.length;
    }
}
