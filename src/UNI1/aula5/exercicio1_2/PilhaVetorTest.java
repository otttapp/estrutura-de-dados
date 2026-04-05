package UNI1.aula5.exercicio1_2;

public class PilhaVetorTest {
    public static void main(String[] args) {

        // 🔹 Caso 1
        PilhaVetor<Integer> p1 = new PilhaVetor<>(5);
        System.out.println("Caso 1: " + p1.estaVazia()); // true

        // 🔹 Caso 2
        PilhaVetor<Integer> p2 = new PilhaVetor<>(5);
        p2.push(10);
        System.out.println("Caso 2: " + p2.estaVazia()); // false

        // 🔹 Caso 3
        PilhaVetor<Integer> p3 = new PilhaVetor<>(10);
        p3.push(10);
        p3.push(20);
        p3.push(30);

        System.out.println("Caso 3:");
        System.out.println(p3.pop()); // 30
        System.out.println(p3.pop()); // 20
        System.out.println(p3.pop()); // 10
        System.out.println(p3.estaVazia()); // true

        // 🔹 Caso 4
        try {
            PilhaVetor<Integer> p4 = new PilhaVetor<>(3);
            p4.push(10);
            p4.push(20);
            p4.push(30);
            p4.push(40); // erro
        } catch (PilhaCheiaException e) {
            System.out.println("Caso 4: PilhaCheiaException OK");
        }

        // 🔹 Caso 5
        try {
            PilhaVetor<Integer> p5 = new PilhaVetor<>(5);
            p5.pop(); // erro
        } catch (PilhaVaziaException e) {
            System.out.println("Caso 5: PilhaVaziaException OK");
        }

        // 🔹 Caso 6
        PilhaVetor<Integer> p6 = new PilhaVetor<>(5);
        p6.push(10);
        p6.push(20);
        p6.push(30);

        System.out.println("Caso 6:");
        System.out.println(p6.peek()); // 30
        System.out.println(p6.pop()); // 30

        // 🔹 Caso 7
        PilhaVetor<Integer> p7 = new PilhaVetor<>(5);
        p7.push(10);
        p7.push(20);
        p7.push(30);
        p7.liberar();

        System.out.println("Caso 7: " + p7.estaVazia()); // true

        // 🔹 Caso 8
        PilhaVetor<Integer> p8a = new PilhaVetor<>(10);
        p8a.push(10);
        p8a.push(20);
        p8a.push(30);

        PilhaVetor<Integer> p8b = new PilhaVetor<>(10);
        p8b.push(40);
        p8b.push(50);

        p8a.concatenar(p8b);

        System.out.println("Caso 8: " + p8a.toString());
    }
}
