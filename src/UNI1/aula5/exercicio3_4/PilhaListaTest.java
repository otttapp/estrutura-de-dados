package UNI1.aula5.exercicio3_4;

import UNI1.aula3.ListaEncadeada;

public class PilhaListaTest {
    public static void main(String[] args) {

        // 🔹 Caso 1
        PilhaLista<Integer> p1 = new PilhaLista<>(new ListaEncadeada<>());
        System.out.println("Caso 1: " + p1.estaVazia()); // true

        // 🔹 Caso 2
        PilhaLista<Integer> p2 = new PilhaLista<>(new ListaEncadeada<>());
        p2.push(10);
        System.out.println("Caso 2: " + p2.estaVazia()); // false

        // 🔹 Caso 3
        PilhaLista<Integer> p3 = new PilhaLista<>(new ListaEncadeada<>());
        p3.push(10);
        p3.push(20);
        p3.push(30);

        System.out.println("Caso 3:");
        System.out.println(p3.pop()); // 30
        System.out.println(p3.pop()); // 20
        System.out.println(p3.pop()); // 10
        System.out.println(p3.estaVazia()); // true

        // 🔹 Caso 4
        PilhaLista<Integer> p4 = new PilhaLista<>(new ListaEncadeada<>());
        p4.push(10);
        p4.push(20);
        p4.push(30);

        System.out.println("Caso 4:");
        System.out.println(p4.peek()); // 30
        System.out.println(p4.pop());  // 30

        // 🔹 Caso 5
        PilhaLista<Integer> p5 = new PilhaLista<>(new ListaEncadeada<>());
        p5.push(10);
        p5.push(20);
        p5.push(30);

        p5.liberar();

        System.out.println("Caso 5: " + p5.estaVazia()); // true
    }
}