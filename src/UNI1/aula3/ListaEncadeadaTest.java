package UNI1.aula3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ListaEncadeadaTest {

    @Test
    void caso1_listaVazia() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        assertTrue(lista.estaVazia());
    }

    @Test
    void caso2_listaNaoVazia() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);

        assertFalse(lista.estaVazia());
    }

    @Test
    void caso3_inserirUmElemento() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);

        NoLista<Integer> primeiro = lista.getPrimeiro();

        assertEquals(5, primeiro.getInfo());
        assertNull(primeiro.getProximo());
    }

    @Test
    void caso4_inserirTresElementos() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);

        assertEquals("15,10,5", lista.toString());
    }

    @Test
    void caso5_buscaPrimeiraPosicao() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> resultado = lista.buscar(20);

        assertNotNull(resultado);
        assertEquals(20, resultado.getInfo());
    }

    @Test
    void caso6_buscaMeioLista() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> resultado = lista.buscar(15);

        assertNotNull(resultado);
        assertEquals(15, resultado.getInfo());
    }

    @Test
    void caso7_buscaInexistente() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> resultado = lista.buscar(50);

        assertNull(resultado);
    }

    @Test
    void caso8_removerPrimeiroElemento() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(20);

        assertEquals("15,10,5", lista.toString());
    }

    @Test
    void caso9_removerElementoMeio() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(15);

        assertEquals("20,10,5", lista.toString());
    }

    @Test
    void caso10_obterNoPrimeiraPosicao() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> no = lista.obterNo(0);

        assertEquals(20, no.getInfo());
    }

    @Test
    void caso11_obterNoUltimaPosicao() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        NoLista<Integer> no = lista.obterNo(3);

        assertEquals(5, no.getInfo());
    }

    @Test
    void caso12_obterNoPosicaoInvalida() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.obterNo(10);
        });
    }

    @Test
    void caso13_comprimentoListaVazia() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        assertEquals(0, lista.obterComprimento());
    }

    @Test
    void caso14_comprimentoListaComElementos() {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();

        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(4, lista.obterComprimento());
    }
}