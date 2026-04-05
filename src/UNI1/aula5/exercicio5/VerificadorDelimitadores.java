package UNI1.aula5.exercicio5;

import java.util.Scanner;

import UNI1.aula5.exercicio1_2.PilhaVetor;

public class VerificadorDelimitadores {

    public static boolean validar(String expressao) {
        PilhaVetor<Character> pilha = new PilhaVetor<>(expressao.length());

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                pilha.push(c);
            }

            else if (c == ')' || c == ']' || c == '}') {

                if (pilha.estaVazia()) {
                    return false;
                }

                char topo = (char) pilha.pop();

                if (!combina(topo, c)) {
                    return false;
                }
            }
        }

        return pilha.estaVazia();
    }

    private static boolean combina(char abre, char fecha) {
        return (abre == '(' && fecha == ')') ||
               (abre == '[' && fecha == ']') ||
               (abre == '{' && fecha == '}');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a expressão: ");
        String exp = sc.nextLine();

        if (validar(exp)) {
            System.out.println("Delimitadores CORRETOS");
        } else {
            System.out.println("Delimitadores INCORRETOS");
        }

        sc.close();
    }
}