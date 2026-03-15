package UNI1.aula4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ListaDuplaTest {

    private ListaDupla<Integer> criarLista() {
        ListaDupla<Integer> lista = new ListaDupla<>();
        lista.inserir(5);
        lista.inserir(10);
        lista.inserir(15);
        lista.inserir(20);
        return lista;
    }

    @Test
    public void testarInsercao() {

        ListaDupla<Integer> lista = criarLista();

        assertEquals("20,15,10,5", lista.toString());

        NoListaDupla<Integer> primeiro = lista.buscar(20);

        assertNotNull(primeiro);
        assertEquals(15, primeiro.getProximo().getInfo());
    }

    @Test
    public void buscarElementoInicio() {

        ListaDupla<Integer> lista = criarLista();

        NoListaDupla<Integer> resultado = lista.buscar(20);

        assertNotNull(resultado);
        assertEquals(20, resultado.getInfo());
    }

    @Test
    public void buscarElementoMeio() {

        ListaDupla<Integer> lista = criarLista();

        NoListaDupla<Integer> resultado = lista.buscar(10);

        assertNotNull(resultado);
        assertEquals(10, resultado.getInfo());
    }

    @Test
    public void removerInicio() {

        ListaDupla<Integer> lista = criarLista();

        lista.retirar(20);

        assertEquals("15,10,5", lista.toString());

        NoListaDupla<Integer> primeiro = lista.getPrimeiro();

        assertEquals(15, primeiro.getInfo());
        assertNull(primeiro.getAnterior());
    }

    @Test
    public void removerMeio() {

        ListaDupla<Integer> lista = criarLista();

        lista.retirar(10);

        assertEquals("20,15,5", lista.toString());

        NoListaDupla<Integer> no = lista.buscar(15);

        assertEquals(20, no.getAnterior().getInfo());
        assertEquals(5, no.getProximo().getInfo());
    }

    @Test
    public void removerFim() {

        ListaDupla<Integer> lista = criarLista();

        lista.retirar(5);

        assertEquals("20,15,10", lista.toString());

        NoListaDupla<Integer> ultimo = lista.buscar(10);

        assertNull(ultimo.getProximo());
    }

    @Test
    public void liberarLista() {

        ListaDupla<Integer> lista = criarLista();

        NoListaDupla<Integer> n1 = lista.buscar(5);
        NoListaDupla<Integer> n2 = lista.buscar(10);
        NoListaDupla<Integer> n3 = lista.buscar(15);
        NoListaDupla<Integer> n4 = lista.buscar(20);

        lista.liberar();

        assertNull(n1.getProximo());
        assertNull(n1.getAnterior());

        assertNull(n2.getProximo());
        assertNull(n2.getAnterior());

        assertNull(n3.getProximo());
        assertNull(n3.getAnterior());

        assertNull(n4.getProximo());
        assertNull(n4.getAnterior());

        assertNull(lista.getPrimeiro());
    }
}