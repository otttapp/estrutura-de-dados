package UNI1.aula6.exercicio3_4;

public class TesteFilaLista {
    public static void main(String[] args) {

        // =========================
        // CASO 1
        // =========================
        System.out.println("Caso 1:");
        FilaLista<Integer> f1 = new FilaLista<>();
        System.out.println(f1.estaVazia()); // true


        // =========================
        // CASO 2
        // =========================
        System.out.println("\nCaso 2:");
        FilaLista<Integer> f2 = new FilaLista<>();
        f2.inserir(10);
        System.out.println(f2.estaVazia()); // false


        // =========================
        // CASO 3
        // =========================
        System.out.println("\nCaso 3:");
        FilaLista<Integer> f3 = new FilaLista<>();

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
        FilaLista<Integer> f4 = new FilaLista<>();

        f4.inserir(10);
        f4.inserir(20);
        f4.inserir(30);

        System.out.println(f4.peek()); // 10
        System.out.println(f4.retirar()); // 10


        // =========================
        // CASO 5
        // =========================
        System.out.println("\nCaso 5:");
        FilaLista<Integer> f5 = new FilaLista<>();

        f5.inserir(10);
        f5.inserir(20);
        f5.inserir(30);

        f5.liberar();
        System.out.println(f5.estaVazia()); // true
    }
}