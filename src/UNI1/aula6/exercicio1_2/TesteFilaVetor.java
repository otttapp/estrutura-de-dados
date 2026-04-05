package UNI1.aula6.exercicio1_2;

public class TesteFilaVetor {
    public static void main(String[] args) {

        // =========================
        // CASO 1
        // =========================
        System.out.println("Caso 1:");
        FilaVetor<Integer> f1 = new FilaVetor<>(5);
        System.out.println(f1.estaVazia()); // true


        // =========================
        // CASO 2
        // =========================
        System.out.println("\nCaso 2:");
        FilaVetor<Integer> f2 = new FilaVetor<>(5);
        f2.inserir(10);
        System.out.println(f2.estaVazia()); // false


        // =========================
        // CASO 3
        // =========================
        System.out.println("\nCaso 3:");
        FilaVetor<Integer> f3 = new FilaVetor<>(10);
        f3.inserir(10);
        f3.inserir(20);
        f3.inserir(30);

        System.out.println(f3.retirar()); // 10
        System.out.println(f3.retirar()); // 20
        System.out.println(f3.retirar()); // 30
        System.out.println(f3.estaVazia()); // true


        // =========================
        // CASO 4
        // =========================
        System.out.println("\nCaso 4:");
        try {
            FilaVetor<Integer> f4 = new FilaVetor<>(3);
            f4.inserir(10);
            f4.inserir(20);
            f4.inserir(30);
            f4.inserir(40); // erro esperado
        } catch (FilaCheiaException e) {
            System.out.println("FilaCheiaException OK");
        }


        // =========================
        // CASO 5
        // =========================
        System.out.println("\nCaso 5:");
        try {
            FilaVetor<Integer> f5 = new FilaVetor<>(5);
            f5.retirar(); // erro esperado
        } catch (FilaVaziaException e) {
            System.out.println("FilaVaziaException OK");
        }


        // =========================
        // CASO 6
        // =========================
        System.out.println("\nCaso 6:");
        FilaVetor<Integer> f6 = new FilaVetor<>(5);
        f6.inserir(10);
        f6.inserir(20);
        f6.inserir(30);

        System.out.println(f6.peek()); // 10
        System.out.println(f6.retirar()); // 10


        // =========================
        // CASO 7
        // =========================
        System.out.println("\nCaso 7:");
        FilaVetor<Integer> f7 = new FilaVetor<>(5);
        f7.inserir(10);
        f7.inserir(20);
        f7.inserir(30);

        f7.liberar();
        System.out.println(f7.estaVazia()); // true


        // =========================
        // CASO 8 (CONCATENAÇÃO)
        // =========================
        System.out.println("\nCaso 8:");

        FilaVetor<Integer> fila1 = new FilaVetor<>(5);
        fila1.inserir(10);
        fila1.inserir(20);
        fila1.inserir(30);

        FilaVetor<Integer> fila2 = new FilaVetor<>(3);
        fila2.inserir(40);
        fila2.inserir(50);

        FilaVetor<Integer> fila3 = fila1.criarFilaConcatenada(fila2);

        // fila resultante
        System.out.println(fila3.toString()); // 10,20,30,40,50

        // filas originais NÃO podem mudar
        System.out.println(fila1.toString()); // 10,20,30
        System.out.println(fila2.toString()); // 40,50
    }
}