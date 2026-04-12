package UNI2.aula1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ArvoreBinariaTest {

    private ArvoreBinaria<Integer> criarArvoreExemplo() {
        NoArvoreBinaria<Integer> no4 = new NoArvoreBinaria<>(4);
        NoArvoreBinaria<Integer> no5 = new NoArvoreBinaria<>(5);
        NoArvoreBinaria<Integer> no6 = new NoArvoreBinaria<>(6);

        NoArvoreBinaria<Integer> no2 = new NoArvoreBinaria<>(2, no4, null);
        NoArvoreBinaria<Integer> no3 = new NoArvoreBinaria<>(3, no5, no6);

        NoArvoreBinaria<Integer> no1 = new NoArvoreBinaria<>(1, no2, no3);

        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        arvore.setRaiz(no1);

        return arvore;
    }

    @Test
    public void testeToString() {
        ArvoreBinaria<Integer> arvore = criarArvoreExemplo();

        String esperado = "<1<2<4<><>><>><3<5<><>><6<><>>>>";

        assertEquals(esperado, arvore.toString());
    }

    @Test
    public void testeEstaVazia() {
        ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
        assertTrue(arvore.estaVazia());

        arvore = criarArvoreExemplo();
        assertFalse(arvore.estaVazia());
    }

    @Test
    public void testeContarNos() {
        ArvoreBinaria<Integer> arvore = criarArvoreExemplo();

        assertEquals(6, arvore.contarNos());
    }

    @Test
    public void testePertence() {
        ArvoreBinaria<Integer> arvore = criarArvoreExemplo();

        assertTrue(arvore.pertence(4));
        assertTrue(arvore.pertence(6));
        assertFalse(arvore.pertence(10));
    }
}