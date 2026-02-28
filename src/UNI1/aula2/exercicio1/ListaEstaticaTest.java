package UNI1.aula2.exercicio1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ListaEstaticaTest {

    @Test
    void caso1_insercao_toString() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals("5,10,15,20", lista.toString());
    }

    @Test
    void caso2_getTamanho() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(4, lista.getTamanho());
    }

    @Test
    void caso3_buscar_existente() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(2, lista.buscar(15));
    }

    @Test
    void caso4_buscar_inexistente() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(-1, lista.buscar(30));
    }

    @Test
    void caso5_retirar() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.retirar(10);

        assertEquals("5,15,20", lista.toString());
        assertEquals(3, lista.getTamanho());
    }

    @Test
    void caso6_redimensionamento() {
        ListaEstatica lista = new ListaEstatica();

        for (int i = 1; i <= 15; i++) {
            lista.inserir(i);
        }

        assertEquals(15, lista.getTamanho());
    }

    @Test
    void caso7_obterElemento_valido() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertEquals(20, lista.obterElemento(3));
    }

    @Test
    void caso8_obterElemento_invalido() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            lista.obterElemento(5);
        });
    }

    @Test
    void caso9_liberar() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.liberar();

        assertTrue(lista.estaVazia());
    }

    @Test
    void caso10_inverter_par() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);

        lista.inverter();

        assertEquals("20,15,10,5", lista.toString());
    }

    @Test
    void caso11_inverter_impar() {
        ListaEstatica lista = new ListaEstatica();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        lista.inserir(25);

        lista.inverter();

        assertEquals("25,20,15,10,5", lista.toString());
    }
}