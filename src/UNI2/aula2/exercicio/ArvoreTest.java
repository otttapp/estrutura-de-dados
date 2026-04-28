package UNI2.aula2.exercicio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArvoreTest {

    private Arvore<Integer> arvore;

    @BeforeEach
    void setup() {
        arvore = new Arvore<>();

        NoArvore<Integer> n1 = new NoArvore<>(1);
        NoArvore<Integer> n2 = new NoArvore<>(2);
        NoArvore<Integer> n3 = new NoArvore<>(3);
        NoArvore<Integer> n4 = new NoArvore<>(4);
        NoArvore<Integer> n5 = new NoArvore<>(5);
        NoArvore<Integer> n6 = new NoArvore<>(6);
        NoArvore<Integer> n7 = new NoArvore<>(7);
        NoArvore<Integer> n8 = new NoArvore<>(8);
        NoArvore<Integer> n9 = new NoArvore<>(9);
        NoArvore<Integer> n10 = new NoArvore<>(10);

        
        n2.inserirFilho(n7);
        n2.inserirFilho(n6);
        n2.inserirFilho(n5);

        n3.inserirFilho(n8);

        n4.inserirFilho(n10);
        n4.inserirFilho(n9);

        n1.inserirFilho(n4);
        n1.inserirFilho(n3);
        n1.inserirFilho(n2);

        arvore.setRaiz(n1);
    }

    @Test
    void testToString() {
        String esperado = "<1<2<5><6><7>><3<8>><4<9><10>>>";
        assertEquals(esperado, arvore.toString());
    }

    @Test
    void testPertenceTrue() {
        assertTrue(arvore.pertence(7));
    }

    @Test
    void testPertenceFalse() {
        assertFalse(arvore.pertence(55));
    }
    
    @Test
    void testContarNos() {
        assertEquals(10, arvore.contarNos());
    }
}