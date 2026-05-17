package UNI3.aula2;

/**
 * Nó do mapa de dispersão com chaves genéricas.
 * 
 * Diferença em relação à aula 1: a chave agora é do tipo genérico K
 * (ao invés de int), permitindo usar String, Integer, ou qualquer
 * objeto como chave.
 * 
 * @param <K> tipo da chave (ex: String para placas de veículos)
 * @param <T> tipo do valor armazenado (ex: Veiculo)
 */
public class NoMapa<K, T> {

    private K chave;
    private T valor;

    public NoMapa(K chave, T valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public K getChave() {
        return chave;
    }

    public void setChave(K chave) {
        this.chave = chave;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * Compara dois NoMapa apenas pela CHAVE (ignora o valor).
     * Isso permite que a busca na ListaEncadeada funcione
     * usando um NoMapa temporário com valor null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoMapa<?, ?> noMapa = (NoMapa<?, ?>) o;
        return chave.equals(noMapa.chave);
    }

    /**
     * Delega o hashCode para a chave, mantendo o contrato
     * equals/hashCode consistente.
     */
    @Override
    public int hashCode() {
        return chave.hashCode();
    }
}
