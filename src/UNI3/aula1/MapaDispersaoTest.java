package UNI3.aula1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MapaDispersaoTest {

    // ====================================================================
    // PL01 - Caso 1: Inserir um dado novo e conseguir localizá-lo
    // Criar mapa de dispersão com capacidade 53.
    // Criar Aluno Jean, matrícula 12000, data 01/01/2000.
    // Inserir com chave 12000. Buscar pela chave 12000.
    // O objeto retornado deve ter identidade de referência idêntica ao obj1.
    // ====================================================================
    @Test
    public void testeCaso1_inserirEBuscarUmAluno() {
        MapaDispersao<Aluno> mapa = new MapaDispersao<>(53);

        Aluno obj1 = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));

        mapa.inserir(12000, obj1);

        Aluno resultado = mapa.buscar(12000);

        assertNotNull(resultado, "O aluno deveria ser encontrado no mapa.");
        assertSame(obj1, resultado, "O objeto retornado deve ser exatamente o mesmo objeto inserido (mesma referência).");
    }

    // ====================================================================
    // PL01 - Caso 2: Inserir mais de um objeto no mapa e localizar todos
    // Criar mapa com capacidade 53. Inserir 4 alunos SEM colisão.
    // Buscar todos por matrícula.
    // Os objetos retornados devem ser os mesmos originais.
    // ====================================================================
    @Test
    public void testeCaso2_inserirVariosAlunosSemColisao() {
        MapaDispersao<Aluno> mapa = new MapaDispersao<>(53);

        Aluno jean  = new Aluno(12000, "Jean",  LocalDate.of(2000, 1, 1));
        Aluno pedro = new Aluno(14000, "Pedro", LocalDate.of(1999, 1, 20));
        Aluno marta = new Aluno(12500, "Marta", LocalDate.of(2001, 2, 18));
        Aluno lucas = new Aluno(13000, "Lucas", LocalDate.of(1998, 11, 25));

        mapa.inserir(12000, jean);
        mapa.inserir(14000, pedro);
        mapa.inserir(12500, marta);
        mapa.inserir(13000, lucas);

        assertSame(jean,  mapa.buscar(12000), "Jean deveria ser encontrado pela matrícula 12000.");
        assertSame(pedro, mapa.buscar(14000), "Pedro deveria ser encontrado pela matrícula 14000.");
        assertSame(marta, mapa.buscar(12500), "Marta deveria ser encontrada pela matrícula 12500.");
        assertSame(lucas, mapa.buscar(13000), "Lucas deveria ser encontrado pela matrícula 13000.");
    }

    // ====================================================================
    // PL01 - Caso 3: Inserção e busca quando há colisão
    // Criar mapa com capacidade 53. Inserir 4 alunos com colisões:
    //   14226 colide com 12000 (ambos hash = 12000 % 53 = 24, 14226 % 53 = 24)
    //   17180 colide com 14000 (ambos hash = 14000 % 53 = 6, 17180 % 53 = 6)
    // Buscar todos por matrícula.
    // Os objetos retornados devem ser os mesmos originais.
    // ====================================================================
    @Test
    public void testeCaso3_inserirComColisao() {
        MapaDispersao<Aluno> mapa = new MapaDispersao<>(53);

        Aluno jean  = new Aluno(12000, "Jean",  LocalDate.of(2000, 1, 1));
        Aluno pedro = new Aluno(14000, "Pedro", LocalDate.of(1999, 1, 20));
        Aluno marta = new Aluno(14226, "Marta", LocalDate.of(2001, 2, 18));
        Aluno lucas = new Aluno(17180, "Lucas", LocalDate.of(1998, 11, 25));

        mapa.inserir(12000, jean);
        mapa.inserir(14000, pedro);
        mapa.inserir(14226, marta);
        mapa.inserir(17180, lucas);

        // Verificar que as colisões realmente ocorrem conforme esperado
        assertEquals(mapa.calcularHash(12000), mapa.calcularHash(14226),
                "12000 e 14226 devem colidir (mesmo hash).");
        assertEquals(mapa.calcularHash(14000), mapa.calcularHash(17180),
                "14000 e 17180 devem colidir (mesmo hash).");

        // Buscar todos os alunos — mesmo com colisão, devem ser encontrados
        assertSame(jean,  mapa.buscar(12000), "Jean deveria ser encontrado mesmo após colisão.");
        assertSame(pedro, mapa.buscar(14000), "Pedro deveria ser encontrado mesmo após colisão.");
        assertSame(marta, mapa.buscar(14226), "Marta deveria ser encontrada mesmo após colisão.");
        assertSame(lucas, mapa.buscar(17180), "Lucas deveria ser encontrado mesmo após colisão.");
    }

    // ====================================================================
    // Testes complementares
    // ====================================================================

    @Test
    public void testeBuscarChaveInexistente() {
        MapaDispersao<Aluno> mapa = new MapaDispersao<>(53);
        Aluno resultado = mapa.buscar(99999);
        assertNull(resultado, "Busca por chave inexistente deve retornar null.");
    }

    @Test
    public void testeRemoverAluno() {
        MapaDispersao<Aluno> mapa = new MapaDispersao<>(53);

        Aluno jean = new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1));
        mapa.inserir(12000, jean);

        assertNotNull(mapa.buscar(12000), "Jean deve estar no mapa antes da remoção.");

        mapa.remover(12000);

        assertNull(mapa.buscar(12000), "Após remoção, Jean não deve ser encontrado.");
    }

    @Test
    public void testeRemoverComColisao() {
        MapaDispersao<Aluno> mapa = new MapaDispersao<>(53);

        Aluno jean  = new Aluno(12000, "Jean",  LocalDate.of(2000, 1, 1));
        Aluno marta = new Aluno(14226, "Marta", LocalDate.of(2001, 2, 18));

        mapa.inserir(12000, jean);
        mapa.inserir(14226, marta);

        // Remover Jean (12000) — Marta (14226) que colide deve continuar acessível
        mapa.remover(12000);

        assertNull(mapa.buscar(12000), "Jean deve ter sido removido.");
        assertSame(marta, mapa.buscar(14226), "Marta deve continuar acessível após remoção de Jean.");
    }

    @Test
    public void testeCalcularFatorCarga() {
        MapaDispersao<Aluno> mapa = new MapaDispersao<>(53);

        assertEquals(0.0, mapa.calcularFatorCarga(), 0.001,
                "Fator de carga de mapa vazio deve ser 0.");

        mapa.inserir(12000, new Aluno(12000, "Jean", LocalDate.of(2000, 1, 1)));
        mapa.inserir(14000, new Aluno(14000, "Pedro", LocalDate.of(1999, 1, 20)));

        double fatorEsperado = 2.0 / 53.0;
        assertEquals(fatorEsperado, mapa.calcularFatorCarga(), 0.001,
                "Fator de carga deve ser total_elementos / tamanho_vetor.");
    }

    @Test
    public void testeCalcularHash() {
        MapaDispersao<Aluno> mapa = new MapaDispersao<>(53);

        assertEquals(12000 % 53, mapa.calcularHash(12000));
        assertEquals(14000 % 53, mapa.calcularHash(14000));
        assertEquals(14226 % 53, mapa.calcularHash(14226));
        assertEquals(17180 % 53, mapa.calcularHash(17180));

        // Confirmar colisões
        assertEquals(mapa.calcularHash(12000), mapa.calcularHash(14226));
        assertEquals(mapa.calcularHash(14000), mapa.calcularHash(17180));
    }
}
