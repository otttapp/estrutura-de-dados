package UNI3.aula2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Testes do Mapa de Dispersão genérico (chaves K, valores T).
 * 
 * Plano de Testes PL01 — Validar implementação do mapa de dispersão
 * usando Veiculo como valor e String (placa) como chave.
 */
public class MapaDispersaoTest {

    // ====================================================================
    // PL01 - Caso 1: Validar que o método insere mais de um objeto
    // no mapa de dispersão, localizando os diversos objetos.
    //
    // Entrada:
    //   - Mapa de dispersão com capacidade de armazenamento de 5
    //     listas encadeadas.
    //   - Inserir os seguintes veículos:
    //     Placa       | Proprietário
    //     AXQ-3041    | Ana
    //     MSE-7521    | Pedro
    //     LJQ-5931    | Marta
    //     MQD-2241    | Lucas
    //
    // Saída esperada:
    //   - Os objetos retornados devem ser os mesmos que foram
    //     originalmente adicionados ao mapa de dispersão.
    //   - Solicite a busca de todos os veículos, um por um,
    //     utilizando a respectiva placa.
    // ====================================================================
    @Test
    public void testePL01_inserirEBuscarVeiculosPorPlaca() {
        MapaDispersao<String, Veiculo> mapa = new MapaDispersao<>(5);

        Veiculo ana   = new Veiculo("AXQ-3041", "Ana");
        Veiculo pedro = new Veiculo("MSE-7521", "Pedro");
        Veiculo marta = new Veiculo("LJQ-5931", "Marta");
        Veiculo lucas = new Veiculo("MQD-2241", "Lucas");

        // Inserir todos os veículos usando a placa como chave
        mapa.inserir("AXQ-3041", ana);
        mapa.inserir("MSE-7521", pedro);
        mapa.inserir("LJQ-5931", marta);
        mapa.inserir("MQD-2241", lucas);

        // Buscar todos os veículos, um por um, pela respectiva placa.
        // Os objetos retornados devem ser exatamente os mesmos (mesma referência).
        assertSame(ana, mapa.buscar("AXQ-3041"),
                "O veículo de Ana deveria ser encontrado pela placa AXQ-3041.");
        assertSame(pedro, mapa.buscar("MSE-7521"),
                "O veículo de Pedro deveria ser encontrado pela placa MSE-7521.");
        assertSame(marta, mapa.buscar("LJQ-5931"),
                "O veículo de Marta deveria ser encontrado pela placa LJQ-5931.");
        assertSame(lucas, mapa.buscar("MQD-2241"),
                "O veículo de Lucas deveria ser encontrado pela placa MQD-2241.");
    }

    // ====================================================================
    // Testes Complementares — validação aprofundada do mapa genérico
    // ====================================================================

    /**
     * Verifica que o hash é calculado usando Math.abs(hashCode()) % tamanho,
     * garantindo que o resultado é sempre um índice válido (>= 0).
     */
    @Test
    public void testeCalcularHashComChaveString() {
        MapaDispersao<String, Veiculo> mapa = new MapaDispersao<>(5);

        int hash1 = mapa.calcularHash("AXQ-3041");
        int hash2 = mapa.calcularHash("MSE-7521");
        int hash3 = mapa.calcularHash("LJQ-5931");
        int hash4 = mapa.calcularHash("MQD-2241");

        // Todos os hashes devem estar no intervalo [0, 4]
        assertTrue(hash1 >= 0 && hash1 < 5,
                "Hash de AXQ-3041 deve estar entre 0 e 4, obteve: " + hash1);
        assertTrue(hash2 >= 0 && hash2 < 5,
                "Hash de MSE-7521 deve estar entre 0 e 4, obteve: " + hash2);
        assertTrue(hash3 >= 0 && hash3 < 5,
                "Hash de LJQ-5931 deve estar entre 0 e 4, obteve: " + hash3);
        assertTrue(hash4 >= 0 && hash4 < 5,
                "Hash de MQD-2241 deve estar entre 0 e 4, obteve: " + hash4);
    }

    /**
     * Verifica que buscar uma placa inexistente retorna null.
     */
    @Test
    public void testeBuscarPlacaInexistente() {
        MapaDispersao<String, Veiculo> mapa = new MapaDispersao<>(5);

        mapa.inserir("AXQ-3041", new Veiculo("AXQ-3041", "Ana"));

        assertNull(mapa.buscar("ZZZ-9999"),
                "Busca por placa inexistente deve retornar null.");
    }

    /**
     * Verifica que a remoção funciona com chaves String.
     */
    @Test
    public void testeRemoverVeiculo() {
        MapaDispersao<String, Veiculo> mapa = new MapaDispersao<>(5);

        Veiculo ana = new Veiculo("AXQ-3041", "Ana");
        mapa.inserir("AXQ-3041", ana);

        assertNotNull(mapa.buscar("AXQ-3041"),
                "Veículo de Ana deve estar no mapa antes da remoção.");

        mapa.remover("AXQ-3041");

        assertNull(mapa.buscar("AXQ-3041"),
                "Após remoção, veículo de Ana não deve ser encontrado.");
    }

    /**
     * Testa que, ao remover um veículo cujo hash colide com outro,
     * o outro veículo continua acessível.
     */
    @Test
    public void testeRemoverComColisaoDeStrings() {
        MapaDispersao<String, Veiculo> mapa = new MapaDispersao<>(5);

        Veiculo ana   = new Veiculo("AXQ-3041", "Ana");
        Veiculo pedro = new Veiculo("MSE-7521", "Pedro");
        Veiculo marta = new Veiculo("LJQ-5931", "Marta");
        Veiculo lucas = new Veiculo("MQD-2241", "Lucas");

        mapa.inserir("AXQ-3041", ana);
        mapa.inserir("MSE-7521", pedro);
        mapa.inserir("LJQ-5931", marta);
        mapa.inserir("MQD-2241", lucas);

        // Remover Ana
        mapa.remover("AXQ-3041");

        assertNull(mapa.buscar("AXQ-3041"),
                "Ana deve ter sido removida.");
        assertSame(pedro, mapa.buscar("MSE-7521"),
                "Pedro deve continuar acessível.");
        assertSame(marta, mapa.buscar("LJQ-5931"),
                "Marta deve continuar acessível.");
        assertSame(lucas, mapa.buscar("MQD-2241"),
                "Lucas deve continuar acessível.");
    }

    /**
     * Verifica o cálculo do fator de carga com chaves String.
     */
    @Test
    public void testeCalcularFatorCarga() {
        MapaDispersao<String, Veiculo> mapa = new MapaDispersao<>(5);

        assertEquals(0.0, mapa.calcularFatorCarga(), 0.001,
                "Fator de carga de mapa vazio deve ser 0.");

        mapa.inserir("AXQ-3041", new Veiculo("AXQ-3041", "Ana"));
        mapa.inserir("MSE-7521", new Veiculo("MSE-7521", "Pedro"));
        mapa.inserir("LJQ-5931", new Veiculo("LJQ-5931", "Marta"));
        mapa.inserir("MQD-2241", new Veiculo("MQD-2241", "Lucas"));

        double fatorEsperado = 4.0 / 5.0; // 0.8
        assertEquals(fatorEsperado, mapa.calcularFatorCarga(), 0.001,
                "Fator de carga com 4 veículos e vetor de 5 deve ser 0.8.");
    }

    /**
     * Valida que o mapa funciona com chaves Integer (não apenas String),
     * demonstrando a verdadeira genericidade da implementação.
     */
    @Test
    public void testeMapaComChaveInteger() {
        MapaDispersao<Integer, String> mapa = new MapaDispersao<>(7);

        mapa.inserir(100, "Valor A");
        mapa.inserir(200, "Valor B");
        mapa.inserir(300, "Valor C");

        assertEquals("Valor A", mapa.buscar(100));
        assertEquals("Valor B", mapa.buscar(200));
        assertEquals("Valor C", mapa.buscar(300));
        assertNull(mapa.buscar(999));
    }
}
